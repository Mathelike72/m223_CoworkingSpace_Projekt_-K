package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import java.time.LocalDateTime;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@QuarkusTest
public class EntryResourceTest {

}