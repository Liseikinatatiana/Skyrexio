package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.standardUser;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login(standardUser());

        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(dataProvider = "loginData")
    public void checkIncorrectLogin(User user) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorMessageDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMessageText(), user.getErrorMessage(), "Error message doesn`t correspond");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.LockedOutUser()},
                {UserFactory.emptyUsernameUser()},
                {UserFactory.emptyPasswordUser()},
                {UserFactory.incorrectUser()}
        };
    }
}
