package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class YourCartTest extends BaseTest {
    @Test(priority = 5,
            description = "Проверка отображения страницы корзины",
            testName = "Проверка отображения страницы корзины без добавления товара",
            groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-5")
    @Description("Проверка отображения страницы без товара")
    public void checkPageYourCart() {

        loginStep.authorisation(user, password);
        productsPage.openShoppingCart()
                .isPageOpened();
    }

    @Test(priority = 4,
            description = "Проверка цены и имени товара",
            testName = "Проверка цены и названия товара после добавления в корзину",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-4")
    @Description("Проверка цени и названия товара")
    public void checkProductYourCart() {

        loginStep.authorisation(user, password);
                productsPage.addProduct()
                .openShoppingCart()
                .isPageOpened();

        // Проверить стоимость и имя товара в корзине
        softAssert.assertEquals(yourCartPage.productDetails(),
                productsPage.expectedProductDetails(),
                "Название товара и цена не совпадает: ");
    }

    @Test(priority = 1,
            description = "Проверка иконки корзины",
            invocationCount = 2,
            testName = "Проверка иконки корзины после добавления товара",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    @Description("Проверка иконки корзины")
    public void checkCartBadge() {

        loginStep.authorisation(user, password);
        productsPage.addProduct()
                .openShoppingCart()
                .isPageOpened();
        //Сравнение иконки корзины на главной странице и страницы корзины
        softAssert.assertEquals(yourCartPage.getCartBadgeShopping(), productsPage.getCartBadge(),
                "Иконка корзины не совпадает: ");
    }

    @Test(priority = 3,
            description = "Проверка отображения страницы",
            testName = "Проверка отображения страницы при переходе по кнопке Checkout",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-3")
    @Description("Проверка перехода на страницу проверки товаров в корзине")
    public void checkButtonCheckout() {

        loginStep.authorisation(user, password);
        productsPage.addProduct()
                .openShoppingCart()
                .isPageOpened()
                .clickButtonCheckout()
                .isPageOpened();
    }

    @Test(priority = 6,
            description = "Проверка возврата на страницу продуктов",
            testName = "Проверка кнопки возврата к покупкам",
            groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-6")
    @Description("Проверка кнопки возврата на страницу покупок")
    public void checkButtonContinueShopping() {

        loginStep.authorisation(user, password);
        productsPage.isPageOpened()
                .addProduct()
                .openShoppingCart()
                .isPageOpened()
                .clickButtonContinueShopping()
                .isPageOpened();
    }
}