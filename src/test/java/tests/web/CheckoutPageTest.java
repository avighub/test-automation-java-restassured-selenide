package tests.web;

import com.saucelab.pages.CheckoutCompletePage;
import com.saucelab.pages.ConfirmationPage;
import com.saucelab.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.basesetup.WebTestSetup;


public class CheckoutPageTest extends WebTestSetup {

    @Test
    public void ShouldBeAbleToCheckoutWithItems() throws InterruptedException {
        LoginPage.getInstance()
                .visit()
                .login("standard_user", "secret_sauce");

        // Navigate directly to checkout step two page
        ConfirmationPage confirmationPage = new ConfirmationPage();
        confirmationPage.visit();

        //Set Page state: Add items to cart
        confirmationPage.setPageState();
        confirmationPage.hasItems();
        CheckoutCompletePage completePage = confirmationPage.finishCheckout();
    }

}
