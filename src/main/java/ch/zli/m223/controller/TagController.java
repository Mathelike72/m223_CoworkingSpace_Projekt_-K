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

import ch.zli.m223.model.Tag;

import ch.zli.m223.service.TagService;

@Path("/tags")
@org.eclipse.microprofile.openapi.annotations.tags.Tag(name = "Tags", description = "Handling of tags")
public class TagController {

    @Inject
    TagService TagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> index() {
        return TagService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Tag create(Tag tag) {
       return TagService.createTag(tag);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id")Long id) {
        TagService.deleteTag(id);
    }

    @PUT
    @Path("/update/{id}")
    public Tag update(@PathParam("id") Long id, Tag tag) {
        return TagService.updateTag(id, tag);
    }

}

