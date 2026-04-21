package tests;

import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie", "Sauce Labs Fleece Jacket");

    @Test
    public void checkGoodsAdded() {
        User user = UserFactory.standardUser();
        loginPage.open();
        loginPage.login(user.getUsername(),user.getPassword());
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
