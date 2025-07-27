package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class YourCartPage extends BasePage {

    private final By NAME_PRODUCT = By.cssSelector(".inventory_item_name");
    private final By PRICE_PRODUCT = By.cssSelector(".inventory_item_price");
    private final By TITLE_YOUR_CART = By.cssSelector("[data-test=title]");
    private final By CART_BADGE = By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']");
    private final By BUTTON_CHECKOUT = By.cssSelector("[data-test=checkout]");
    private final By BUTTON_CONTINUE_SHOPPING = By.cssSelector("[data-test=continue-shopping]");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public YourCartPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_YOUR_CART));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't open");
        }
        return this;
    }

    @Step("Клик по кнопке проверить в корзине")
    public CheckoutPage clickButtonCheckout() {
        log.info("Click checkout button");
        driver.findElement(BUTTON_CHECKOUT).click();
        return new CheckoutPage(driver);
        //takeScreenshot(driver);
    }

    @Step("Клик по кнопке вернуться к покупкам")
    public ProductsPage clickButtonContinueShopping() {
        log.info("Click continue button ");
        driver.findElement(BUTTON_CONTINUE_SHOPPING).click();
        return new ProductsPage(driver);
        //takeScreenshot(driver);
    }

    @Step("Получение имени и цены продукта в корзине")
    public String[] productDetails() {
        log.info("Get Name and price product");
        String name = driver.findElement(NAME_PRODUCT).getText();
        String price = driver.findElement(PRICE_PRODUCT).getText();
        // сохранить данные в поля класса, если нужно
        return new String[]{name, price};
    }

    @Step("Получение иконки корзины в корзине товаров")
    public String getCartBadgeShopping() {
        log.info("Get shopping icon text");
        return driver.findElement(CART_BADGE).getText();
    }
}