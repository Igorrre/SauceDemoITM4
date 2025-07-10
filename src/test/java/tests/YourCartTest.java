package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class YourCartTest extends BaseTest {
    @Test(priority = 6, testName = "Проверка отображения страницы корзины без добавления товара",
            groups = {"regression"})
    public void checkPageYourCart() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        yourCartPage.openYourCartPage();
        loginPage.login("standard_user", "secret_sauce");

        // Перейти в корзину
        yourCartPage.openShoppingCart();
        // Проверить отображение страницы
        assertTrue(yourCartPage.isYourCartPage());
    }

    @Test(priority = 5, testName = "Проверка цены и названия товара после добавления в корзину",
            groups = {"smoke"})
    public void checkProductYourCart() {

        driver.get("https://www.saucedemo.com/");

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

    @Test(priority = 1, invocationCount = 2,
            testName = "Проверка иконки корзины после добавления товара",
            groups = {"smoke"})
    public void checkCartBadge() {

        driver.get("https://www.saucedemo.com/");

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

    @Test(priority = 3, testName = "Проверка отображения страницы при переходе по кнопке Checkout",
            groups = {"smoke"})
    public void checkButtonCheckout() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        yourCartPage.openCheckoutPage1();
        loginPage.login("standard_user", "secret_sauce");

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Перейти по кнопке Checkout
        yourCartPage.clickButtonCheckout();

        // Проверить отображение страницы
        assertTrue(yourCartPage.isCheckoutPage1());
    }

    @Test(priority = 4, testName = "Проверка удаления товара в корзине",
            groups = {"smoke"})
    public void checkButtonRemoveProductYourCart() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");
        // Получить ожидаемую имя и цену продукта
        yourCartPage.expectedNameProduct();
        yourCartPage.expectedPriceProduct();

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Удалить товар
        yourCartPage.removeButtonProduct();

        // Проверить удаление товара
        softAssert.assertFalse(yourCartPage.isCartBadge(), "Товар не был удалён из корзины");
        softAssert.assertAll();
    }
}