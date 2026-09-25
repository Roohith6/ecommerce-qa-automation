package com.qa.automation.tests.api;

import com.qa.automation.base.ApiBaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class AuthApiTest extends ApiBaseTest {

    @Test(description = "API-01 POST /auth with valid credentials returns a non-empty token")
    public void testValidAuth() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", System.getProperty("api.password", System.getenv().getOrDefault("API_PASSWORD", "password123")));

        String token = RestAssured.given()
                .spec(requestSpec)
                .body(credentials)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
        
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test(description = "API-02 POST /auth with invalid credentials is rejected")
    public void testInvalidAuth() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "invalid");
        credentials.put("password", "wrong");

        String reason = RestAssured.given()
                .spec(requestSpec)
                .body(credentials)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("reason");
        
        Assert.assertEquals(reason, "Bad credentials", "Should return Bad credentials reason");
    }
}
