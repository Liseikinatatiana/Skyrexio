package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    // Локаторы
    private final By usernameInput = By.xpath("//*[@id='user-name']");
    private final By passwordInput = By.xpath("//*[@data-test='password']");
    private final By loginButton = By.cssSelector("[id=login-button]");
    private final By  errorMessage = By.xpath("//*[@data-test='error']");

    public LoginPage (WebDriver driver) {
        this.driver =driver;
    }
    //Метод для открытия страницы
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }
    //Метод для ввода имени пользователя
    public  void enterUsername (String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }
    //Метод для ввода пароля
    public void enterPassword (String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    //Метод для клика по кнопке логина
    public  void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    //Комбинированный метод для логина
    public void login(String username,String password) {
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
