package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    // Локаторы
    private final By usernameInput = By.xpath("//*[@id='user-name']");
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector("[id=login-button]");
    private final By errorMessage = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public void open() {
        driver.get(BasePage.BASE_URL);
    }

    public void open(final String url) {
        driver.get(BasePage.BASE_URL + url);
    }

    @Step("Ввод имени пользователя")
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку login")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Логин с именем и паролем")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Логин пользователем")
    public void login(User user) {
        login(user.getUsername(), user.getPassword());
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    @Step("Получение текста ошибки")
    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}
