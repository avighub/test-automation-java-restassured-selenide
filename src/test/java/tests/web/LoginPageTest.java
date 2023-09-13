package tests.web;

import com.saucelab.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.basesetup.WebTestSetup;

public class LoginPageTest extends WebTestSetup {

    @Test
    public void shouldBeAbleToLoginWithValidUsernameAndPassword() {
        LoginPage.getInstance()
                .visit()
                .checkLoginButtonColor()
                .login("standard_user", "secret_sauce");
        // TODO: Extract and keep the secrets in external files and pass as data provider
        // TODO: pass all user roles as test data and iterate over them
    }

}
