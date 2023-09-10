package com.saucelab.helper;


import com.saucelab.baseConfig.BaseTest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class HelperLog extends BaseTest {
    public static Logger log = Logger.getLogger(HelperLog.class.getName());

    /**
     * This methods accepts the log level from {env}.properties
     * and sets as per the level provoded
     */
    public static Level setLogLevel(String level) {
        Level logLevel = null;

        switch (level.toLowerCase()) {
            case "info":
                logLevel = Level.INFO;
                break;
            case "error":
                logLevel = Level.ERROR;
                break;
            case "debug":
                logLevel = Level.DEBUG;
                break;
            default:
                logLevel = Level.ALL;
        }

        return logLevel;
    }

    public HelperLog() {
        PropertyConfigurator.configure("Log4j.properties");
    }

    // TODO : Use this method to provide class name for Logging precision
    public static Logger getLogger() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        /*
         * stackTrace[0] is for Thread.currentThread().getStackTrace() stackTrace[1] is for this method log()
         */
        String className = stackTrace[2].getClassName();
        log.info(className);

        return Logger.getLogger(HelperLog.class.getName());
    }

    public void info(String message) {
//        log.info(message);
//        extentTest.get().info(message);
    }

    public void warn(String message) {
//        log.warn(message);
//        extentTest.get().warning(message);
    }

    public void fail(String message) {
//        log.error(message);
//        extentTest.get().fail(message);
    }

    public void pass(String message) {
//        log.info(message);
//        extentTest.get().pass(message);
    }

}
