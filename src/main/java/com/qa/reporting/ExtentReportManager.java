package com.qa.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.baseConfig.BaseTest;
import com.qa.baseConfig.EnvManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExtentReportManager extends BaseTest {

    // Instantiate ExtentReports class
    public static ExtentReports extentReport;

    //Extent 3.1.3
    public KlovReporter klovReporter;
    public ExtentHtmlReporter htmlReporter;

    //-------Report info------
    //Getting below info from config.properties
    public String ExtentReportOption = EnvManager.configProperties.get("ExtentReportOption");
    public String projectName = EnvManager.configProperties.get("ApplicationName");

    //Creating current timestamp for report name
    public String date = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());

    //Getting below report info from Environment value(passes as Env param during loal run or param from Jenkins)
    Map<String, String> env = System.getenv();
    public String AppBuildVersion = env.get("AppBuildVersion");
    public String reportName = env.get("ReportName");

    //==================Setting up KLOV configs===========
    // ExtentReportOption and KLOVReportLocation value fetched from config.properties
    public KlovReporter setupKlovReporter() {

        //Checking if AppBuildVersion and reportName is not passed in env variable , will set to default value from config.properties
        if (AppBuildVersion == null) {
            AppBuildVersion = EnvManager.configProperties.get("AppBuildVersion");
        }
        klovReporter = new KlovReporter(projectName, "V" + AppBuildVersion + " - " + date);

        // address, host/port of MongoDB
        if (EnvManager.configProperties.get("KLOVReportLocation").equalsIgnoreCase("remote")) {
            String KLOVRemoteHost = EnvManager.configProperties.get("KLOVRemoteHost");
            int KLOVRemoteHostPort = Integer.parseInt(EnvManager.configProperties.get("KLOVRemoteHostPort"));
            // klov MongoDB Conn
            klovReporter.initMongoDbConnection(KLOVRemoteHost, KLOVRemoteHostPort);

            // Klov server address
            klovReporter.initKlovServerConnection(KLOVRemoteHost);
            log.info("=== KLOV REPORT HOST: REMOTE === ");
        } else {
            int KLOVLocalHostPort = Integer.parseInt(EnvManager.configProperties.get("KLOVLocalHostPort"));

            // klov MongoDB Conn
            klovReporter.initMongoDbConnection("localhost", KLOVLocalHostPort);

            // Klov server address
            klovReporter.initKlovServerConnection("http://localhost");
            log.info("=== KLOV REPORT HOST: LOCAL === ");
        }
        return klovReporter;
    }

    //Creating ExtentReport instance
    public ExtentReports createInstance() {
        // If TRUE: Report will be pushed to KLOV(remote/local) only
        // If FALSE: Report will be pushed to both KLOV and HTML
        if (ExtentReportOption.equalsIgnoreCase("klov")) {

            //Calling KLOV setup method
            setupKlovReporter();

            // create object of ExtentReports
            extentReport = new ExtentReports();
            extentReport.attachReporter(klovReporter);
            log.info("=== KLOV REPORT ENABLED === ");

        } else {
            //******** HTML Report Configuration  *******

            // location of the extent report - Local HTML File
            String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport";
            String reportFileName = File.separator + "AutomationReport_" + date + ".html";
            String fullReportPath = reportPath + reportFileName;

            Path path = Paths.get(reportPath);
            if (Files.exists(path)) {
                log.info("=== REPORT PATH : " + fullReportPath);
            } else {
                File directory = new File(reportPath);
                directory.mkdir();
                log.info("=== REPORT PATH : " + fullReportPath);
            }
            File report = new File(reportPath);
            reportDirectoryCleanUp(report, 10);
            htmlReporter = new ExtentHtmlReporter(fullReportPath);

            // Title of the report
            htmlReporter.config().setDocumentTitle(projectName);

            // Name of the report
            //Checking if AppBuildVersion is not passed in env variable , will set to default value from config.properties
            if (reportName == null) {
                reportName = EnvManager.configProperties.get("ReportName");
            }
            htmlReporter.config().setReportName("V" + AppBuildVersion + "_" + reportName);
            htmlReporter.config().setTheme(Theme.STANDARD);

            //kLOV Setup
//            setupKlovReporter();

            extentReport = new ExtentReports(); // create object of ExtentReports
//            extentReport.attachReporter(htmlReporter, klovReporter);
            extentReport.attachReporter(htmlReporter);
            log.info("=== HTML EXTENT REPORT ENABLED: LOCAL === ");
        }

        // General information releated to application
        extentReport.setSystemInfo("Environment", EnvManager.configProperties.get("test.environment"));
        extentReport.setSystemInfo("Test Layer", EnvManager.configProperties.get("TestLayer"));
        extentReport.setSystemInfo("Browser", EnvManager.configProperties.get("browser.code"));

        return extentReport;
    }

    public static void reportDirectoryCleanUp(File reportDir, int numOfReports) {
        if (reportDir.isDirectory()) {
            List<String> reports = Arrays.asList(reportDir.list());
            System.out.println("reports.size() :" + reports.size());
            if (reports.size() > numOfReports) {

                for (int i = 0; i < reports.size(); i++) {

                    List<String> reports1 = Arrays.asList(reportDir.list());

                    if (reports1.size() > numOfReports) {
                        Collections.sort(reports);
                        File oldreport = new File(reportDir, reports.get(i));
                        if (oldreport.delete()) {
                            System.out.println("Deleted an old report - " + reports.get(i));
                        }
                    } else
                        break;
                }
            }
        }
    }

    public static String takeScreenshotAsBase64(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BASE64);
    }
}
