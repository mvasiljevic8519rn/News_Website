package rs.raf.demo.resources.kategorije;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.services.KategorijeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/kategorije")
public class KategorijeResource {
    @Inject
    private KategorijeService kategorijeService;

//public
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getKategorije(@QueryParam("page") Integer page){
        return this.kategorijeService.getKategorije(page);
    }
    //public
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getKategorije(){
        return this.kategorijeService.getKategorije();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija create(/*@Valid*/ Kategorija kategorija) {
        return this.kategorijeService.addKategorija(kategorija);
    }
    @POST
    @Path("/replace")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija replace(/*@Valid*/ Kategorija kategorija) {
        return this.kategorijeService.replaceKategorija(kategorija);
    }
    //public
    @GET
    @Path("/max")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer max(){
        return this.kategorijeService.max();
    }
    //public
    @GET
    @Path("/allNames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> all(){
        return  this.kategorijeService.getAllKategorijaNames();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija getKategorija(@PathParam("id") Integer id){
        return  this.kategorijeService.getKategorija(id);}

    @GET
    @Path("/delete")
    public Integer delete(@QueryParam("id") Integer id){return this.kategorijeService.delteKategorija(id);}



}
