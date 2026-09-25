package com.qa.automation.base;

import com.qa.automation.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        // Disable password manager
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");
        
        String headless = System.getProperty("headless", "false");
        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
        }

        WebDriver driver = new ChromeDriver(options);
        driverLocal.set(driver);
        
        if (!Boolean.parseBoolean(headless)) {
            driver.manage().window().maximize();
        }

        int timeout = Integer.parseInt(ConfigReader.getProperty("timeoutSeconds"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverLocal.remove();
        }
    }

    public static WebDriver getDriver() {
        return driverLocal.get();
    }
}
