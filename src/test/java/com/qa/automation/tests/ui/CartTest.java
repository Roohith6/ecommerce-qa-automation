package com.qa.automation.tests.ui;

import com.qa.automation.base.BaseTest;
import com.qa.automation.pages.LoginPage;
import com.qa.automation.pages.ProductsPage;
import com.qa.automation.pages.CartPage;
import com.qa.automation.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(description = "UI-06 Add one product, cart badge shows 1 and item is in the cart", groups = {"smoke", "positive"})
    public void testAddProductToCart() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        
        Assert.assertEquals(productsPage.getCartBadgeText(), "1", "Cart badge should display 1");

        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver(), wait);
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "There should be exactly 1 item in the cart");
    }

    @Test(description = "UI-07 Remove the product, cart becomes empty", groups = {"regression", "positive"})
    public void testRemoveProductFromCart() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        productsPage.removeProductFromCart(TestData.PRODUCT_BACKPACK);
        
        Assert.assertEquals(productsPage.getCartBadgeText(), "0", "Cart badge should be empty (or not show any count)");
        
        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver(), wait);
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty");
    }
}
