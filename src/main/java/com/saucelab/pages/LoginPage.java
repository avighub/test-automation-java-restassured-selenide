package com.saucelab.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;


public class LoginPage {

    private LoginPage() {
    }

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
    private static final SelenideElement USERNAME_FILED = Selenide.$(Selectors.byCssSelector("#user-name"));
    private static final SelenideElement PASSWORD_FILED = Selenide.$(Selectors.byCssSelector("#password"));
    private static final SelenideElement LOGIN_BUTTON = Selenide.$(Selectors.byCssSelector("#login-button"));
    private static final SelenideElement SWAGLAB_LOGO = Selenide.$(Selectors.byXpath("//div[@class='login_logo']"));

    public static LoginPage getInstance() {
        return new LoginPage();
    }

    public LoginPage visit() {
        Selenide.open(ENV_CONFIG.loginPageUrl());
        SWAGLAB_LOGO.shouldBe(Condition.visible);
        return this;
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
