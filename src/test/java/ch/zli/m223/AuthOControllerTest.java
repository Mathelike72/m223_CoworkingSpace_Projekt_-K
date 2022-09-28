package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.exceptions.InvalidValueException;
import ch.zli.m223.model.Benutzer;
import ch.zli.m223.model.Login;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AuthOControllerTest {

    @Test
    public void unsuccessfulLogin() {
        Login invalidLogin = new Login();

        invalidLogin.setEmail("invalid@invalid.invalid");
        invalidLogin.setPassword("topsecreat");

        given()
                .contentType(ContentType.JSON)
                .body(invalidLogin)
                .when().post("/login")
                .then()
                .statusCode(400);
    }

    @Test
    public void successfulLogin() {
        Login successfulLogin = new Login();

        successfulLogin.setEmail("meinemail@bosshart.com");
        successfulLogin.setPassword("1QWrfIEgoq");

        given()
                .contentType(ContentType.JSON)
                .body(successfulLogin)
                .when().post("/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void unsuccessfulRegister() {
        Benutzer invalidUser = new Benutzer();

        given()
                .contentType(ContentType.JSON)
                .body(invalidUser)
                .when().post("/register")
                .then()
                .statusCode(400);
    }

    @Test
    public void successfulRegister() {
        Benutzer validBenutzer = new Benutzer();

        validBenutzer.setAdmin(true);
        try {
            validBenutzer.setEMail("email@email.co");
        } catch (InvalidValueException e) {

        }
        validBenutzer.setFirstName("deine");
        validBenutzer.setSecoundName("mutter");
        validBenutzer.setPassword("weilicheskann");

        given()
                .contentType(ContentType.JSON)
                .body(validBenutzer)
                .when().post("/register")
                .then()
                .statusCode(200);
    }
}