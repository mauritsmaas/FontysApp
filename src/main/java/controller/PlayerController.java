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
    public List<Player> All(){
        return playerService.All();
    }

    @GET
    @Path("/club/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Player> getPlayersByClubId(@PathParam("id") Long id){
        return playerService.getPlayersByClubId(id);
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

    @PUT
    @Consumes("application/json")
    public void update(Player player){playerService.update(player);}
}
