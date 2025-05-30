package rs.raf.demo.filters;

import rs.raf.demo.resources.MyUserResource;
import rs.raf.demo.resources.kategorije.KategorijeResource;
import rs.raf.demo.resources.news.NewsResource;
import rs.raf.demo.resources.subjtUser.SubjectResource;
import rs.raf.demo.resources.subjtUser.UserResource;
import rs.raf.demo.services.MyUserService;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    MyUserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            if (!this.userService.isAuthorized(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }

        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {

            if (matchedResource instanceof SubjectResource ) {return true;}

            if (matchedResource instanceof NewsResource) {return true;}

            if (matchedResource instanceof KategorijeResource) {return true;}

            if (matchedResource instanceof MyUserResource) {return true;}
//
           // if (matchedResource instanceof UserResource) {return true;}

        }

        return false;
    }
}
