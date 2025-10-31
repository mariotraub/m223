package ch.zli.m223.controller;

import ch.zli.m223.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthController {
    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login() {
        return authService.generateToken();
    }
}
