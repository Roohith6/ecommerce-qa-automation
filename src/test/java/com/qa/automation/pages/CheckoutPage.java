package com.qa.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.cssSelector("[data-test='complete-header']");
    private By itemPrice = By.cssSelector(".inventory_item_price");
    private By subtotalLabel = By.cssSelector(".summary_subtotal_label");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public double getCalculatedItemTotal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));
        List<WebElement> prices = driver.findElements(itemPrice);
        double total = 0;
        for (WebElement price : prices) {
            String p = price.getText().replace("$", "");
            total += Double.parseDouble(p);
        }
        return total;
    }

    public double getDisplayedSubtotal() {
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalLabel)).getText();
        return Double.parseDouble(subtotalText.replace("Item total: $", ""));
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
