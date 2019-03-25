package controller;

import model.Services.ClubService;
import model.logic.Club;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("club")
public class ClubController {

    @EJB
    ClubService clubService;

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Club> allClubs(){
        return clubService.all();
    }

    @POST
    @Consumes("application/json")
    public void createClub(Club club){
        clubService.createClub(club);
    }

    @GET
    @Path("/user/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Club getClubByUserId(@PathParam("id") Long id){
        return clubService.getClubByUserId(id);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Club getById(@PathParam("id") Long id){
        return clubService.getById(id);
    }

    @PUT
    @Consumes("application/json")
    public void update(Club club){
        clubService.update(club);
    }

    @GET
    @Path("/{clubid}/contract/{playerid}")
    @Consumes("application/json")
    public void contractPlayer(@PathParam("clubid") Long clubid, @PathParam("playerid") Long playerid){
        clubService.contract(clubid,playerid);
    }
}
