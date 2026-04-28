package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By cancelBtn = By.id("cancel");
    private final By errorMessage = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение заголовка страницы оформления заказов")
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    @Step("Ввод имени {firstName}")
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    @Step("Ввод фамилии {lastName}")
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    @Step("Ввод почтового индекса {postalCode}")
    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    @Step("Заполнение формы оформления заказа: имя {firstName}, фамилия {lastName}, почтовый индекс {postalCode}")
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    @Step("Нажатие на кнопку cancel")
    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }

    @Step("Проверка отображения сообщения об ошибке в форме оформления")
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    @Step("Получение текста сообщения об ошибке в форме оформления")
    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Проверка что поле имени пустое")
    public boolean isFirstNameEmpty() {
        return driver.findElement(firstNameInput).getAttribute("value").isEmpty();
    }

    @Step("Очистка формы оформления заказа")
    public void clearForm() {
        driver.findElement(firstNameInput).clear();
        driver.findElement(lastNameInput).clear();
        driver.findElement(postalCodeInput).clear();
    }

    @Step("Нажатие на кнопку Continue и переход к обзору заказа")
    public CheckoutOverviewPage clickContinue() {
        driver.findElement(continueBtn).click();
        return new CheckoutOverviewPage(driver);
    }
}
