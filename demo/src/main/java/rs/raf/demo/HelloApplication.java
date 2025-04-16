package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.repositories.Users.MsqlUserRepository;
import rs.raf.demo.repositories.kategorije.KategorijeRepository;
import rs.raf.demo.repositories.kategorije.MsqlKategorijeRepository;
import rs.raf.demo.repositories.newsall.MsqlNewsRepository;
import rs.raf.demo.repositories.newsall.NewsRepository;
import rs.raf.demo.repositories.coments.ComentsRepository;
import rs.raf.demo.repositories.coments.MsqlComentsRepository;
import rs.raf.demo.repositories.subject.MySqlSubjectRepository;
import rs.raf.demo.repositories.subject.SubjectRepository;
import rs.raf.demo.repositories.Users.InMemoryUserRepository;
import rs.raf.demo.repositories.Users.UserRepository;
import rs.raf.demo.repositories.tags.MsqlTagRepository;
import rs.raf.demo.repositories.tags.TagRepository;
import rs.raf.demo.resources.publicResources.PublicService;
import rs.raf.demo.services.*;


import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlSubjectRepository.class).to(SubjectRepository.class).in(Singleton.class);
            //    this.bind(InMemoryUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MsqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MsqlNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MsqlComentsRepository.class).to(ComentsRepository.class).in(Singleton.class);
                this.bind(MsqlKategorijeRepository.class).to(KategorijeRepository.class).in(Singleton.class);
                this.bind(MsqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                //this.bind(MsqlTagRepository.class).to(TagRepository.class).in(Singleton.class);

                this.bindAsContract(SubjectService.class);
          //      this.bindAsContract(UserService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(ComentsService.class);
                this.bindAsContract(MyUserService.class);
                this.bindAsContract(KategorijeService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(PublicService.class);

            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("rs.raf.demo");
    }
}
