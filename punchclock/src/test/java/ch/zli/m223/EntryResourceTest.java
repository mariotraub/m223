package ch.zli.m223;

import ch.zli.m223.model.Entry;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class EntryResourceTest {
    @Inject
    EntityManager em;
    private Entry entry;
    private LocalDateTime checkin;
    private LocalDateTime checkout;

    @BeforeEach
    public void setup() {
        entry = new Entry();
        checkin = LocalDateTime.of(2025, 10, 30, 8, 0);
        checkout = LocalDateTime.of(2025, 10, 30, 16, 0);
        entry.setCheckIn(checkin);
        entry.setCheckOut(checkout);
    }

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testCreateEntryEndpoint() {
        Entry actual = given()
                .body(entry).header("Content-Type", "application/json")
                .when().post("/entries")
                .then().statusCode(200)
                .extract().body().as(Entry.class);

        assertThat(actual.getId(), notNullValue());
        actual.setId(null);
        assertThat(actual, equalTo(entry));
    }

    @Test
    public void testDeleteEntryEndpoint() {
        persistEntry(entry);
        Long id = entry.getId();

        given()
                .when().delete("/entries/" + id)
                .then().statusCode(204).body(is(""));
    }

    @Test
    public void tryToDeleteEntryThatNotExists() {
        long id = -777L;
        given()
                .when().delete("/entries/" + id)
                .then().statusCode(404);
    }

    @Transactional
    void persistEntry(Entry e) {
        em.persist(e);
    }

}