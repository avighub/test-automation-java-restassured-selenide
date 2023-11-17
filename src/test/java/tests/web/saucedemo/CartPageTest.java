package tests.web.saucedemo;

import annotations.feature.CartTest;
import com.saucelab.pages.InventoryPage;
import com.saucelab.pages.LoginPage;
import com.saucelab.pages.enums.UserRole;
import org.junit.jupiter.api.Test;
import tests.basetest.WebTestSetup;

@CartTest
public class CartPageTest extends WebTestSetup {


    @Test
    public void shouldLoadAllTheHeaderElements() {
        LoginPage.getInstance()
                .open()
                .loginAs(UserRole.STANDARD_USER)
                .checkHeaderElementsArePresent()
                .checkInventoryPageHeaderElementsArePresent();
    }

    // This test is to compare both ways of navigating to Inventory page
    @Test
    public void shouldLoadAllTheHeaderElements2() {
        InventoryPage.getInstance()
                .open()
                .checkHeaderElementsArePresent()
                .checkInventoryPageHeaderElementsArePresent();
    }

//    @Test
    public void shouldDisplayRemoveButtonAndUpdateItemCountInCartIfClickedOnAddToCart() {
        String itemName = "Sauce Labs Backpack";
        int expectedItemCount = 1;

        InventoryPage.getInstance()
                .open()
                .checkInventoryPageHeaderElementsArePresent()
                .clickAddToCartButton(itemName)
                .checkIfRemoveButtonIsPresent(itemName)
                .checkItemCountInCart(expectedItemCount);
    }

}
