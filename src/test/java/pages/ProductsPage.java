package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//div[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By addToCartBtn = By.xpath("//*[text()='Add to cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение заголовка страницы товаров")
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    @Step("Добавление первого товара в корзину")
    public void addToCart() {
        driver.findElements(addToCartBtn).get(0).click();
    }

    @Step("Получение количества товаров на странице")
    public int getGoodsQuantity() {
        return driver.findElements(addToCartBtn).size();
    }

    @Step("Добавление товара {goodsName} в корзину")
    public void addToCart(final String goodsName) {
        //By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, goodsName));
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }
}
