package com.saucelab.reportingInterface;

/**
 * It has only one abstract method, can be called as functional interface
 */
public interface Loggable {

    abstract void log(String status, String message);


}
