package org.randomfacts;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class FactResourceTest {
    @Test
    public void testGetFactsEndpoint() {
        RestAssured.given()
          .when().get("/facts?limit=5")
          .then()
             .statusCode(200)
             .body("$.size()", greaterThanOrEqualTo(0));
    }

}