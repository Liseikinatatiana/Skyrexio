package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel {
    WebDriver driver;
    private final By cartLink = By.cssSelector(BasePage.DATA_TEST_PATTERN.formatted("shopping-cart-link"));
    private final By cartBadge = By.cssSelector(BasePage.DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход в корзину")
    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    @Step("Получение значения счетчика корзины")
    public String checkCounterValue() {
        return driver.findElement(cartBadge).getText();
    }

    @Step("Получение цвета счетчика корзины")
    public String checkCounterColor() {
        return driver.findElement(cartBadge).getCssValue("background-color");
    }
}
