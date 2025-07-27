package tests;

import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {
    @Test(testName = "Проверка локаторов",
            description = "Проверка локаторов",
            groups = {"smoke"})
    public void checkLocators() {

        loginStep.authorisation("standard_user", "secret_sauce");
        loginPage.locatorTest();
    }
}