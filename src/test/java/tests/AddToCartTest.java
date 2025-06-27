package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddToCartTest extends BaseTest {
    @Test
    public void checkAddToCart() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        assertTrue(driver.findElement(By.className("title")).isDisplayed());

        // Добавить товар в корзину
        driver.findElement(By.cssSelector("[id=add-to-cart-sauce-labs-backpack]")).click();

        // Перейти в корзину
        driver.findElement(By.cssSelector("[id=shopping_cart_container]")).click();

        // Проверить стоимость и имя товара в корзине
        String cartProductName = driver.findElement(By.xpath("//div[1]/div[3]/div[2]/a/div")).getText();
        String cartProductPrice = driver.findElement(By.xpath("//div[1]/div[3]/div[2]/div[2]/div")).getText();

        String expectedName = "Sauce Labs Backpack";
        String expectedPrice = "$29.99";

        softAssert.assertEquals(cartProductName, expectedName, "Название товара не совпадает: ");
        softAssert.assertEquals(cartProductPrice, expectedPrice, "Цена не совпадает: ");

        driver.quit();
        softAssert.assertAll();
    }
}