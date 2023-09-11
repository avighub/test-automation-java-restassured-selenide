package com.saucelab.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;


public class LoginPage {

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
    private static final SelenideElement USERNAME_FILED = Selenide.$(Selectors.byCssSelector("#user-name"));
    private static final SelenideElement PASSWORD_FILED = Selenide.$(Selectors.byCssSelector("#password"));
    private static final SelenideElement LOGIN_BUTTON = Selenide.$(Selectors.byCssSelector("#login-button"));
    private static final SelenideElement SWAGLAB_LOGO = Selenide.$(Selectors.byXpath("//div[@class='login_logo']"));


    public LoginPage visit() {
        Selenide.open(ENV_CONFIG.loginPageUrl());
        return this;
    }

    public InventoryPage login(String username, String password) {
        USERNAME_FILED.shouldBe(Condition.interactable).sendKeys(username);
        PASSWORD_FILED.shouldBe(Condition.interactable).sendKeys(password);
        LOGIN_BUTTON.shouldBe(Condition.interactable).click();
        return new InventoryPage();
    }

}
