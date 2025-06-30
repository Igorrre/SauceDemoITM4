package tests;

import org.openqa.selenium.By;
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
        yourCartPage.getExpectedNameProduct();
        yourCartPage.getExpectedPriceProduct();

        // Добавить товар в корзину
        yourCartPage.addProduct();

        // Перейти в корзину
        yourCartPage.openShoppingCart();

        // Проверить стоимость и имя товара в корзине
        softAssert.assertEquals(driver.findElement(By.cssSelector(".inventory_item_name")).getText(),
                yourCartPage.getExpectedNameProduct(),
                "Название товара не совпадает: ");
        softAssert.assertEquals(driver.findElement(By.cssSelector(".inventory_item_price")).getText(),
                yourCartPage.getExpectedPriceProduct(),
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
        softAssert.assertEquals(driver.findElement(By.cssSelector("[data-test=shopping-cart-badge]")).getText(),
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
        yourCartPage.getExpectedNameProduct();
        yourCartPage.getExpectedPriceProduct();

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