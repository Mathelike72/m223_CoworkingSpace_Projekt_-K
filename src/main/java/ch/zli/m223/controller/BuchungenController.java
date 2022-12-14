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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.zli.m223.exceptions.NullValueException;
import ch.zli.m223.model.Buchungen;
import ch.zli.m223.service.BuchungenService;

@Path("/buchungen")
public class BuchungenController {
    @Inject
    BuchungenService buchungenService;

    // Hier befinden sich alle POST Requests
    @POST
    @RolesAllowed({"Benutzer", "Admin"})
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
    @RolesAllowed({"Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Buchungen> index() {
        return buchungenService.findAll();
    }

    @Path("/review/{id}/{accept}")
    @GET
    @RolesAllowed({"Admin"})
    public Response changeBuchungenStatus(Long id, Boolean status) {
        Buchungen buchungen = buchungenService.getBuchungen(id);
        buchungen.setStatus(status);
        buchungenService.updateBuchungen(buchungen);
        return Response.ok().build();
    }

    // Hier befinden sich alle PUT Requests
    @PUT
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin"})
    public Response cancelBuchungen(Long id, @Context SecurityContext ctx) {
        
        Long benutzerId = null;
        if (ctx.isUserInRole("Benutzer")) {
            benutzerId = Long.parseLong(ctx.getUserPrincipal().getName());
        }

        Buchungen Buchungen = buchungenService.getBuchungen(id);

        if (benutzerId == null ? Buchungen == null : Buchungen == null || (Buchungen.getBenutzer()).getId() != benutzerId)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Buchungen.setStatus(false);

        try {
            buchungenService.updateBuchungen(Buchungen);
            return Response.ok().build();
        } catch (TransactionRequiredException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Hier befinden sich alle DELETE Requests
    @Path("/{id}")
    @DELETE
    @RolesAllowed({"Admin"})
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
