package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.zli.m223.exceptions.NullValueException;
import ch.zli.m223.model.Benutzer;
import ch.zli.m223.service.BenutzerService;

@Path("/users")
public class BenutzerController {

    @Inject
    BenutzerService benutzerService;

    // Hier befinden sich alle POST Requests
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Benutzer benutzer) {
        try {
            Benutzer createdBenutzer = benutzerService.createBenutzer(benutzer);
            return Response.ok(createdBenutzer).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Hier befinden sich alle GET Requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Benutzer> index() {
        return benutzerService.findAll();
    }

    // Hier befinden sich alle PUT Requests
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBenutzer(Benutzer benutzer) {
        try {
            return Response.ok(benutzerService.updateBenutzer(benutzer)).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (TransactionRequiredException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Hier befinden sich alle DELETE Requests
    @Path("/{id}")
    @DELETE
    public Response deleteBenutzer(Long id) {
        try {
            benutzerService.deleteBenutzer(id);
            return Response.noContent().build();
        } catch (NullValueException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}