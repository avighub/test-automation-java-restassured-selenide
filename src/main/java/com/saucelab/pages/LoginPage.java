package com.saucelab.pages;

import com.codeborne.selenide.*;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;
import com.saucelab.pages.enums.UserRole;
import com.saucelab.utils.BrowserUtils;
import org.openqa.selenium.support.Color;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private LoginPage() {
    }

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
    private static final SelenideElement SWAGLAB_LOGO = $(byXpath("//div[@class='login_logo']"));
    private static final SelenideElement USERNAME_FILED = $(byCssSelector("#user-name"));
    private static final SelenideElement PASSWORD_FILED = $(byCssSelector("#password"));
    private static final SelenideElement LOGIN_BUTTON = $(byXpath("//input[@value='Login']"));
    private static final String LOGIN_BUTTON_COLOR_IN_HEX = "#3ddc91";
    private static final SelenideElement ERROR_MESSAGE_CONTAINER = $(byXpath("//div[@class='error-message-container error']"));
    private static final String ERROR_MESSAGE_CONTAINER_COLOR_IN_HEX = "#e2231a";
    private static final SelenideElement ERROR_MESSAGE = $(byXpath("//h3[@data-test='error']"));
    private static final String ERROR_MESSAGE_TEXT = "Epic sadface: You can only access '/inventory.html' when you are logged in.";


    public static LoginPage getInstance() {
        return new LoginPage();
    }

    public LoginPage open() {
        Selenide.open(ENV_CONFIG.loginPageUrl());
        SWAGLAB_LOGO.shouldBe(visible);
        return this;
    }

    public LoginPage clickLoginButton() {
        LOGIN_BUTTON.shouldBe(visible).click();
        return this;
    }

    public LoginPage checkLoginButtonColor() {
        String loginButtonColorAsRgba = Color.fromString(LOGIN_BUTTON_COLOR_IN_HEX).asRgba();
        LOGIN_BUTTON.getCssValue("background-color").equals(loginButtonColorAsRgba);
        return this;
    }

    public LoginPage checkForValidationErrorMessageAndElementsColor() {
        String containerBoxColorAsRgba = Color.fromString(ERROR_MESSAGE_CONTAINER_COLOR_IN_HEX).asRgba();
        ERROR_MESSAGE_CONTAINER.shouldBe(visible).getCssValue("background-color").equals(containerBoxColorAsRgba);
        ERROR_MESSAGE.shouldBe(visible).getText().equals(ERROR_MESSAGE_TEXT);
        return this;
    }

    public InventoryPage loginUsingCookies() {
        BrowserUtils.setCookie("session-username", "standard_user", "/");
        return InventoryPage.getInstance();
    }

    private void enterCredentialsAndClickLogin(String username, String password) {
        USERNAME_FILED.shouldBe(Condition.interactable).sendKeys(username);
        PASSWORD_FILED.shouldBe(Condition.interactable).sendKeys(password);
        LOGIN_BUTTON.shouldBe(Condition.interactable).click();
    }

    public InventoryPage login(String username, String password) {
        enterCredentialsAndClickLogin(username, password);
        return InventoryPage.getInstance();
    }

    public InventoryPage loginAs(UserRole userRole) {
        String standardUser = "standard_user";
        String problemUser = "problem_user";
        String lockedOutUser = "locked_out_user";
        String performanceGlitchUser = "performance_glitch_user";
        String password = "secret_sauce";

        switch (userRole) {
            case STANDARD_USER:
                enterCredentialsAndClickLogin(standardUser, password);
                break;
            case PROBLEM_USER:
                enterCredentialsAndClickLogin(problemUser, password);
                break;
            case LOCKED_OUT_USER:
                enterCredentialsAndClickLogin(lockedOutUser, password);
                break;
            case PERFORMANCE_GLITCH_USER:
                enterCredentialsAndClickLogin(performanceGlitchUser, password);
                break;
        }

        return InventoryPage.getInstance();
    }

}
