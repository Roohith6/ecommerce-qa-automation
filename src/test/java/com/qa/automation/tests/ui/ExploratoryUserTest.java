package com.qa.automation.tests.ui;

import com.qa.automation.base.BaseTest;
import com.qa.automation.pages.CartPage;
import com.qa.automation.pages.CheckoutPage;
import com.qa.automation.pages.LoginPage;
import com.qa.automation.pages.ProductsPage;
import com.qa.automation.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExploratoryUserTest extends BaseTest {

    @Test(description = "UI-11 problem_user exploratory test")
    public void testProblemUserFlow() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login(TestData.PROBLEM_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        
        Assert.assertEquals(productsPage.getCartBadgeText(), "1", "Badge should update");
        
        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver(), wait);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver(), wait);
        checkoutPage.fillCheckoutInfo(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPage.finishCheckout();
        
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!", "Should complete order");
    }

    @Test(description = "UI-12 performance_glitch_user exploratory test")
    public void testPerformanceGlitchUserFlow() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login(TestData.PERFORMANCE_GLITCH_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        
        Assert.assertEquals(productsPage.getCartBadgeText(), "1", "Badge should update");
        
        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver(), wait);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver(), wait);
        checkoutPage.fillCheckoutInfo(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPage.finishCheckout();
        
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!", "Should complete order despite delay");
    }
}
