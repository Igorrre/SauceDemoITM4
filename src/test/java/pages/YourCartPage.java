package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage extends BasePage {

    private final By TITLE_YOUR_CART = By.cssSelector("[data-test=title]");
    private final By PRODUCT = By.cssSelector("[data-test^=add-to-cart]");
    private final By SHOPPING_CART = By.cssSelector("[data-test=shopping-cart-link]");
    private final By NAME_PRODUCT = By.cssSelector(".inventory_item_name");
    private final By PRICE_PRODUCT = By.cssSelector(".inventory_item_price");
    private final By CART_BADGE = By.cssSelector("[data-test=shopping-cart-badge]");
    private final By BUTTON_CHECKOUT = By.cssSelector("[data-test=checkout]");
    private final By TITLE_CHECKOUT = By.cssSelector("[data-test=title]");
    private final By BUTTON_CONTINUE_SHOPPING = By.cssSelector("[data-test=continue-shopping]");
    private final By BUTTON_REMOVE_PRODUCT = By.cssSelector("[data-test=remove-sauce-labs-backpack]");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isYourCartPage() {
        return driver.findElement(TITLE_YOUR_CART).isDisplayed();
    }

    @Step("Клик по кнопке добавить продукт в корзину")
    public void addProduct() {
        driver.findElement(PRODUCT).click();
        //takeScreenshot(driver);
    }

    @Step("Клик по кнопке корзины")
    public void openShoppingCart() {
        driver.findElement(SHOPPING_CART).click();
        //takeScreenshot(driver);
    }

    public String expectedNameProduct() {
        return driver.findElement(NAME_PRODUCT).getText();
    }

    public String expectedPriceProduct() {
        return driver.findElement(PRICE_PRODUCT).getText();
    }

    public String getNameProduct() {
        return driver.findElement(NAME_PRODUCT).getText();
    }

    public String getPriceProduct() {
        return driver.findElement(PRICE_PRODUCT).getText();
    }

    public String getCartBadge() {
        return driver.findElement(CART_BADGE).getText();
    }

    public String getCartBadgeShopping() {
        return driver.findElement(CART_BADGE).getText();
    }

    @Step("Клик по кнопке проверить в корзине")
    public void clickButtonCheckout() {
        driver.findElement(BUTTON_CHECKOUT).click();
        //takeScreenshot(driver);
    }

    public void openCheckoutPage1() {
        driver.get(BASE_URL + "/checkout-step-one.html");
    }

    public boolean isCheckoutPage() {
        return driver.findElement(TITLE_CHECKOUT).isDisplayed();
    }

    @Step("Клик по кнопке вернуться к покупкам")
    public void clickButtonContinueShopping() {
        driver.findElement(BUTTON_CONTINUE_SHOPPING).click();
        //takeScreenshot(driver);
    }

    @Step("Клик по кнопке удалить продукт из корзины")
    public void removeButtonProduct() {
        driver.findElement(BUTTON_REMOVE_PRODUCT).click();
        //takeScreenshot(driver);
    }

    public boolean isCartBadge() {
        return driver.findElements(CART_BADGE).size() > 0;
    }
}