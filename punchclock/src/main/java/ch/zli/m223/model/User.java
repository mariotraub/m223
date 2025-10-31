package ch.zli.m223.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "application_user")
@NamedQuery(name = "findByUsername", query = "SELECT u FROM User u WHERE u.name = :username")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Setter(AccessLevel.NONE)
    private String password;

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }
}
