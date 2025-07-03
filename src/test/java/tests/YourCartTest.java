package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class YourCartTest extends BaseTest {
    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void checkButtonContinueShopping() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Перейти по кнопке Checkout
        yourCartPage.clickButtonContinueShopping();

        // Проверить отображение страницы
        assertTrue(productsPage.isPageOpened());
    }

    @Test
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