package com.saucelab.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class InventoryPage {
    private InventoryPage() {
    }

    public static InventoryPage getInstance() {
        return new InventoryPage();
    }

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
    private static final SelenideElement PRODUCT_TITLE_TEXT = $(byText("Products"));
    private static final SelenideElement MENU_BUTTON = $(byId("react-burger-menu-btn"));
    private static final SelenideElement CART_BUTTON = $(byClassName("shopping_cart_link"));
    private static final SelenideElement SORTING_DROPDOWN = $(byXpath("//select[@data-test='product_sort_container']"));
    private static final SelenideElement LOGO_TEXT = $(byClassName("app_logo"));
    private static final String ADD_TO_CART_BUTTON_LOCATOR = "//div[@class='inventory_item']/div[contains(.,'%s')]/div/button[contains(.,'Add to cart')]";
    private static final String REMOVE_BUTTON_LOCATOR = "//div[@class='inventory_item']/div[contains(.,'%s')]/div/button[contains(.,'Remove')]";
    private static final SelenideElement CART_ITEM_COUNT = $(byXpath("//span[@class='shopping_cart_badge']"));


    public InventoryPage open() {
        LoginPage.getInstance().loginUsingCookies();
        Selenide.open(ENV_CONFIG.inventoryPageUrl());
        PRODUCT_TITLE_TEXT.shouldBe(visible);
        return this;
    }

    public InventoryPage checkHeaderElementsArePresent() {
        MENU_BUTTON.shouldBe(interactable);
        LOGO_TEXT.shouldBe(visible);
        CART_BUTTON.shouldBe(visible);
        return this;
    }

    public InventoryPage checkInventoryPageHeaderElementsArePresent() {
        PRODUCT_TITLE_TEXT.shouldBe(visible);
        SORTING_DROPDOWN.shouldBe(visible);
        return this;
    }

    public InventoryPage clickAddToCartButton(String itemName) {
        String addToCartButtonLocatorFormatted = String.format(ADD_TO_CART_BUTTON_LOCATOR, itemName);
        $x(addToCartButtonLocatorFormatted).shouldBe(visible).click();
        return this;
    }

    public InventoryPage checkIfRemoveButtonIsPresent(String itemName) {
        String removeButtonLocatorFormatted = String.format(REMOVE_BUTTON_LOCATOR, itemName);
        $x(removeButtonLocatorFormatted).shouldBe(interactable);
        return this;
    }

    public InventoryPage checkItemCountInCart(int count) {
        CART_ITEM_COUNT.shouldBe(visible).getText().equals(Integer.toString(count));
        return this;
    }

    public CartPage clickCartButton() {
        CART_BUTTON.shouldBe(visible).click();
        return CartPage.getInstance();
    }
}
