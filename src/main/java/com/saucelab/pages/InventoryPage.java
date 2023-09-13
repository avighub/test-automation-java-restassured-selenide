package com.saucelab.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InventoryPage {
    private InventoryPage() {
    }

    public static InventoryPage getInstance() {
        return new InventoryPage();
    }

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
    private static final SelenideElement PRODUCT_TITLE_TEXT = $(Selectors.byText("Products"));
    private static final SelenideElement MENU_BUTTON = $(Selectors.byId("react-burger-menu-btn"));
    private static final SelenideElement CART_LINK = $(Selectors.byClassName("shopping_cart_link"));
    private static final SelenideElement SORTING_DROPDOWN = $(Selectors.byXpath("//select[@data-test='product_sort_container']"));
    private static final SelenideElement LOGO_TEXT = $(Selectors.byClassName("app_logo"));

    public InventoryPage visit() {
        LoginPage.getInstance().loginUsingCookies();
        open(ENV_CONFIG.inventoryPageUrl());
        PRODUCT_TITLE_TEXT.shouldBe(visible);
        return this;
    }

    public InventoryPage checkHeaderElementsArePresent() {
        MENU_BUTTON.shouldBe(interactable);
        LOGO_TEXT.shouldBe(visible);
        CART_LINK.shouldBe(interactable);
        return this;
    }

    public InventoryPage checkInventoryPageHeaderElementsArePresent() {
        PRODUCT_TITLE_TEXT.shouldBe(visible);
        SORTING_DROPDOWN.shouldBe(visible);
        return this;
    }
}
