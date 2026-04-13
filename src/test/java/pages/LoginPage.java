package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Локаторы
    private final By usernameInput = By.xpath("//*[@id='user-name']");
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector("[id=login-button]");
    private final By errorMessage = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Метод для открытия страницы
    public void open() {
        driver.get(BasePage.BASE_URL);
    }

    public void open(final String url) {
        driver.get(BasePage.BASE_URL + url);
    }

    //Метод для ввода имени пользователя
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    //Метод для ввода пароля
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    //Метод для клика по кнопке логина
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    //Комбинированный метод для логина
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    //Проверка отображения сообщения об ошибке
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    //Получение текста ошибки
    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}
