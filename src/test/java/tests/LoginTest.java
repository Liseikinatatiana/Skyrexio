package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(dataProvider = "LoginData")
    public void checkIncorrectLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorMessageDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMessageText(), errorMessage,
                "Error message doesn`t correspond");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_saeuc", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_saeuc", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }
}
