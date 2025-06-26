import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class ShoppingTest {
    @Test
    public void checkLocators() {
        SoftAssert softAssert = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        //Создается карта настроек chromePrefs для хранения предпочтений браузера
        HashMap<String, Object> chromePrefs = new HashMap<>();
        //В карту добавляются параметры, отключающие менеджер паролей
        chromePrefs.put("credentials_enable_service", false);//отключает сервис сохранения паролей
        chromePrefs.put("profile.password_manager_enabled", false);//отключает встроенный менеджер паролей.
        options.setExperimentalOption("prefs", chromePrefs);//предпочтения передаются браузеру
        //Добавляются аргументы командной строки для запуска браузера
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=Login]")).click();

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