package com.qa.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    private WebDriverWait wait;

    private By title = By.cssSelector(".title");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By cartLink = By.cssSelector(".shopping_cart_link");
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
    }

    public boolean isTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).isDisplayed();
    }

    public void addProductToCart(String productName) {
        String addBtnLocator = String.format("[data-test='add-to-cart-%s']", productName.toLowerCase().replace(" ", "-"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(addBtnLocator))).click();
    }

    public void removeProductFromCart(String productName) {
        String removeBtnLocator = String.format("[data-test='remove-%s']", productName.toLowerCase().replace(" ", "-"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(removeBtnLocator))).click();
    }

    public String getCartBadgeText() {
        try {
            WebElement badge = wait.until(ExpectedConditions.presenceOfElementLocated(cartBadge));
            return badge.getText();
        } catch (org.openqa.selenium.TimeoutException e) {
            return "0";
        }
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
