package com.saucelab.reportingJava8;

import com.saucelab.baseConfig.BaseTest;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Using Java8 functional programming to enhance more readability
 * and less code with more simplicity
 */
public class FrameworkLogger extends BaseTest {

    //private constructor to prevent instantiation to restrict making bad use
    private FrameworkLogger() {
    }

    // crating Consumer variables using Java 8
    private static Consumer<String> PASS = (message) -> extentTest.get().pass(message);
    private static Consumer<String> FAIL = (message) -> extentTest.get().fail(message);
    private static Consumer<String> SKIP = (message) -> extentTest.get().skip(message);
    private static Consumer<String> INFO = (message) -> extentTest.get().info(message);
    private static Consumer<String> CONSOLE = (message) -> System.out.println(message);
    private static Consumer<String> EXTENTANDCONSOLE = PASS.andThen(CONSOLE);

    /**
     * Creating a EnumMap to store and get status and message
     * Using LogType Enum to limit user providing wrong param from Test classes
     */
    private static Map<LogType, Consumer<String>> MAP = new EnumMap(LogType.class);

    /**
     * Loading this all static variables to memory before execution
     * We can Make LogType static import to make code more readable and easy to use
     */
    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL);
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);
    }

    /**
     * This method will be called from Test classes which accepts status and message
     * Then it gets the status from MAP and calls the respective static variable
     */

    public static void log(LogType status, String message) {
        MAP.get(status).accept(message);
    }

}
