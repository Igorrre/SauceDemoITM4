package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class YourCartTest extends BaseTest {
    @Test(priority = 5,
            description = "Проверка отображения страницы корзины",
            testName = "Проверка отображения страницы корзины без добавления товара",
            groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-5")
    @Description("Проверка отображения страницы без товара")
    public void checkPageYourCart() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Перейти в корзину
        yourCartPage.openShoppingCart();
        // Проверить отображение страницы
        assertFalse(yourCartPage.isYourCartPage());
    }

    @Test(priority = 4,
            description = "Проверка цены и имени товара",
            testName = "Проверка цены и названия товара после добавления в корзину",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-4")
    @Description("Проверка цени и названия товара")
    public void checkProductYourCart() {

        loginPage.open();
        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");
        // Получить ожидаемую имя и цену продукта
        yourCartPage.expectedPriceProduct();
        yourCartPage.expectedNameProduct();

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Проверить стоимость и имя товара в корзине
        softAssert.assertEquals(yourCartPage.getPriceProduct(),
                yourCartPage.expectedPriceProduct(),
                "Название товара не совпадает: ");
        softAssert.assertEquals(yourCartPage.getNameProduct(),
                yourCartPage.expectedNameProduct(),
                "Цена не совпадает: ");
        softAssert.assertAll();
    }

    @Test(priority = 1,
            description = "Проверка иконки корзины",
            invocationCount = 2,
            testName = "Проверка иконки корзины после добавления товара",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    @Description("Проверка иконки корзины")
    public void checkCartBadge() {

        loginPage.open();

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");

        // Добавить товар в корзину
        yourCartPage.addProduct();
        // Получить иконку корзины с количеством
        yourCartPage.getCartBadge();

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Проверить иконку в корзине
        softAssert.assertEquals(yourCartPage.getCartBadgeShopping(),
                yourCartPage.getCartBadge(),
                "Иконка корзины не совпадает: ");
        softAssert.assertAll();
    }

    @Test(priority = 3,
            description = "Проверка отображения страницы",
            testName = "Проверка отображения страницы при переходе по кнопке Checkout",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-3")
    @Description("Проверка перехода на страницу проверки товаров в корзине")
    public void checkButtonCheckout() {

        loginPage.open();

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Перейти по кнопке Checkout
        yourCartPage.clickButtonCheckout();

        // Проверить отображение страницы
        assertTrue(yourCartPage.isCheckoutPage());
    }

    @Test(priority = 6,
            description = "Проверка возврата на страницу продуктов",
            testName = "Проверка кнопки возврата к покупкам",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-6")
    @Description("Проверка кнопки возврата на страницу покупок")
    public void checkButtonContinueShopping() {

        loginPage.open();

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        //Вернуться к покупкам
        yourCartPage.clickButtonContinueShopping();

        // Проверить отображение страницы
        assertTrue(productsPage.isPageOpened());
    }
}