package com.saucelab.webdriver;

import com.saucelab.baseConfig.BaseTest;
import com.saucelab.baseConfig.EnvManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory extends BaseTest {


    /**
     * This method can be called in BeforeMethod for each Test
     *
     * @param environment
     * @param browser
     * @param browserMode
     */
    //TODO : Remove static and make public final for parallelization
    public static final String BROWSER = EnvManager.configProperties.get("test.browser");
    public static final String BROWSER_MODE = EnvManager.configProperties.get("test.browserMode");
    protected WebDriver driver;

    public WebDriver getDriver() {

        // Based on the values provided in config.properties, webdriver session will be created
        if (BROWSER.equalsIgnoreCase("chrome")) {
            // To remove the time out error caused due to renderer
            System.setProperty("webdriver.chrome.silentOutput", "true");
            log.info("Browser= Chrome");
            // Using webDriver manager dependency
            WebDriverManager.chromedriver().setup();

            // System.setProperty("webdriver.chrome.driver", EnvironmentVariables.fileAndEnv(environment).get("chromeDriverPath")); // Use this only if using webdriver exe file
            if (BROWSER_MODE.equalsIgnoreCase("headless")) {
                log.info("BrowserMode= Headless");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-logging");
                options.addArguments("--log-level=3");
                options.addArguments("window-size=1920,1080");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(options);
            } else {
                log.info("BrowserMode= Headfull");
                // Configuring Chrome options
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(options);
            }

        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            // To remove the time out error caused due to renderer
            System.setProperty("webdriver.chrome.silentOutput", "true");
            log.info("Browser= firefox");
            // Using webDriver manager dependency
            WebDriverManager.firefoxdriver().setup();

            // System.setProperty("webdriver.chrome.driver", EnvironmentVariables.fileAndEnv(environment).get("chromeDriverPath")); // Use this only if using webdriver exe file
            if (BROWSER_MODE.equalsIgnoreCase("headless")) {
                log.info("BrowserMode= Headless");
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-logging");
                options.addArguments("--log-level=3");
                options.addArguments("window-size=1920,1080");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(options);
            } else {
                log.info("BrowserMode= Headfull");
                // Configuring Chrome options
                FirefoxOptions options = new FirefoxOptions();
//                options.addArguments("start-maximized");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
            }
        }

        return driver;
    }

}
