package testCases.ui;

import PageObjects.BasePage;
import PageObjects.InventoryPage;
import PageObjects.LoginPage;
import com.qa.helper.HelperLog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePage {

    HelperLog logger = new HelperLog();

    @Test(groups = {"ui","sanity","login"})
    public void ShouldBeAbleToLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Thread.sleep(4000); //Delete this
        Assert.assertTrue(loginPage.isLoaded());

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(4000); //Delete this
        Assert.assertTrue(inventoryPage.isLoaded());
    }


}
