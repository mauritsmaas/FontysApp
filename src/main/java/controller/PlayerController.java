package controller;

import model.Services.PlayerService;
import model.logic.Player;
import model.logic.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
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
    public Player getById(@PathParam("id") Long id, @Context UriInfo uriInfo){
        Player player = playerService.getById(id);
        player.addLink(getUriForSelf(uriInfo,player), "self", "GET");
        player.addLink(getUriForSelf(uriInfo,player), "self", "PUT");
        player.addLink(getUriForSelf(uriInfo,player), "self", "DELETE");
        return player;
    }

    @PUT
    @Consumes("application/json")
    public void update(Player player){playerService.update(player);}

    private String getUriForSelf(@Context UriInfo uriInfo, Player player){
        return uriInfo.getBaseUriBuilder()
                .path(PlayerController.class)
                .path(Long.toString(player.getId()))
                .build()
                .toString();
    }
}
