package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Benutzer;
import ch.zli.m223.security.jwt.TokenSecuredResource;
import ch.zli.m223.service.BenutzerService;

public class AuthOController {
    @Inject
    BenutzerService benutzerService;

    @Inject
    TokenSecuredResource tokenResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index alle Benutzer.", description = "Gibt eine liste aller Benutzer zurück.")
    public List<Benutzer> index() {
        return benutzerService.findAll();
    }
}
