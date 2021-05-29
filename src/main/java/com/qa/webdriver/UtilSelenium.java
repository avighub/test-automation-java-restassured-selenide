package com.qa.webdriver;

import com.qa.baseConfig.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class UtilSelenium extends BaseTest {
    public static String getSessionStorage(WebDriver driver, String key) {

        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        SessionStorage sessionStorage = webStorage.getSessionStorage();
        String sessionStorageValue = sessionStorage.getItem(key);

        return sessionStorageValue;
    }

    public static String getLocalStorage(String key, WebDriver driver) {

        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        String localStorageValue = localStorage.getItem(key);

        return localStorageValue;
    }

    public static boolean waitUntilClickable(WebDriver driver, WebElement element, int sec) {
        log.info("<<< waitUntilClickable() : start >>>");
        log.info("Waiting for the element : " + element);
        //Selenium Alpha release
//        return new WebDriverWait(driver, Duration.ofSeconds(sec)).until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
        //Selenium 3.141.59
        return new WebDriverWait(driver, sec).until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }

    public static boolean waitUntilVisible(WebDriver driver, WebElement element, int sec) {
        log.info("<<< waitUntilVisible() : start >>>");
        log.info("Waiting for the element : " + element);
        try {
            //Selenium Alpha release
//            return new WebDriverWait(driver, Duration.ofSeconds(sec)).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            //Selenium 3.141.59
            return new WebDriverWait(driver, sec).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void waitFluent(WebDriver driver, int sec) {
        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(sec))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("foo"));
            }
        });
    }

    public static void waitForPageLoad(WebDriver driver) {
        log.info("<<< Waiting for pageLoad: Start >>>");
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        //Selenium Alpha release
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        //Selenium 3.141.59
        WebDriverWait wait = new WebDriverWait(driver, 90);
        wait.until(pageLoadCondition);
        log.info("<<< Waiting for pageLoad: End >>>");
    }

    public static void waitForElementToDisappear(WebDriver driver, WebElement element) {
        log.info("<<< Waiting for the element to disappear >>>");

        //Selenium Alpha release
//        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.invisibilityOf(element));
        //Selenium 3.141.59
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(element));
    }

    public static String takeScreenshot(WebDriver driver, ITestResult result) {
        String destination = null;
        String date = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
        String methodName = result.getMethod().getMethodName();
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            destination = System.getProperty("user.dir") + File.separator + "ExtentReport" + File.separator + "screenshots" + File.separator + "Screenshot_" + date + "_" + methodName + "_" + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
            log.info("*** Screenshot taken successfully ***");
        } catch (Exception e) {
            log.info("*** Failed to take Screenshot *** Error: " + e);
        }

        return destination;
    }

    public static void terminateWebDriverProcess() {
        log.info("<<< WebDriver process cleanup >>>");
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                Process process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                process.destroy();
                log.info("All active WebDriver processes terminated !");
            } else {
                log.info("No active WebDriver process found!");
            }
        } catch (Exception e) {
            log.info("<<< Error occured while cleaning up WebDriver process." + e);

        }

    }
}
