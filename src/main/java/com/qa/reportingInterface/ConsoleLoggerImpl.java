package com.qa.reportingInterface;

public class ConsoleLoggerImpl implements Loggable {


    @Override
    public void log(String status, String message) {
        System.out.println(status + "--->" + message);
    }
}
