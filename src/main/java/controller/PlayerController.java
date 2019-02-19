package controller;

import model.Services.PlayerService;
import model.logic.Player;
import model.logic.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("player")
public class PlayerController {

    @EJB
    PlayerService playerService;

    @GET
    @Consumes("application/json")
    public List<User> allUsers(){
        return playerService.allUsers();
    }

    @POST
    @Consumes("application/json")
    public void createPlayer(Player player){
        playerService.createPlayer(player);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public Player getById(@PathParam("id") Long id){
        return playerService.getById(id);
    }
}
