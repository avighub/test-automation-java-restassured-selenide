package PageObjects;

import com.saucelab.baseConfig.EnvManager;
import com.saucelab.helper.HelperLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    //Initializing Logger and waits for the page
    HelperLog logger = new HelperLog();
    int waitTimeMax = Integer.parseInt(EnvManager.configProperties.get("waitMax"));

    //Passing driver to super class(BasePageTemplate)
    //Initializing PageObjects
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Web Elements
    @FindBy(css = "#user-name")
    WebElement userField;

    @FindBy(css = "#password")
    WebElement passField;

    @FindBy(css = "#login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//div[@class='login_logo']")
    WebElement swagLabsLogo;

    public LoginPage visit() {
        driver.get(baseUrlUI);
        return this;
    }

    public InventoryPage login(String username, String password) {
        userField.sendKeys(username);
        passField.sendKeys(password);
        loginBtn.click();
        logger.info("=== Username , password enetered and Clicked on Login button ===");
        return new InventoryPage(driver);
    }

    public boolean isLoaded() {
        logger.info("=== Looking for SauceBot Logo ===");
        return pageWait.until(ExpectedConditions.visibilityOf(swagLabsLogo)).isDisplayed();
    }

}
