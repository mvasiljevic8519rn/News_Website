package rs.raf.demo.services;

import rs.raf.demo.entities.Coment;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.repositories.coments.ComentsRepository;
import rs.raf.demo.repositories.subject.SubjectRepository;

import javax.inject.Inject;
import javax.xml.stream.events.Comment;
import java.util.List;

public class ComentsService {

    public ComentsService() {
        System.out.println(this);
    }
    @Inject
    private ComentsRepository comentsRepository;

    public Coment addComent(Coment coment) {return this.comentsRepository.addComent(coment);}


    public Integer max(Integer id){return this.comentsRepository.max(id);}
    public List<Coment> allComentsFromNewsWithId(Integer newsId,Integer page) {return this.comentsRepository.getAllComentFromNewsWithId(newsId,page);}

    public Coment findComent(Integer comentId){return this.comentsRepository.getComentWihtId(comentId);}

    public void deleteComent(Integer comentId){this.comentsRepository.deleteComent(comentId);}

    public void deleteAllComents(Integer newsId){this.comentsRepository.deleteAllComents(newsId);}
}
