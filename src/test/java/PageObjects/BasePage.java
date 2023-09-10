package PageObjects;

import com.saucelab.baseConfig.BaseTest;
import com.saucelab.baseConfig.EnvManager;
import com.saucelab.webdriver.UtilSelenium;
import com.saucelab.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BasePage extends BaseTest {
    /**
     * Can be accessed from any class any package within Framework
     * Can not be modified, a constant
     */
    public WebDriver driver;
    public WebDriverWait pageWait;

    public BasePage() {
        driver = null;
    }

//    public String baseUrlUI;

    /**
     * Creating constructor and passing WebDriver
     * The passed WebDriver will be assigned to Class member WebDriver
     * Same Class member WebDriver can be used throughout the class
     * baseUrlUI will be default from {env}.properties
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        pageWait = new WebDriverWait(this.driver, 10);
        baseUrlUI = EnvManager.envProperties.get("baseUrlUI");
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void terminateActiveWebDriverProcess() {
        log.info("**** AfterSuite : BaseTestTemplate ****");
        UtilSelenium.terminateWebDriverProcess();
    }

}
