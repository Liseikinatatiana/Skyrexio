package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.standardUser;

@Epic("Управление товарами")
@Feature("Добавление товаров в корзину")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie", "Sauce Labs Fleece Jacket");

    @Test(description = "Проверка добавления товаров в корзину и обновления счетчиков", priority = 1)
    @Story("Добавление нескольких товаров")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(standardUser());
        assertTrue(productsPage.pageTitleDisplayed());
        assertEquals(productsPage.getGoodsQuantity(), 6);
        productsPage.addToCart();

        for (String goods : goodsList) {
            productsPage.addToCart(goods);
        }
        assertEquals(productsPage.navigationPanel.checkCounterValue(), "4");
        assertEquals(productsPage.navigationPanel.checkCounterColor(), "rgba(226, 35, 26, 1)");
        productsPage.navigationPanel.goToCart();
        assertEquals(yourCartPage.getTitle(), "Your Cart");
    }
}
