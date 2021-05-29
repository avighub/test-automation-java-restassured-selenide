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
        Thread.sleep(4000); // Delete this
        loginPage.login("standard_user", "secret_sauce");

        // Navigate directly to checkout step two page
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        Thread.sleep(4000); // Delete this
        Assert.assertTrue(confirmationPage.isLoaded());

        //Set Page state: Add items to cart
        confirmationPage.setPageState();
        Thread.sleep(4000); // Delete this
        Assert.assertTrue(confirmationPage.hasItems());
        Thread.sleep(4000); // Delete this
        CheckoutCompletePage completePage = confirmationPage.finishCheckout();
        Thread.sleep(4000); // Delete this
        Assert.assertTrue(completePage.isLoaded());
    }

}
