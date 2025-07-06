package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    final String BASE_URL = "https://www.saucedemo.com/";

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //JavascriptExecutor js = (JavascriptExecutor) driver;

//    public void clickJS(WebElement element) {
//        js.executeScript("arguments[0].click;",element);
//    }
//
//    public void waitForPageLoaded() {
//        new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor) driver)
//                        .executeScript("return document.readyState")
//                        .toString().equals("complete");        }
//        }; }
}