package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.standardUser;

@Epic("Авторизация пользователя")
@Feature("Логин в систему")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class LoginTest extends BaseTest {

    @Test(description = "Проверка корректный логин", priority = 1)
    @Story("Успешный вход в систему")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void checkLogin() {
        loginPage.open();
        loginPage.login(standardUser());

        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(description = "Проверка некорректный логин с разными данными", dataProvider = "loginData", priority = 2)
    @Story("Неуспешный вход в систему")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
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
