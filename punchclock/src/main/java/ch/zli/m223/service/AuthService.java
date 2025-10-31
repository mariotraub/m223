package ch.zli.m223.service;

import ch.zli.m223.model.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;
import io.smallrye.jwt.build.Jwt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

@ApplicationScoped
public class AuthService {
    @Inject
    EntityManager em;

    public Response generateToken(String username, String password) {
        try {
            User user = findUserByName(username);

            assert Objects.equals(user.getPassword(), BcryptUtil.bcryptHash(password));

            String token =
                    Jwt.upn(user.getName()).issuer("https://example.com/issuer")
                            .groups(user.getRoles())
                            .sign();

            return Response
                    .status(Response.Status.CREATED)
                    .header("Authorization", "Bearer ".concat(token))
                    .build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public User findUserByName(String username) {
        return em.createNamedQuery("findByUsername", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
