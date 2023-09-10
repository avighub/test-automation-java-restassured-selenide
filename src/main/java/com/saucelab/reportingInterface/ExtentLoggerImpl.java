package com.saucelab.reportingInterface;

import com.saucelab.baseConfig.BaseTest;

/**
 * This is an implementation class of Loggable Interface
 */
public class ExtentLoggerImpl extends BaseTest implements Loggable {

    @Override
    public void log(String status, String message) {
        if (status.equalsIgnoreCase("pass")) {
            extentTest.get().pass(message);
        } else if (status.equalsIgnoreCase("fail")) {
            extentTest.get().fail(message);
        }
    }
}
