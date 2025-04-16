package rs.raf.demo.resources.publicResources;

import rs.raf.demo.entities.Id;
import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.News;
import rs.raf.demo.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/public")
public class PublicResources {
    @Inject
    private PublicService publicService;

    //news
    @GET
    @Path("/news")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> all(@QueryParam("page") Integer page) {
        System.out.println("SADADas"); return null;  }//this.publicService.allNews(page);}
    @GET
    @Path("/news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  News find(@PathParam("id") int id){
        return this.publicService.findNewsById(id);
    }
    @GET
    @Path("/news/max")
    @Produces({MediaType.APPLICATION_JSON})
    public Integer maxNews() {return this.publicService.maxNews();}
    @GET
    @Path("/news/inKategorija/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allInKategorija(@PathParam("id") int id) {return this.publicService.allNewsInKategorija(id);}
    @GET
    @Path("/news/withTag/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allWithTag(@PathParam("name") String name) {return this.publicService.allNewsWithTag(name);}
    @GET
    @Path("/news/sortedDate")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allSortedDate( Integer page) {return this.publicService.allNewsSortedDate();}
    @GET
    @Path("/news/sortedClicks")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allSortedClicks(Integer page) {return this.publicService.allNewsSortedClicks();}
    @POST
    @Path("/news/incrementClicks")
    @Produces(MediaType.APPLICATION_JSON)
    public void incrementClicks(@Valid Id id){this.publicService.incrementClicks(id);}
    //kategorije
    @GET
    @Path("/kategorije")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getKategorije(@QueryParam("page") Integer page){
        return this.publicService.getKategorije(page);
    }
    @GET
    @Path("/kategorijeNames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getKategorijeNames(){
        return this.publicService.getKategorije();
    }

    @GET
    @Path("/kategorije/max")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer maxKategorije(){
        return this.publicService.maxKategorije();
    }
}
