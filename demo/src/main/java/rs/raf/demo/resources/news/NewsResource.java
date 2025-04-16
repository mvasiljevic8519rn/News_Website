package rs.raf.demo.resources.news;


import rs.raf.demo.entities.Id;
import rs.raf.demo.entities.News;
import rs.raf.demo.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {
    @Inject
    private NewsService newsService;
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> all(@QueryParam("page") Integer page) {return this.newsService.allNews(page);}

    @GET
    @Path("/max")
    @Produces({MediaType.APPLICATION_JSON})
    public Integer max() {return this.newsService.max();}
    @GET
    @Path("/inKategorija/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allInKategorija(@PathParam("id") int id) {return this.newsService.allNewsinKagtegorija(id);}
    @GET
    @Path("/withTag/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allWithTag(@PathParam("name") String name) {return this.newsService.allNewsWithTag(name);}

    //treba da bude public
    @GET
    @Path("/sortedDate")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allSortedDate( Integer page) {return this.newsService.allNewsSortedDate();}
    @GET
    @Path("/sortedClicks")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allSortedClicks(Integer page) {return this.newsService.allNewsSortedClicks();}
    @POST
    @Path("/incrementClicks")
    @Produces(MediaType.APPLICATION_JSON)
    public void incrementClicks(@Valid Id id){this.newsService.incrementClicks(id);}
    //treba da bude public

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(@Valid News news){return this.newsService.addNews(news);}
    @POST
    @Path("/replace")
    @Produces(MediaType.APPLICATION_JSON)
    public News replace(@Valid News news){return this.newsService.replace(news);}
    @GET
    @Path("/topTen")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<News> topTenAll(){return  this.newsService.topTen();}
    @GET
    @Path("with{in}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<News> allWithString(@PathParam("in") String in) {return this.newsService.News_withWord(in);}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  News find(@PathParam("id") int id){
        return this.newsService.findNews(id);
    }
    @DELETE
    @Path("/{id}")
    public Integer delete(@PathParam("id") int id){return this.newsService.deleteNews(id);}

}
