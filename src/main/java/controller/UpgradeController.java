package controller;

import model.Services.UpgradeService;
import model.logic.Upgrade;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("upgrade")
public class UpgradeController {

    @EJB
    UpgradeService upgradeService;

    @GET
    @Consumes("application/json")
    public List<Upgrade> All(){
        return upgradeService.All();
    }

    @POST
    @Consumes("application/json")
    public void create(Upgrade upgrade){
        upgradeService.create(upgrade);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public Upgrade getById(@PathParam("id") Long id){
        return upgradeService.getById(id);
    }

    @PUT
    @Consumes("application/json")
    public void update(Upgrade upgrade){
        upgradeService.update(upgrade);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id){
        upgradeService.delete(id);
    }
}
