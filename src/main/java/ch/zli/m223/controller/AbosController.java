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
import ch.zli.m223.model.Abos;
import ch.zli.m223.service.AbosService;

@Path("/abos")
public class AbosController {
    @Inject
    AbosService abosService;

    // Hier befinden sich alle POST Requests
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Abos abos) throws Exception {
        try {
            Abos createdAbos = abosService.createAbos(abos);
            return Response.ok(createdAbos).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Hier befinden sich alle GET Requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Abos> index() {
        return abosService.findAll();
    }

    // Hier befinden sich alle PUT Requests
    @Path("/status/{id}/{status}")
    @PUT
    public Response changeAbosStatus(Long id, Boolean status) {
        Abos abos = abosService.getAbos(id);
        abos.setStatus(status);
        abosService.updateAbos(abos);
        return Response.ok().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAbos(Abos abos) throws IllegalArgumentException, TransactionRequiredException {
        try {
            return Response.ok(abosService.updateAbos(abos)).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (TransactionRequiredException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    // Hier befinden sich alle DELETE Requests
    @DELETE
    public Response deleteAbos(Long id) throws NullValueException {
        if (id < 0 || id == null) {
            throw new NullValueException("Kein Abo mit der id: " + id + " wurde gefunden");
        }
        try {
            abosService.deleteAbos(id);
            return Response.noContent().build();
        } catch (NullValueException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
