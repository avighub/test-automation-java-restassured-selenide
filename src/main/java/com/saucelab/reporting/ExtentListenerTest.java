package com.saucelab.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.saucelab.baseConfig.BaseTest;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ExtentListenerTest implements ITestListener {

    // Initialize Log4j logs
    public static Logger log = Logger.getLogger(ExtentListenerTest.class.getName());
    // Initialize ExtentReports
    public static ExtentReports extentReport = null;
    // Instantiate ExtentReports class
    ExtentReportManager extentReportManager;

    // This will be executed before Any test, but once
    public void onStart(ITestContext context) {
        extentReportManager = new ExtentReportManager();
        extentReport = extentReportManager.createInstance();
    }

    // This will be executed before each Test start
    public void onTestStart(ITestResult result) {
        log.info("===========================================================");
        log.info("Test Case Execution Started,Test Name is:\t" + result.getName());
        log.info("===========================================================");

        // For each test method , it will generate Class name + method name
        ExtentTest test = extentReport.createTest(result.getMethod().getMethodName());
//        extentTest.set(test);
//        extentTest.get().log(Status.INFO, "<b>Test Description :</b> " + result.getMethod().getDescription());
//        extentTest.get().assignCategory(result.getMethod().getGroups());
    }

    // This will be executed for each Test if only Test is PASSED
    public void onTestSuccess(ITestResult result) {
        if (result.getParameters().length > 0) {
            String paramName = Arrays.asList(result.getParameters()).toString();
//            extentTest.get().log(Status.INFO, "<b>Package:</b> " + result.getTestClass().getName());
//            extentTest.get().log(Status.INFO, "<b>Test Data: </b>" + paramName);
//            extentTest.get().log(Status.PASS, MarkupHelper
//                    .createLabel(result.getMethod().getMethodName() + " Test Case PASSED", ExtentColor.GREEN));
            log.info("===========================================================");
            log.info("Test Case Executed Sucesfully,Test Name is:\t" + result.getName());
            log.info("===========================================================");
        } else {
//            extentTest.get().log(Status.INFO, "<b>Package: </b>" + result.getTestClass().getName());
//            extentTest.get().log(Status.PASS, MarkupHelper
//                    .createLabel(result.getMethod().getMethodName() + " Test Case PASSED", ExtentColor.GREEN));
            log.info("===========================================================");
            log.info("Test Case Executed Sucesfully,Test Name is:\t" + result.getName());
            log.info("===========================================================");
        }

    }

    // This will be executed for each Test if only Test is FAILED
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
//        extentTest.get().log(Status.INFO, "<b>Package: </b>" + result.getTestClass().getName());
//        extentTest.get().log(Status.FAIL,
//                MarkupHelper.createLabel(methodName + " - Test Case FAILED", ExtentColor.RED));
//        extentTest.get().fail("<b>Click on the log below to expand...</b>");
//        extentTest.get().log(Status.FAIL, result.getThrowable());
        log.info("===========================================================");
        log.info("Test Case Execution Got Failed,Test Name is:\t" + result.getName());
        log.info("===========================================================");
//        extentTest.get().addScreenCaptureFromBase64String(ExtentReportManager.takeScreenshotAsBase64(driver));

    }

    public void onTestSkipped(ITestResult result) {
//        extentTest.get().log(Status.INFO, "<b>Package:</b> " + result.getTestClass().getName());
//        extentTest.get().log(Status.SKIP, MarkupHelper
//                .createLabel(result.getMethod().getMethodName() + " Test Case SKIPPED", ExtentColor.ORANGE));
        log.info("===========================================================");
        log.info("Test Case is Skipped,Test Name is:\t" + result.getName());
        log.info("===========================================================");
    }

    public void onFinish(ITestContext context) {
        log.info("========== All Testcase execution ended ==========");
        if (extentReport != null) {
            extentReport.flush();
        }

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub
    }


}
