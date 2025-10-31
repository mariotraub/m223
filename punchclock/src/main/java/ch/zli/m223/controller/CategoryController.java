package ch.zli.m223.controller;

import ch.zli.m223.model.Category;
import ch.zli.m223.service.CategoryService;
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
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/categories")
@Tag(name = "Categories", description = "Handling of categories")
@RolesAllowed({"User", "Admin"})
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> index() {
        return categoryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category create(Category category) {
        return categoryService.createCategory(category);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        categoryService.deleteCategory(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category update(Category category) {
        return categoryService.updateCategory(category);
    }
}
