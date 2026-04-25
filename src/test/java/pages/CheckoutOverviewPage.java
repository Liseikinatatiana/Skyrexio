package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage{
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }
    public String getTitle(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
}
