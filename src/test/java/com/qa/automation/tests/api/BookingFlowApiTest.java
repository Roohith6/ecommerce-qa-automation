package com.qa.automation.tests.api;

import com.qa.automation.base.ApiBaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

public class BookingFlowApiTest extends ApiBaseTest {

    @Test(description = "API-11 Chained flow: create, get, put, patch, delete, get fail")
    public void testChainedBookingFlow() {
        // 1. Create Booking
        Map<String, Object> payload = buildBookingPayload("Test", "User", 150, true, "2024-01-01", "2024-01-05", "Breakfast");
        
        int bookingId = RestAssured.given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");
        
        Assert.assertTrue(bookingId > 0, "Booking ID should be valid");

        // 2. Extract Token (Using system properties for secure credential handling)
        String apiPassword = System.getProperty("api.password", System.getenv().getOrDefault("API_PASSWORD", "password123"));
        String token = getToken("admin", apiPassword);

        // 3. GET and verify
        String firstname = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .path("firstname");
        Assert.assertEquals(firstname, "Test");

        // 4. PUT update and verify
        Map<String, Object> updatePayload = buildBookingPayload("UpdatedTest", "User", 200, false, "2024-01-01", "2024-01-05", "None");
        RestAssured.given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .body(updatePayload)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200);

        // 5. PATCH partial update and verify
        RestAssured.given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .body("{\"firstname\": \"PatchedTest\"}")
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .statusCode(200);

        // 6. DELETE
        RestAssured.given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201); // Note: API returns 201 for delete

        // 7. GET and confirm fail
        RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404);
    }
}
