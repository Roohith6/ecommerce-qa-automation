package com.qa.automation.utils;

public class TestData {
    public static final String STANDARD_USER = "standard_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    
    // Demonstrate secure handling by checking system properties/env variables first
    public static final String PASSWORD = System.getProperty("ui.password", System.getenv().getOrDefault("UI_PASSWORD", "secret_sauce"));
    
    public static final String PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String PRODUCT_BIKELIGHT = "Sauce Labs Bike Light";
    
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String POSTAL_CODE = "12345";
}
