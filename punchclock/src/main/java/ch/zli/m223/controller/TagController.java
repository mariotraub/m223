package ch.zli.m223.controller;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;

@Path("/tags")
@org.eclipse.microprofile.openapi.annotations.tags.Tag(name = "Tags", description = "Handling of tags")
@RolesAllowed({"User", "Admin"})
public class TagController {

    @Inject
    TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Tags.", description = "Returns a list of all tags.")
    public List<Tag> index() {
        return tagService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new tag.", description = "Creates a new tag and returns the newly added tag.")
    public Tag create(Tag tag) {
       return tagService.createTag(tag);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a tag.", description = "Deletes an existing tag.")
    public void delete(@PathParam("id") long id) {
        tagService.deleteTag(id);
    }

    @PUT
    @Operation(summary = "Update a tag.", description = "Updates an existing tag.")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Tag update(Tag tag) {
        return tagService.updateTag(tag);
    }
}
