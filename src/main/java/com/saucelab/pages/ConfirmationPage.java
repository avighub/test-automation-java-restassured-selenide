package com.saucelab.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;


public class ConfirmationPage {

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();

    public void visit() {
        Selenide.open(ENV_CONFIG.loginPageUrl() + "/checkout-step-two.html");
    }

    public void setPageState() {
        Selenide.sessionStorage().setItem("standard-username", "standard-user");
        Selenide.sessionStorage().setItem("cart-contents", "[4,1]");
        Selenide.refresh();
    }

    public void hasItems() {
        String cartBadge = "";
        Selenide.$(Selectors.byCssSelector(".shopping_cart_badge")).shouldBe(Condition.visible);
    }

    public CheckoutCompletePage finishCheckout() {
        Selenide.$(Selectors.byCssSelector("#finish")).shouldBe(Condition.visible).click();
        return new CheckoutCompletePage();
    }

}
