package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    //this fails as the response from the endpoint called by the api internally is returned
    void testSelfCall() {
        given()
          .when()
                .accept(MediaType.TEXT_PLAIN)
                .get("/hello/self-call")

          .then()
             .statusCode(200);
    }

    @Test
    //this behaves as expected
    void testGoogleCall() {
        given()
                .when()
                .accept(MediaType.TEXT_PLAIN)
                .get("/hello/google-call")

                .then()
                .statusCode(402);
    }
}