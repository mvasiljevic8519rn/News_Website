package rs.raf.demo.resources.tag;

import rs.raf.demo.entities.Subject;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.services.TagService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tags")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> all(@PathParam("id") Integer id)
    {
        return this.tagService.allTagsFromNews(id);
    }
}
