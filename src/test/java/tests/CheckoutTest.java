package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutOverviewPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static user.UserFactory.standardUser;

public class CheckoutTest extends BaseTest {
    private void addProductAndGoToCheckout() {
        loginPage.open();
        loginPage.login(standardUser());
        productsPage.addToCart();
        productsPage.navigationPanel.goToCart();
        yourCartPage.clickCheckout();
    }

    @Test
    public void testCheckoutPageTitle() {
        addProductAndGoToCheckout();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }

    @Test(dataProvider = "validCheckoutData")
    public void testSuccessfulCheckout(String firstName, String lastname, String postalCode) {
        addProductAndGoToCheckout();
        checkoutPage.fillCheckoutForm(firstName, lastname, postalCode);
        CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();
        assertEquals(overviewPage.getTitle(), "Checkout: Overview");
    }

    @Test(dataProvider = "invalidCheckoutData")
    public void testCheckoutValidationError(String firstName, String lastName, String postalCode, String expectedError) {
        addProductAndGoToCheckout();
        checkoutPage.fillCheckoutForm(firstName, lastName, postalCode);
        checkoutPage.clickContinue();
        assertTrue(checkoutPage.isErrorMessageDisplayed());
        assertEquals(checkoutPage.getErrorMessageText(), expectedError);
    }

    @DataProvider(name = "validCheckoutData")
    public Object[][] validCheckoutData() {
        return new Object[][]{
                {"Mark", "Snoy", "12345"}
        };
    }

    @DataProvider(name = "invalidCheckoutData")
    public Object[][] invalidCheckoutData() {
        return new Object[][]{

                {"", "Snoy", "12345", "Error: First Name is required"},
                {"Mark", "", "12345", "Error: Last Name is required"},
                {"Mark", "Snoy", "", "Error: Postal Code is required"},
                {"", "", "", "Error: First Name is required"}
        };
    }
}
