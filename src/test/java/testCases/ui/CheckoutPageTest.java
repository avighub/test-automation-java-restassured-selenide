package testCases.ui;

import PageObjects.BasePage;
import PageObjects.CheckoutCompletePage;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutPageTest extends BasePage {

    @Test(groups = {"ui","sanity","checkout"})
    public void ShouldBeAbleToCheckoutWithItems() throws InterruptedException {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.visit();
        loginPage.login("standard_user", "secret_sauce");

        // Navigate directly to checkout step two page
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        Assert.assertTrue(confirmationPage.isLoaded());

        //Set Page state: Add items to cart
        confirmationPage.setPageState();
        Assert.assertTrue(confirmationPage.hasItems());
        CheckoutCompletePage completePage = confirmationPage.finishCheckout();
        Assert.assertTrue(completePage.isLoaded());
    }

}
