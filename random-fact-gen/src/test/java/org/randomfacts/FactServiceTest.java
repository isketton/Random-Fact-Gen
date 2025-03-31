package org.randomfacts;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

public @QuarkusTest
class FactServiceTest {

    @Test
    public void testGetFactsEndpoint() {
        RestAssured.given()
                .queryParam("limit", 1)
                .when()
                .get("/facts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", greaterThan(0))
                .body("[0].fact", notNullValue());
    }
} 
