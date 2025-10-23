package ch.zli.m223;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/calc")
public class CalcResource {
    @Path("/add/{first}/{second}")
    @GET
    public int add(@PathParam("first") int first,  @PathParam("second") int second) {
        return first + second;
    }
}
