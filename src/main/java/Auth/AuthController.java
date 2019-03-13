package Auth;

import model.logic.User;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("auth")
public class AuthController {

    @EJB
    private UserEJB repo;

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public void create() {
        User u = new User("testAuthUser", "abc");
        repo.createUser(u);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public void getById(@PathParam("id") Long id) {
        User u = repo.findUserById(id);
    }
}
