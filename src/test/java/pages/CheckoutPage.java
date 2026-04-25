package pages;

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
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }
    public void enterFirstName (String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void enterPostalCode(String postalCode){
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }
    public void fillCheckoutForm(String firstName, String lastName, String postalCode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }
    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }
    public boolean isErrorMessageDisplayed(){
        return driver.findElement(errorMessage).isDisplayed();
    }
    public String getErrorMessageText(){
        return driver.findElement(errorMessage).getText();
    }
    public boolean isFirstNameEmpty(){
        return driver.findElement(firstNameInput).getText().isEmpty();
    }
    public void clearForm(){
        driver.findElement(firstNameInput).clear();
        driver.findElement(lastNameInput).clear();
        driver.findElement(postalCodeInput).clear();
    }
    public CheckoutOverviewPage clickContinue(){
        driver.findElement(continueBtn).click();
        return new CheckoutOverviewPage(driver);
    }
}
