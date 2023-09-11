package com.saucelab.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class InventoryPage {

    private static final SelenideElement APP_LOGO = Selenide.$(Selectors.byClassName("app_logo"));

    public void isLoaded() {
        APP_LOGO.shouldBe(Condition.visible);
    }
}
