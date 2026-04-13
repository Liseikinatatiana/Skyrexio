package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void checkGoodsIncart() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(goodsName);
        productsPage.goToCart();
        Thread.sleep(2000);
        assertFalse(yourCartPage.getProductsNames().isEmpty());
        assertEquals(yourCartPage.getProductsNames().size(), 1);
        assertTrue(yourCartPage.getProductsNames().contains(goodsName));
    }
}
