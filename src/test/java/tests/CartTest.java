package tests;

import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void checkGoodsInCart() {
        User user = UserFactory.standardUser();
        loginPage.open();
        loginPage.login(user.getUsername(), user.getPassword());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.goToCart();

        assertFalse(yourCartPage.getProductsNames().isEmpty());
        assertEquals(yourCartPage.getProductsNames().size(), 1);
        assertTrue(yourCartPage.getProductsNames().contains(goodsName));
    }

    @Test
    public void checkCheckoutButton() {
        User user = UserFactory.standardUser();
        loginPage.open();
        loginPage.login(user.getUsername(), user.getPassword());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.goToCart();
        assertEquals(yourCartPage.getTitle(), "Your Cart");
        yourCartPage.clickCheckout();
    }
}
