package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutOverviewPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static user.UserFactory.standardUser;

@Epic("Оформление заказа")
@Feature("Заполнение данных для доставки")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class CheckoutTest extends BaseTest {
    private void addProductAndGoToCheckout() {
        loginPage.open();
        loginPage.login(standardUser());
        productsPage.addToCart();
        productsPage.navigationPanel.goToCart();
        yourCartPage.clickCheckout();
    }

    @Test (description = "Проверка заголовка страницы оформления заказа", priority =1)
    @Story("Отображение страницы Checkout")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testCheckoutPageTitle() {
        addProductAndGoToCheckout();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }

    @Test(description ="Проверка успешного оформления заказа с валидными данными" , dataProvider = "validCheckoutData",priority = 2)
    @Story("Успешное заполнение формы")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testSuccessfulCheckout(String firstName, String lastname, String postalCode) {
        addProductAndGoToCheckout();
        checkoutPage.fillCheckoutForm(firstName, lastname, postalCode);
        CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();
        assertEquals(overviewPage.getTitle(), "Checkout: Overview");
    }

    @Test(description = "Проверка валидации формы с некорректными данными", dataProvider = "invalidCheckoutData",priority = 3)
    @Story("Валидация полей формы")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
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
