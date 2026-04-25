package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import user.User;
import user.UserFactory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CheckoutTest extends BaseTest {
    private void addProductAndGoToCheckout() {
        User user = UserFactory.standardUser();
        loginPage.open();
        loginPage.login(user.getUsername(), user.getPassword());
        productsPage.addToCart();
        productsPage.navigationPanel.goToCart();
        yourCartPage.clickCheckout();
    }

    @Test
    public void testCheckoutPageTitle() {
        addProductAndGoToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }

    @Test(dataProvider = "checkoutData")
    public void testCheckoutValidation(String firstName, String lastname, String postalCode, String expectedError) {
        addProductAndGoToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutForm(firstName, lastname, postalCode);
        if (expectedError.isEmpty()) {
            CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();
            assertEquals(overviewPage.getTitle(), "Checkout: Overview");
        } else {
            checkoutPage.clickContinue();
            assertTrue(checkoutPage.isErrorMessageDisplayed());
            assertEquals(checkoutPage.getErrorMessageText(), expectedError);
        }
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"Mark", "Snoy", "12345", ""},
                {"", "Snoy", "12345", "Error: First Name is required"},
                {"Mark", "", "12345", "Error: Last Name is required"},
                {"Mark", "Snoy", "", "Error: Postal Code is required"},
                {"", "", "", "Error: First Name is required"}
        };
    }
}
