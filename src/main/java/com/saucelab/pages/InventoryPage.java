package com.saucelab.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class InventoryPage {
    private InventoryPage(){}

    public static InventoryPage getInstance(){
        return new InventoryPage();
    }

    private static final SelenideElement PRODUCT_TITLE_TEXT = Selenide.$(Selectors.byText("Products"));
    private static final SelenideElement MENU_BUTTON = Selenide.$(Selectors.byId("react-burger-menu-btn"));
    private static final SelenideElement CART_LINK = Selenide.$(Selectors.byClassName("shopping_cart_link"));
    private static final SelenideElement LOGO_TEXT = Selenide.$(Selectors.byClassName("app_logo"));

    public void checkHeaderElementsArePresent() {
        PRODUCT_TITLE_TEXT.shouldBe(Condition.visible);
        MENU_BUTTON.shouldBe(Condition.interactable);
        CART_LINK.shouldBe(Condition.interactable);
        LOGO_TEXT.shouldBe(Condition.visible);
    }
}
