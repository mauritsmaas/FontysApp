package controller;


import model.Services.AuthService;
import model.logic.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthController {
    @EJB
    private AuthService service;

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(User user) {
        return Response.ok(service.register(user)).build();
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(User user) {
        return Response.ok(service.login(user.getEmail(), user.getPassword())).build();
    }

    @POST
    @Path("login/code")
    @Consumes("application/json")
    @Produces("application/json")
    public Response verifyUserWithCode(User user) {
        return Response.ok(service.checkCode(user)).build();
    }
}

