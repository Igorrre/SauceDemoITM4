package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class LocatorsTest {
    @Test
    public void checkLocators() {
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

        driver.findElement(By.className("login_logo"));
        driver.findElement(By.tagName("h4"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        driver.findElement(By.linkText("Sauce Labs Backpack")); //ищет только в тегах "/а"
        driver.findElement(By.partialLinkText("Backpack"));//ищет только в тегах "/а"

        //xpath:
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));//поиск по атрибуту
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));//поиск по тексту,
        driver.findElement(By.xpath("//a[contains(@class,'shopping')]"));//поиск по частичному совпадению атрибута,
        driver.findElement(By.xpath("//div[contains(text(),'Swag')]"));//поиск по частичному совпадению текста,

        //ancestor поднимается по дереву и ищет ближайший или все родительские
        driver.findElement(By.xpath("//*[text()='Swag Labs']//ancestor::div"));// поднимается по дереву и ищем ближайший или все родительские <div>.

        //descendant, помогает найти все вложенные элементы внутри выбранного элемента. Обычно используют сокращение ".//" для поиска всех потомков.
        WebElement filter = driver.findElement(By.cssSelector("[class=header_secondary_container]"));// Находим элемент filter
        filter.findElement(By.xpath(".//span")); //Ищем все <span> внутри этого элемента

        //following ищет все элементы, расположенные после выбранного элемента в документе, вне зависимости от уровня вложенности.
        driver.findElements(By.xpath("//*[@class='right_component']//following::*"));

        // parent для поиска родительского элемента
        driver.findElement(By.xpath("//*[text()='Products']/parent::div"));

        //preceding ищет все элементы, расположенные перед выбранным в документе.
        driver.findElements(By.xpath("//*[@class='bm-burger-button']//preceding::*"));

        //*поиск элемента с условием AND, например input[@class='_2zrpKA_1dBPDZ'and@type='text']
        driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ' and @id='add-to-cart-sauce-labs-bike-light']"));

        //- [attribute=value] Элемент с атрибутом, равным конкретному значению.
        driver.findElement(By.cssSelector("a[href='https://twitter.com/saucelabs']"));

        //- [attribute~=value] Атрибут содержит слово value в списке слов, разделённых пробелами.
        driver.findElement(By.cssSelector("button[class~='btn']"));

        //- [attribute|=value] Атрибут равен value или начинается с value-.
        driver.findElement(By.cssSelector("html[lang|='en']"));

        //- [attribute^=value] Атрибут начинается с value.
        driver.findElement(By.cssSelector("div[class^='app']"));

        //- [attribute$=value] Атрибут заканчивается на value.
        driver.findElement(By.cssSelector("div[class$='logo']"));

        //- [attribute*=value] Атрибут содержит value в любой части.
        driver.findElement(By.cssSelector("div[class*='item']"));

        //css
        driver.findElement(By.cssSelector(".header_label"));//.class
        driver.findElement(By.cssSelector("#menu_button_container"));//#id
        driver.findElement(By.cssSelector("a"));//tagname
        driver.findElement(By.cssSelector("a.shopping_cart_link"));//tagname.class

        //.class1 .class2 — выбирает любой элемент с классом class2, находящийся внутри (в дочерних или более глубоких уровнях) элемента с классом class1
        driver.findElement(By.cssSelector(".primary_header .header_label"));

        //.class1.class2 Если элемент имеет сразу несколько классов
        driver.findElement(By.cssSelector("[id=react-burger-menu-btn]")).click();
        driver.findElement(By.cssSelector("[id=about_sidebar_link]")).click();
        driver.findElement(By.cssSelector(".MuiContainer-root.MuiContainer-maxWidthXl.MuiContainer-disableGutters.css-1l45bh9"));

        driver.close();
    }
}
