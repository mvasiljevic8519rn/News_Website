package rs.raf.demo.resources;

import rs.raf.demo.entities.User;
import rs.raf.demo.requests.LoginRequest;
import rs.raf.demo.services.MyUserService;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class MyUserResource {
    @Inject
    private MyUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers(){return  this.userService.allUsers();}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Integer id){return this.userService.getUser(id);}
    @GET
    @Path("checkEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkEmail( @QueryParam("email") String email){return this.userService.checkEmail(email);}
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public void changeActive(User user){this.userService.addUser(user);}
    @POST
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void changeActive(@PathParam("id") Integer id){this.userService.activateDeactivate(id);}
    @POST
    @Path("edit/")
    @Produces({MediaType.APPLICATION_JSON})
    public User editUser(User user){return this.userService.editUser(user);}
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

}
