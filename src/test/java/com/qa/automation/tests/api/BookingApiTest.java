package com.qa.automation.tests.api;

import com.qa.automation.base.ApiBaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BookingApiTest extends ApiBaseTest {

    @Test(description = "API-03 GET /booking returns a list and each item has a bookingid")
    public void testGetBookings() {
        List<Integer> bookingIds = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("bookingid");

        Assert.assertFalse(bookingIds.isEmpty(), "Booking list should not be empty");
        Assert.assertNotNull(bookingIds.get(0), "First booking ID should not be null");
    }

    @Test(description = "API-04 GET /booking/{id} returns the expected JSON fields")
    public void testGetBookingById() {
        // First get a valid booking ID
        int bookingId = RestAssured.given().spec(requestSpec).get("/booking").then().extract().path("[0].bookingid");
        
        RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", org.hamcrest.Matchers.notNullValue())
                .body("lastname", org.hamcrest.Matchers.notNullValue())
                .body("totalprice", org.hamcrest.Matchers.notNullValue())
                .body("depositpaid", org.hamcrest.Matchers.notNullValue())
                .body("bookingdates.checkin", org.hamcrest.Matchers.notNullValue())
                .body("bookingdates.checkout", org.hamcrest.Matchers.notNullValue());
    }

    @Test(description = "API-05 GET a non-existent booking id returns 404")
    public void testGetNonExistentBooking() {
        RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/booking/999999999")
                .then()
                .statusCode(404);
    }

    @Test(description = "API-06 POST /booking creates a booking")
    public void testCreateBooking() {
        java.util.Map<String, Object> payload = buildBookingPayload("John", "Doe", 111, true, "2024-01-01", "2024-01-05", "None");
        
        RestAssured.given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", org.hamcrest.Matchers.notNullValue())
                .body("booking.firstname", org.hamcrest.Matchers.equalTo("John"));
    }

    @Test(description = "API-07 PUT /booking/{id} with a valid token updates the booking")
    public void testUpdateBookingWithToken() {
        // Create booking first
        java.util.Map<String, Object> payload = buildBookingPayload("Initial", "Name", 100, true, "2024-01-01", "2024-01-05", "None");
        int bookingId = RestAssured.given().spec(requestSpec).body(payload).post("/booking").then().extract().path("bookingid");
        String token = getToken("admin", "password123");

        java.util.Map<String, Object> updatedPayload = buildBookingPayload("Updated", "Name", 100, true, "2024-01-01", "2024-01-05", "None");

        RestAssured.given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .body(updatedPayload)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", org.hamcrest.Matchers.equalTo("Updated"));
    }

    @Test(description = "API-08 PUT /booking/{id} without a token is rejected")
    public void testUpdateBookingWithoutToken() {
        java.util.Map<String, Object> payload = buildBookingPayload("Updated", "Name", 100, true, "2024-01-01", "2024-01-05", "None");
        
        RestAssured.given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .put("/booking/1")
                .then()
                .statusCode(403);
    }

    @Test(description = "API-09 DELETE /booking/{id} with a token succeeds")
    public void testDeleteBooking() {
        java.util.Map<String, Object> payload = buildBookingPayload("ToDelete", "User", 100, true, "2024-01-01", "2024-01-05", "None");
        int bookingId = RestAssured.given().spec(requestSpec).body(payload).post("/booking").then().extract().path("bookingid");
        String token = getToken("admin", "password123");

        RestAssured.given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
                
        RestAssured.given().spec(requestSpec).get("/booking/" + bookingId).then().statusCode(404);
    }

    @Test(description = "API-10 POST /booking with an invalid body is rejected")
    public void testCreateBookingInvalidBody() {
        RestAssured.given()
                .spec(requestSpec)
                .body("{\"invalid\": \"data\"}")
                .when()
                .post("/booking")
                .then()
                .statusCode(500); // Known quirk: Server returns 500 for invalid body
    }
}
