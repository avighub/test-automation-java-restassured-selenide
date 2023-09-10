package com.saucelab.baseConfig;

import com.saucelab.helper.HelperReadFile;
import com.saucelab.helper.HelperLog;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class reads the frameworkconfig.properties file and stores each key as class object
 * So that these keys can be available with ClassName
 */
public class EnvManager {

    /**
     * Step 1: Reads the frameworkconfig.properties file and stores the key value in Map
     * Step 2: Reads the Environment properties from {env}.properties based on the Env present in frameworkconfig.properties
     * Step 3: Sets the environment either from properties file or System environment variable availability
     */

    public static Map<String, String> configProperties = new HashMap<String, String>();
    public static Logger log = HelperLog.getLogger();



    /**
     * This method takes {environment} as parameter
     * and reads the respective {environment}.properties file
     * and stored in HashMap {envProperties}
     */
    public static Map<String, String> envProperties = new HashMap<>();



}
