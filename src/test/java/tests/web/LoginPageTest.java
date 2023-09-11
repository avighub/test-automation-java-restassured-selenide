package tests.web;

import com.saucelab.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.basesetup.WebTestSetup;

public class LoginPageTest extends WebTestSetup {


    @Test
    public void ShouldBeAbleToLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        loginPage.login("standard_user", "secret_sauce");
    }


}
