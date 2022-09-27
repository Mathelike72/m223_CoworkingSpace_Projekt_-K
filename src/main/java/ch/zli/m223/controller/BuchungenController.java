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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Buchungen> index() {
        return buchungenService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Buchungen buchungen) {
        try {
            Buchungen createdBuchungen = buchungenService.createBuchungen(buchungen);
            return Response.ok(createdBuchungen).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBooking(Buchungen buchungen) {
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

    @Path("/{id}")
    @DELETE
    public Response deleteBooking(Long id) {
        try {
            buchungenService.deleteBuchungen(id);
            return Response.noContent().build();
        } catch (NullValueException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/review/{id}/{accept}")
    @GET
    public Response changeBuchungenStatus(Long id, Boolean status) {
        Buchungen buchungen = buchungenService.getBuchungen(id);
        buchungen.setIsAccepted(status);
        buchungenService.updateBuchungen(buchungen);
        return Response.ok().build();
    }

    @Path("/cancel/{id}")
    @GET
    public Response cancelBooking(Long id) {
        //TODO: get user id through claim if not admin
        Buchungen buchungen = buchungenService.getBuchungen(id);
        buchungen.setIsAccepted(false);
        buchungenService.updateBuchungen(buchungen);
        return Response.ok().build();
    }
}
