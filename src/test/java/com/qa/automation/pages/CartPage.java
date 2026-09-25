package com.qa.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutButton = By.id("checkout");
    private By cartItem = By.cssSelector(".cart_item");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        return driver.findElements(cartItem).size();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
