package controller;

import model.facade.DatabaseFacade;
import model.logic.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("player")
public class PlayerController {

    @EJB
    DatabaseFacade databaseFacade;

    @GET
    @Consumes("application/json")
    public List<User> allUsers(){
        return databaseFacade.allUsers();
    }

    @POST
    @Consumes("application/json")
    public void createPlayer(User user){
        databaseFacade.persistUser(user);
    }
}
