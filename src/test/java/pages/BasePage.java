package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    final String BASE_URL = "https://www.saucedemo.com/";

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}