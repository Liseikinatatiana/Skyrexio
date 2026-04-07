package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");

        assertEquals(productsPage.getTitle(),"Products");
    }

    @Test
    public void checkIncorrectLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user","secret_sauce");

        assertTrue(loginPage.isErrorMessageDisplayed(),"The error message fails to appear");
        String actualErrorText = loginPage.getErrorMessageText();
        assertEquals(actualErrorText,"Epic sadface: Sorry, this user has been locked out.");
    }
}
