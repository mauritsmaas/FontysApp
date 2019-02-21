package controller;

import model.Services.MatchSetupService;
import model.logic.MatchSetup;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("matchSetup")
public class MatchSetupController {

    @EJB
    MatchSetupService matchSetupService;

    @GET
    @Consumes("application/json")
    public List<MatchSetup> All(){
        return matchSetupService.All();
    }

    @POST
    @Consumes("application/json")
    public void create(MatchSetup matchSetup){
        matchSetupService.create(matchSetup);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public MatchSetup getById(@PathParam("id") Long id){
        return matchSetupService.getById(id);
    }

    @PUT
    @Consumes("application/json")
    public void update(MatchSetup matchSetup){
        matchSetupService.update(matchSetup);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id){
        matchSetupService.delete(id);
    }
}
