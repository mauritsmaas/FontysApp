package controller;

import model.Services.MatchService;
import model.logic.Match;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("match")
public class MatchController {

    @EJB
    MatchService matchService;

    @GET
    @Consumes("application/json")
    public List<Match> All(){
        return matchService.All();
    }

    @POST
    @Consumes("application/json")
    public void create(Match match){
        matchService.create(match);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public Match getById(@PathParam("id") Long id){
        return matchService.getById(id);
    }

    @PUT
    @Consumes("application/json")
    public void update(Match match){
        matchService.update(match);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id){
        matchService.delete(id);
    }
}
