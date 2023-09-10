package testCases.util;

import com.saucelab.baseConfig.BaseTest;
import com.saucelab.reportingInterface.ConsoleLoggerImpl;
import com.saucelab.reportingInterface.ExtentLoggerImpl;
import com.saucelab.reportingJava8.FrameworkLogger;
import com.saucelab.reportingJava8.LogType;
import org.testng.annotations.Test;

public class ReportingTest extends BaseTest {

    @Test(groups = {"extentreport"})
    public void testExtentReportWithInterfaceImpl() {
        // Using Implementation way
        new ExtentLoggerImpl().log("pass", "This is a pass message coming from LoggerImpl");
        new ExtentLoggerImpl().log("fail", "This is a fail message coming from LoggerImpl");
        new ConsoleLoggerImpl().log("pass", "This is a console Log from Console Impl");

        // Old way
        extentTest.get().pass("This is coming from Default FW logger");
    }

    @Test(groups = {"extentreport"})
    public void testExtentReportWithInterfaceImplJava8() {
        // Using JAVA8 Implementation way
        new ExtentLoggerImpl().log("pass", "This is a pass message coming from LoggerImpl");
        new ExtentLoggerImpl().log("fail", "This is a fail message coming from LoggerImpl");
        new ConsoleLoggerImpl().log("pass", "This is a console Log from Console Impl");

        // Old way
        extentTest.get().pass("This is coming from Default FW logger");

        // JAVA8 way
        FrameworkLogger.log(LogType.PASS, "This message is coming from JAVA8 logger");
        FrameworkLogger.log(LogType.FAIL, "This message is coming from JAVA8 logger");
        FrameworkLogger.log(LogType.SKIP, "This message is coming from JAVA8 logger");
        FrameworkLogger.log(LogType.CONSOLE, "ONLY CONSOLE - This message is coming from JAVA8 logger");
        FrameworkLogger.log(LogType.EXTENTANDCONSOLE, "EXTENT AND CONSOLE -- This message is coming from JAVA8 logger");
    }

}
