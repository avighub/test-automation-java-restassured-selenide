package tech.techiewolf.pages;

import com.codeborne.selenide.*;
import tech.techiewolf.config.ConfigurationFactory;
import tech.techiewolf.config.EnvironmentConfig;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;


public final class LoginPage {

  private LoginPage()
    {
    }

  private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
  private static final SelenideElement SIGN_IN_HEADER_TEXT = $(byText("Sign in"));
  private static final SelenideElement USERNAME_FILED = $(byName("username"));
  private static final SelenideElement PASSWORD_FILED = $(byName("password"));
  private static final SelenideElement SIGN_IN_BUTTON = $(byText("Sign In"));


  public static LoginPage getInstance()
    {
      return new LoginPage();
    }

  public LoginPage open()
    {
      Selenide.open(ENV_CONFIG.loginPageUrl());
      SIGN_IN_HEADER_TEXT.shouldBe(visible);
      return this;
    }

  public LoginPage enterUsername(String username)
    {
      USERNAME_FILED.shouldBe(interactable).sendKeys(username);
      return this;
    }

  public LoginPage enterPassword(String password)
    {
      PASSWORD_FILED.shouldBe(interactable).sendKeys(password);
      return this;
    }

  public LoginPage clickSignInButton()
    {
      SIGN_IN_BUTTON.shouldBe(enabled).click();
      return this;
    }

  public HomePage loginSuccessfully(String username, String password)
    {
      this.enterUsername(username)
              .enterPassword(password)
              .clickSignInButton();
      return HomePage.getInstance();
    }
}
