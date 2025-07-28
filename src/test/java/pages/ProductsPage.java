package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.className("title");
    private final By PRODUCT = By.cssSelector("[data-test^=add-to-cart]");
    private final By SHOPPING_CART = By.cssSelector("[data-test=shopping-cart-link]");
    private final By NAME_PRODUCT = By.cssSelector(".inventory_item_name");
    private final By PRICE_PRODUCT = By.cssSelector(".inventory_item_price");
    private final By CART_BADGE = By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't open");
        }
        return this;
    }

    @Step("Клик по кнопке добавить продукт в корзину")
    public ProductsPage addProduct() {
        log.info("Click add product");
        driver.findElement(PRODUCT).click();
        return this;
        //takeScreenshot(driver);
    }

    @Step("Клик по кнопке корзины")
    public YourCartPage openShoppingCart() {
        log.info("Click shopping button ");
        driver.findElement(SHOPPING_CART).click();
        return new YourCartPage(driver);
        //takeScreenshot(driver);
    }

    @Step("Получение имени и цены продукта")
    public String[] expectedProductDetails() {
        log.info("Get Name and price product");
        String name = driver.findElement(NAME_PRODUCT).getText();
        String price = driver.findElement(PRICE_PRODUCT).getText();
        // сохранить данные в поля
        return new String[]{name, price};
    }

    @Step("Получение иконки корзины на главной странице")
    public String getCartBadge() {
        log.info("Get shopping icon text");
        return driver.findElement(CART_BADGE).getText();
    }
}