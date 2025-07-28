package tests;

import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {
    @Test(testName = "Проверка локаторов",
            description = "Проверка локаторов",
            groups = {"smoke"})
    public void checkLocators() {

        loginPage.open()
                .locatorTest(user, password);
    }
}