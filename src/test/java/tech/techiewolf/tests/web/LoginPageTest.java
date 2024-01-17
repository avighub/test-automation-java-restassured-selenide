package tech.techiewolf.tests.web;

import org.junit.jupiter.api.Test;
import tech.techiewolf.annotations.feature.LoginTest;
import tech.techiewolf.pages.HomePage;
import tech.techiewolf.pages.LoginPage;
import tech.techiewolf.tests.base.WebTestSetup;

class LoginPageTest extends WebTestSetup {

  @LoginTest
  @Test
  void shouldBeAbleToLoginUsingValidCredentials()
    {
      LoginPage.getInstance()
              .open()
              .loginSuccessfully(ENV_CONFIG.username(), ENV_CONFIG.password());
      HomePage.getInstance()
              .checkIfHomePageIsLoaded();
    }
}
