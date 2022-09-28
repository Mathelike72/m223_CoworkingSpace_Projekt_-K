package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import ch.zli.m223.exceptions.InvalidValueException;
import ch.zli.m223.model.Benutzer;
import ch.zli.m223.service.TokenService;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BenutzerControllerTest {
    @Test
    public void getAllBenutzerUnauthorisedWithoutToken() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/benutzer")
                .then()
                .statusCode(401);
    }

    @Test
    public void getAllUsersWorks() {

        Benutzer benutzer = new Benutzer();

        try {
            benutzer.setEMail("meinemail@bosshart.com");
        } catch (InvalidValueException e) {

        }
        benutzer.setPassword("sdf32xbjrsa!");
        benutzer.setId(1L);
        benutzer.setAdmin(true);

        String token = new TokenService().createToken(benutzer);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(benutzer)
                .when().get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void unauthorizedDeleteWhenNoToken() {
        given()
                .contentType(ContentType.JSON)
                .when().delete("/benutzer/1")
                .then()
                .statusCode(401);
    }

    @Test
    public void deleteWorks() {
        Benutzer benutzer = new Benutzer();

        try {
            benutzer.setEMail("meinmail@bosshart.com");
        } catch (InvalidValueException e) {

        }
        benutzer.setPassword("sajhfo23sd!");
        benutzer.setId(1L);
        benutzer.setAdmin(true);

        String token = new TokenService().createToken(benutzer);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().delete("/benutzer/1")
                .then()
                .statusCode(204);
    }
}