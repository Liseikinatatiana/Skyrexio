package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class YourCartPage extends BasePage {
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By product = By.cssSelector(DATA_TEST_PATTERN.formatted("inventory-item-name"));
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By checkoutBtn = By.id("checkout");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение заголовка страницы корзины")
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    @Step("Получение списка наименований товаров в корзине")
    public ArrayList<String> getProductsNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingBtn));
        List<WebElement> allProducts = driver.findElements(product);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Нажитие на кнопку checkout")
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        driver.findElement(checkoutBtn).click();
    }
}
