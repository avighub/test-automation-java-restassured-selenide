package tests.web.saucelabs;

import annotations.feature.InventoryTest;
import com.saucelab.pages.InventoryPage;
import com.saucelab.pages.LoginPage;
import com.saucelab.pages.enums.UserRole;
import org.junit.jupiter.api.Test;
import tests.basesetup.WebTestSetup;

@InventoryTest
public class ProductsPageTest extends WebTestSetup {


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

//    @FlakyTest
//    @Test
    public void shouldDisplayRemoveButtonAndUpdateItemCountInCartIfClickedOnAddToCart() {
        String itemName = "Sauce Labs Backpack";
        int expectedItemCount = 1;

        InventoryPage.getInstance()
                .open()
                .checkHeaderElementsArePresent()
                .checkInventoryPageHeaderElementsArePresent()
                .clickAddToCartButton(itemName)
                .checkIfRemoveButtonIsPresent(itemName)
                .checkItemCountInCart(expectedItemCount);
    }

}
