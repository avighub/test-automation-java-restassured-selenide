package PageObjects;

import com.qa.helper.HelperLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    HelperLog logger = new HelperLog();
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        logger.info("=== Added products to the cart ===");
        return pageWait.until(ExpectedConditions.urlContains("checkout-complete.html"));
    }
}
