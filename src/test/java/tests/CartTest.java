package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.standardUser;

@Epic("Управление корзиной")
@Feature("Просмотр и управление корзиной")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test(description = "Проверка отображения добавленного товара в корзине", priority = 1)
    @Story("Отображение товаров в корзине")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void checkGoodsInCart() {
        loginPage.open();
        loginPage.login(standardUser());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.goToCart();

        assertFalse(yourCartPage.getProductsNames().isEmpty());
        assertEquals(yourCartPage.getProductsNames().size(), 1);
        assertTrue(yourCartPage.getProductsNames().contains(goodsName));
    }

    @Test(description = "Проверка работы кнопки Checkout в корзине", priority = 2)
    @Story("Оформление заказа из корзины")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void checkCheckoutButton() {
        loginPage.open();
        loginPage.login(standardUser());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.goToCart();
        assertEquals(yourCartPage.getTitle(), "Your Cart");
        yourCartPage.clickCheckout();
    }
}
