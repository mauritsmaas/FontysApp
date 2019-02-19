package controller;

import model.facade.DatabaseFacade;
import model.logic.User;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.util.List;

@Path("/MyRestService")
@ApplicationPath("/rest")
public class RestService extends Application {

    @EJB
    DatabaseFacade databaseFacade;

    @GET
    @Path("/sayHello")
    @Consumes("application/json")
    public List<User> helloMessage(){
        return databaseFacade.allUsers();
    }
}
