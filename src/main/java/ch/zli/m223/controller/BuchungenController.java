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
import ch.zli.m223.model.Buchungen;
import ch.zli.m223.service.BuchungenService;

@Path("/buchungen")
public class BuchungenController {
    @Inject
    BuchungenService buchungenService;

    // Hier befinden sich alle POST Requests
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Buchungen buchungen) throws Exception {
        try {
            Buchungen createdBuchungen = buchungenService.createBuchungen(buchungen);
            return Response.ok(createdBuchungen).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Hier befinden sich alle GET Requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Buchungen> index() {
        return buchungenService.findAll();
    }

    @Path("/review/{id}/{accept}")
    @GET
    public Response changeBuchungenStatus(Long id, Boolean status) {
        Buchungen buchungen = buchungenService.getBuchungen(id);
        buchungen.setStatus(status);
        buchungenService.updateBuchungen(buchungen);
        return Response.ok().build();
    }

    // Hier befinden sich alle PUT Requests
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBuchungen(Buchungen buchungen) throws IllegalArgumentException, TransactionRequiredException {
        try {
            return Response.ok(buchungenService.updateBuchungen(buchungen)).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (TransactionRequiredException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/ablehnen/{id}")
    @PUT
    public Response cancelBuchungen(Long id) {
        //TODO: get user id through claim if not admin
        Buchungen buchungen = buchungenService.getBuchungen(id);
        buchungen.setStatus(false);
        buchungenService.updateBuchungen(buchungen);
        return Response.ok().build();
    }

    // Hier befinden sich alle DELETE Requests
    @Path("/{id}")
    @DELETE
    public Response deleteBooking(Long id) throws NullValueException, IllegalArgumentException {
        if (id < 0 || id == null) {
            throw new NullValueException("Keine Buchung mit der id: " + id + " wurde gefunden");
        }
        try {
            buchungenService.deleteBuchungen(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
