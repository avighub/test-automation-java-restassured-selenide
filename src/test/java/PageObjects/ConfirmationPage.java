package PageObjects;

import com.qa.helper.HelperLog;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationPage extends BasePage {
    HelperLog logger = new HelperLog();

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    //TODO the back part of the URL is being duplicated and can probably be extracted into
    //a property that keeps track of checkout-step-two.html
    public void visit() {
        driver.navigate().to(baseUrlUI + "/checkout-step-two.html");
        logger.info("=== Navigated to Check out step two page ===");
    }

    public void setPageState() {
        //TODO what are you going to do with the JS executor duplication
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.setItem('standard-username', 'standard-user')");
        ((JavascriptExecutor) driver).executeScript("window.localStorage.setItem('cart-contents', '[4,1]')");
        driver.navigate().refresh();
        logger.info("=== Added products to the cart ===");
    }

    public Boolean hasItems() {
        String cartBadge = ".shopping_cart_badge";
        logger.info("=== Checking if Cart has any items ===");
        return Integer.parseInt(driver.findElement(By.cssSelector(cartBadge)).getText()) > 0;
    }

    public CheckoutCompletePage finishCheckout() {
        String finishedCheckoutLocator = "#finish";
        WebElement finishButton = driver.findElement(By.cssSelector(finishedCheckoutLocator));
        finishButton.click();
        logger.info("=== Clicked on Finish button ===");
        return new CheckoutCompletePage(driver);
    }

    public boolean isLoaded() {
        logger.info("=== Checking for Check out steop two page ===");
        return pageWait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }
}
