package com.qa.automation.base;

import com.qa.automation.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class ApiBaseTest {
    protected RequestSpecification requestSpec;

    @BeforeClass(alwaysRun = true)
    public void setupApi() {
        RestAssured.baseURI = ConfigReader.getProperty("apiBaseUrl");
        RestAssured.config = RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
        
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    protected String getToken(String username, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        return RestAssured.given()
                .spec(requestSpec)
                .body(credentials)
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    protected Map<String, Object> buildBookingPayload(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstname);
        booking.put("lastname", lastname);
        booking.put("totalprice", totalprice);
        booking.put("depositpaid", depositpaid);
        
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);
        booking.put("bookingdates", bookingdates);
        
        booking.put("additionalneeds", additionalneeds);
        return booking;
    }
}
