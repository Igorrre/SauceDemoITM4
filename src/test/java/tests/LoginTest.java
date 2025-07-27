package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(priority = 1,
            invocationCount = 2,
            description = "Проверка входа в систему без пароля",
            testName = "Негативный тест логина без пароля",
            groups = {"smoke"})

    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    @Link("https://www.saucedemo.com/")
    @Epic("Login")
    @Feature("Log in")
    @Story("LoginWithoutPassword")
    @TmsLink("ITM-4")
    @Issue("ITM-4-1")
    @Description("Проверка, что пользователь не может войти без пароля")
    public void checkLoginWithoutPassword() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение не соответствует");
    }

    @Test(priority = 4,
            description = "Проверка входа в систему без имени",
            groups = {"regression"},
            dependsOnMethods = {"checkLogin"})
    public void checkLoginWithoutUserName() {
        loginPage.open()
                .isPageOpened()
                .login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение не соответствует");
    }


    @Test(priority = 3,
            description = "Проверка входа в систему по логину паролю",
            testName = "Негативный тест логина без пароля",
            groups = {"regression"})
    public void checkLoginWithNegativeValue() {
        loginPage.open()
                .isPageOpened()
                .login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение не соответствует");
    }

    @Test(priority = 2,
            testName = "Позитивный тест логина",
            description = "Проверка входа в систему с именем и паролем",
            timeOut = 5000,
            groups = {"regression"})
    public void checkLogin() {
        loginStep.authorisation("standard_user", "secret_sauce");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "LoginData",
            groups = {"smoke"},
            description = "Проверка получения сообщений при различных способах входа",
            testName = "Негативный тест логина")
    public void checkLoginWithNegativeValue1(String user, String password, String expectedMessage) {
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedMessage,
                "Сообщение не соответствует");
    }
}