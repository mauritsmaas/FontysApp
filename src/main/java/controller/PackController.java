package controller;

import model.Services.PackService;
import model.logic.Pack;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("pack")
public class PackController {

    @EJB
    PackService packService;

    @GET
    @Consumes("application/json")
    public List<Pack> All(){
        return packService.All();
    }

    @POST
    @Consumes("application/json")
    public void create(Pack pack){
        packService.create(pack);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public Pack getById(@PathParam("id") Long id){
        return packService.getById(id);
    }

    @PUT
    @Consumes("application/json")
    public void update(Pack pack){
        packService.update(pack);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id){
        packService.delete(id);
    }
}
