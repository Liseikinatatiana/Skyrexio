package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class YourCartPage extends BasePage {
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By product = By.cssSelector(".inventory-item-name");

    public YourCartPage(WebDriver driver) {
      super(driver);
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public ArrayList<String> getProductsNames() {
        List<WebElement> allProducts = driver.findElements(product);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}
