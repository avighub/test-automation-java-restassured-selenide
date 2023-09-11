package tests.web;

import com.saucelab.pages.LoginPage;
import com.saucelab.pages.UserRole;
import org.junit.jupiter.api.Test;
import tests.basesetup.WebTestSetup;

public class InventoryPageTest extends WebTestSetup {


    @Test
    public void shouldLoadAllTheHeaderElements() {
        LoginPage.getInstance()
                .visit()
                .loginAs(UserRole.STANDARD_USER)
                .checkHeaderElementsArePresent();
    }

}
