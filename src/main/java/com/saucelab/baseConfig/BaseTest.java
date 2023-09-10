package com.saucelab.baseConfig;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.saucelab.config.EnvironmentConfig;
import com.saucelab.config.FrameworkConfig;
import com.saucelab.helper.HelperLog;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.PrintStream;
import java.io.StringWriter;
import java.time.LocalTime;

public class BaseTest {
    /**
     * - BaseTestTemplate class is responsible for common properties
     * required for UI or API tests.
     * - Define the API or UI specific configurations in BaseTest class inside  test/java
     * - By Default API setup is done in this class
     * - Define WebDriver specific setup in only UI specific Test classes
     */

    // Environment
    public static String environment = null;

    // Rest Assured and selenium
    public Response response;
    public String baseUrl;
    public String baseUrlAPI;

    // Utilities and helpers
    public static LocalTime tokenGenerationTime = null;
    public static String authToken = null;
    public static Logger log;

    // ExtentTest threadLocal to be able to use in parallel testing
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    protected StringWriter writer;
    protected PrintStream captor;


    @BeforeSuite(alwaysRun = true)
    public void setupBeforeSuite() {
        // ------- Initializing Config properties ------
        FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
        environment = frameworkConfig.environment();

        // ------- START OF LOG INIT -------
        log = Logger.getLogger("");
        PropertyConfigurator.configure("Log4j.properties");


    }

    @BeforeClass(alwaysRun = true)
    public void setupBeforeClass() {
        log.info("**** BeforeClass: BaseTestTemplate ****");
        /**
         * - By default Global Rest Assured Request object is created to be used
         * - If any test specific request specification is needed
         * create is separately inside Test method
         */
        FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
        ConfigFactory.setProperty("environment", frameworkConfig.environment());
        EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

        // Defining RestAssured configurations
        baseUrl = environmentConfig.baseUrl();
        log.info("baseUrl: " + baseUrl);
    }

//    @BeforeMethod(alwaysRun = true)
    public void appendRequestInfoToReport() {
        // TODO : Add request details in report
        // Logging Request details to report
    }

    /**
     * After every Test response will be saved in Extent report
     */
//    @AfterMethod(alwaysRun = true)
    public void appendResponseToReport() {
        log.info("**** AfterMethod: TestBase ****");
        try {
            extentTest.get().info(MarkupHelper.createCodeBlock(response.asString()));
            extentTest.get().pass(MarkupHelper.createCodeBlock(response.getHeaders().toString()));
            log.info("<<< Response is added in Extent report >>>");
            response = null;

        } catch (Exception e) {
            extentTest.get().info("No content found in response body. Could be a UI Test or API has returned NULL response. <br> Exception: " + e);
            log.info("<<< No content in response body. Response could not be added in Extent report >>>");
        }

    }

}
