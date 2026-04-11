package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage extends BasePage {
    private final By pageTitle = By.cssSelector("[data-test='title']");

    public YourCartPage(WebDriver driver) {
      super(driver);
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
