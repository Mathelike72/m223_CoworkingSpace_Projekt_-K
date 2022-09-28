package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import ch.zli.m223.model.Plaetze;
import ch.zli.m223.service.PlaetzeService;

@Path("/plaetze")
public class PlaetzeController {
    @Inject
    PlaetzeService plaetzeService;

    // Hier befinden sich alle POST Requests
    @POST
    @RolesAllowed({"Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Plaetze plaetze) throws Exception {
        try {
            Plaetze createdPlaetze = plaetzeService.createPlaetze(plaetze);
            return Response.ok(createdPlaetze).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Hier befinden sich alle GET Requests
    @GET
    @RolesAllowed({"Benutzer", "Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plaetze> index() {
        return plaetzeService.findAll();
    }

    // Hier befinden sich alle PUT Requests
    @PUT
    @RolesAllowed({"Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaetze(Plaetze plaetze) throws IllegalArgumentException, TransactionRequiredException {
        try {
            return Response.ok(plaetzeService.updatePlaetze(plaetze)).build();
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
    @RolesAllowed({"Admin"})
    public Response deletePlaetze(Long id) throws NullValueException {
        if (id < 0 || id == null) {
            throw new NullValueException("Kein Platz mit der id: " + id + " wurde gefunden");
        }
        try {
            plaetzeService.deletePlaetze(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
