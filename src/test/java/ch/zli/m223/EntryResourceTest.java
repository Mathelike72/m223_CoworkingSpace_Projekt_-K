package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEndpoint() {
        RestAssured.given().when().delete("http://127.0.0.1:8080/entries/delete/3").then().assertThat().statusCode(204);
    }


    @Test
    public void testUpdateEndpoint() {

        Entry entry = new Entry();
        Long testId = Integer.toUnsignedLong(4);
        entry.setId(testId);
        LocalDateTime testCheckIn = LocalDateTime.parse("2023-06-10T12:22:51"); 
        entry.setCheckIn(testCheckIn);
        LocalDateTime testCheckOut = LocalDateTime.parse("2021-06-10T12:23:15"); 
        entry.setCheckOut(testCheckOut);

        given().body(entry).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().put("http://127.0.0.1:8080/entries/update/4").then().statusCode(200);
    }

}