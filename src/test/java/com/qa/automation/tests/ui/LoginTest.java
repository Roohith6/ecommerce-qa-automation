package com.qa.automation.tests.ui;

import com.qa.automation.base.BaseTest;
import com.qa.automation.pages.LoginPage;
import com.qa.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "UI-01 Valid login lands on the products page", groups = {"smoke", "positive"})
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        Assert.assertTrue(productsPage.isTitleDisplayed(), "Products page title should be displayed after valid login");
    }

    @Test(description = "UI-05 locked_out_user is rejected with the locked-out message", groups = {"negative", "regression"})
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login("locked_out_user", "secret_sauce");

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Epic sadface: Sorry, this user has been locked out."), "Locked out error message mismatch");
    }

    @Test(description = "UI-02 Invalid password shows the error message", groups = {"negative", "regression"})
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), "Invalid password error mismatch");
    }

    @Test(description = "UI-03 Empty username shows Username is required error", groups = {"negative", "regression"})
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login("", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Empty username error mismatch");
    }

    @Test(description = "UI-04 Empty password shows Password is required error", groups = {"negative", "regression"})
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login("standard_user", "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"), "Empty password error mismatch");
    }

    @Test(description = "UI-10 Logout returns to the login page", groups = {"regression", "positive"})
    public void testLogout() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(getDriver(), wait);
        productsPage.logout();
        
        // Assert we are back on login page by checking if the login button is displayed
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should reappear after logout");
    }
}
