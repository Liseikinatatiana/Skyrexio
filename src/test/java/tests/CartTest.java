package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void checkGoodsIncart() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(goodsName);

        System.out.println("=== Проверка состояния кнопки ===");
        String buttonXpath = String.format("//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button", goodsName);
        String buttonText = driver.findElement(By.xpath(buttonXpath)).getText();
        System.out.println("Текст кнопки после клика: " + buttonText);
        System.out.println("=== Проверка бейджика корзины ===");
        try {
            WebElement badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
            System.out.println("Счетчик корзины: " + badge.getText());
        } catch (Exception e) {
            System.out.println("Бейджик корзины не найден (возможно, счетчик не появился)");
        }
        productsPage.navigationPanel.goToCart();

        System.out.println("Товары в корзине: " + yourCartPage.getProductsNames());
        System.out.println("Пустая ли корзина? " + yourCartPage.getProductsNames().isEmpty());
        System.out.println("Размер корзины: " + yourCartPage.getProductsNames().size());
        assertFalse(yourCartPage.getProductsNames().isEmpty());
        assertEquals(yourCartPage.getProductsNames().size(), 1);
        assertTrue(yourCartPage.getProductsNames().contains(goodsName));
    }
}
