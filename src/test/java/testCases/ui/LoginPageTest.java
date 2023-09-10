package testCases.ui;

import PageObjects.BasePage;
import PageObjects.InventoryPage;
import PageObjects.LoginPage;
import com.saucelab.helper.HelperLog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePage {

    HelperLog logger = new HelperLog();

    @Test(groups = {"ui","sanity","login"})
    public void ShouldBeAbleToLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Assert.assertTrue(loginPage.isLoaded());

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isLoaded());
    }


}
