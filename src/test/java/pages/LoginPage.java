package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    private final By LOGIN_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы логина")
    public LoginPage open() {
        log.info("Login open page");
        driver.get(BASE_URL);
        //takeScreenshot(driver);
        //waitForPageLoaded();
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FIELD));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't open");
        }
        return this;
    }

    @Step("Вход в систему с именем пользователя {user} и паролем {password}")
    public ProductsPage login(String user, String password) {
        log.info("Authorization: {}, {}", user, password);
        driver.findElement(LOGIN_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Проверка локаторов")
    public void locatorTest() {
        log.info("Check locators");
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
//        driver.findElement(By.cssSelector("[id=react-burger-menu-btn]")).click();
//        driver.findElement(By.cssSelector("[id=about_sidebar_link]")).click();
//        driver.findElement(By.cssSelector(".MuiContainer-root.MuiContainer-maxWidthXl.MuiContainer-disableGutters.css-1l45bh9"));
    }

    public String getErrorMessage() {
        log.info("Get message error");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}