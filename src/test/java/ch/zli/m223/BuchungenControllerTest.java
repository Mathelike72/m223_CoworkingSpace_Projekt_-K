package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.exceptions.InvalidValueException;
import ch.zli.m223.model.Benutzer;
import ch.zli.m223.service.TokenService;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BuchungenControllerTest {

    @Test
    public void getAllBookingsUnauthorisedWithoutToken() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/buchungen")
                .then()
                .statusCode(401);
    }

    @Test
    public void getAllBookingsWorks() {

        Benutzer benutzer = new Benutzer();

        try {
            benutzer.setEMail("meinemail@bosshart.com");
        } catch (InvalidValueException e) {

        }
        benutzer.setPassword("1QWrfIEgoq");
        benutzer.setId(1L);
        benutzer.setAdmin(true);

        String token = new TokenService().createToken(benutzer);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(benutzer)
                .when().get("/bookings")
                .then()
                .statusCode(200);
    }

    @Test
    public void unauthorizedDeleteBuchungenWhenNoToken() {
        given()
                .contentType(ContentType.JSON)
                .when().delete("/buchungen/1")
                .then()
                .statusCode(401);
    }

    @Test
    public void deleteWorks() {
        Benutzer benutzer = new Benutzer();

        try {
            benutzer.setEMail("meinemail@bosshart.com");
        } catch (InvalidValueException e) {

        }
        benutzer.setPassword("yjsd2s9jw");
        benutzer.setId(1L);
        benutzer.setAdmin(true);

        String token = new TokenService().createToken(benutzer);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().delete("/buchungen/1")
                .then()
                .statusCode(204);
    }
}
