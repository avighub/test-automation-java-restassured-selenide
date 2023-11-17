package tests.web.saucedemo;

import annotations.SmokeTest;
import annotations.feature.LoginTest;
import com.saucelab.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.basetest.WebTestSetup;

@LoginTest
@SmokeTest
public class LoginPageTest extends WebTestSetup {

    @Test
    public void shouldBeAbleToLoginWithValidUsernameAndPassword() {
        LoginPage.getInstance()
                .open()
                .checkLoginButtonColor()
                .login("standard_user", "secret_sauce")
                .checkHeaderElementsArePresent();
        // TODO: Extract and keep the secrets in external files
        // TODO: pass all user roles as test data and iterate over them
    }

    @Test
    public void shouldDisplayValidationErrorMessageForInvalidCredentials() {
        LoginPage.getInstance()
                .open()
                .clickLoginButton()
                .checkForValidationErrorMessageAndElementsColor();
    }

}
