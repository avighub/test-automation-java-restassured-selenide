package tests.web;

import com.codeborne.selenide.junit5.TextReportExtension;
import com.saucelab.pages.InventoryPage;
import com.saucelab.pages.LoginPage;
import com.saucelab.pages.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.basesetup.WebTestSetup;

@ExtendWith({TextReportExtension.class})
public class InventoryPageTest extends WebTestSetup {


    @Test
    public void shouldLoadAllTheHeaderElements()  {
        LoginPage.getInstance()
                .visit()
                .loginAs(UserRole.STANDARD_USER)
                .checkHeaderElementsArePresent()
                .checkInventoryPageHeaderElementsArePresent();
    }

    // This test is to compare both ways of navigating to Inventory page
    @Test
    public void shouldLoadAllTheHeaderElements2()  {
        InventoryPage.getInstance()
                .visit()
                .checkHeaderElementsArePresent()
                .checkInventoryPageHeaderElementsArePresent();
    }

}
