package ch.zli.m223.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.Benutzer;
import ch.zli.m223.model.Login;
import ch.zli.m223.exceptions.InvalidLoginException;
import ch.zli.m223.service.BenutzerService;


@Path("/")
public class AuthOController {

    @Inject
    private BenutzerService benutzerService;

    @POST
    @Path("/register")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Benutzer benutzer) {
        try {
            String token = benutzerService.register(benutzer);
            return Response.ok(token).build();
        } catch (EntityExistsException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } 
    }

    @POST
    @Path("/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try {
            String token = benutzerService.login(login);
            return Response.ok(token).build();
        } catch (InvalidLoginException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
