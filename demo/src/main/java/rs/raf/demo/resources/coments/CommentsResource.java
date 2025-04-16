package rs.raf.demo.resources.coments;

import rs.raf.demo.entities.Coment;
import rs.raf.demo.services.ComentsService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentsResource {
    @Inject
    private ComentsService comentsService;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Coment add(Coment comment){return  this.comentsService.addComent(comment);}
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coment> all(@PathParam("id") int id,@QueryParam("page") Integer page ){return  this.comentsService.allComentsFromNewsWithId(id,page);}
    @GET
    @Path("/max")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer max(@QueryParam("id") Integer id){return  this.comentsService.max(id);}
    @DELETE
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAll(@QueryParam("id") Integer id){this.comentsService.deleteAllComents(id);}

}
