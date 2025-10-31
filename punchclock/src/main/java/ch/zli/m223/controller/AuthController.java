package ch.zli.m223.controller;

import ch.zli.m223.dto.Credentials;
import ch.zli.m223.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@PermitAll
public class AuthController {
    @Inject
    AuthService authService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Credentials credentials) {
        return authService.generateToken(credentials.username(), credentials.password());
    }
}
