package com.qa.automation.tests.ui;

import com.qa.automation.base.BaseTest;
import com.qa.automation.pages.CartPage;
import com.qa.automation.pages.CheckoutPage;
import com.qa.automation.pages.LoginPage;
import com.qa.automation.pages.ProductsPage;
import com.qa.automation.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "UI-08 Add two products; on checkout, item total equals sum of item prices", groups = {"regression", "functional"})
    public void testCheckoutItemTotal() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        productsPage.addProductToCart(TestData.PRODUCT_BIKELIGHT);
        
        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver(), wait);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver(), wait);
        checkoutPage.fillCheckoutInfo(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        
        double calculatedTotal = checkoutPage.getCalculatedItemTotal();
        double displayedTotal = checkoutPage.getDisplayedSubtotal();
        
        Assert.assertEquals(calculatedTotal, displayedTotal, 0.01, "Displayed subtotal should match sum of item prices");
    }

    @Test(description = "UI-09 Complete checkout end to end and see the confirmation message", groups = {"smoke", "end-to-end"})
    public void testCheckoutEndToEnd() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        
        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver(), wait);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver(), wait);
        checkoutPage.fillCheckoutInfo(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPage.finishCheckout();
        
        String confirmationMsg = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMsg, "Thank you for your order!", "Confirmation message mismatch");
    }
}
