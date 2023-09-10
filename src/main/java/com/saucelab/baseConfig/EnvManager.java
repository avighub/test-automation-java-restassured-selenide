package com.saucelab.baseConfig;

import com.saucelab.helper.HelperReadFile;
import com.saucelab.helper.HelperLog;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class reads the config.properties file and stores each key as class object
 * So that these keys can be available with ClassName
 */
public class EnvManager {

    /**
     * Step 1: Reads the config.properties file and stores the key value in Map
     * Step 2: Reads the Environment properties from {env}.properties based on the Env present in config.properties
     * Step 3: Sets the environment either from properties file or System environment variable availability
     */

    public static Map<String, String> configProperties = new HashMap<String, String>();
    public static Logger log = HelperLog.getLogger();

    // Get properties from config.properties and store in configProperties Map
    public static void initConfigProperties() {
        HelperReadFile readFile = new HelperReadFile();
        Properties prop = readFile.getPropertiesFromConfig();
        configProperties = HelperReadFile.storePropertiesToMap(prop);
        log.debug("== Config.properties: " + configProperties.toString());
    }

    /**
     * This method checks for system env variable {environment}
     * If provided during test run it assigns it else if it is null
     * it assigns the value from config.properties file
     */
    public static void setEnvironment() {
        Map<String, String> env = System.getenv();
        if (env.get("environment") == null) {
            log.info("*** System environment variable not found! config.properties value will be used... ***");
            BaseTest.environment = configProperties.get("test.environment");
        } else {
            log.info("*** System environment variable found! config.properties value will be ignored...***");
            BaseTest.environment = env.get("environment");
        }
        log.info("=== Test Environment : " + BaseTest.environment + " ===");
    }

    /**
     * This method takes {environment} as parameter
     * and reads the respective {environment}.properties file
     * and stored in HashMap {envProperties}
     */
    public static Map<String, String> envProperties = new HashMap<>();

    public static void initEnvProperties(String environment) {
        HelperReadFile readFile = new HelperReadFile();
        Properties envProp = readFile.getPropertiesFromEnvConfig(environment);
        envProperties = HelperReadFile.storePropertiesToMap(envProp);
        log.debug(envProperties);
    }


}
