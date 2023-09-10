package com.saucelab.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;
import com.saucelab.baseConfig.BaseTest;
import org.apache.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;

import static io.restassured.RestAssured.given;

public class HelperApi extends BaseTest {
    //Initializing Logger and waits for the page
    static Logger log = HelperLog.getLogger();

    private HelperApi() {
        throw new IllegalStateException("Utility class can not be instantiated, Use it statically.");
    }

    /**
     * Uses everit dependency
     * Pros: Very easy to debug as it shows exact position where mismatch is found
     * in case of failure.
     * Schema file can be placed anywhere, independent of classpath
     */
    public static boolean validateJsonSchema(String jsonSchemaFilePath, String jsonString) {
        boolean validationFlag = false;
        try {
            JSONTokener schemaData = new JSONTokener(new FileInputStream(new File(jsonSchemaFilePath)));
            JSONObject jsonSchema = new JSONObject(schemaData);

            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(new JSONObject(jsonString));
            validationFlag = true;
        } catch (Exception e) {
            log.info("Schema validation failed: \n" + e);
        }
        return validationFlag;
    }

    /**
     * Accepts Json string and jsonPath
     * Returns Object ( Single object or Array Object)
     * Use toString() to save as String
     */
    public static Object jsonToObjectByJsonPath(String json, String jsonPath) {
        return JsonPath.read(json, jsonPath);
    }

    public static String getValueFromJsonString(String jsonString, String key) {
        // create gson object
        Gson gson = new Gson();

        // convert to json tree
        JsonElement jsonTree = gson.toJsonTree(jsonString);
        log.info(jsonTree.toString());

        // get value for a key
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        String value = jsonObject.get(key).getAsString();
        log.info(value);

        return value;

    }

    /**
     * This will get the object and return json String
     *
     * @param obj
     * @return String
     */
    public static String objToString(Object obj) {
        String pojoString = null;
        try {
            ObjectMapper objMapper = new ObjectMapper();
            pojoString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            log.info("*** Converted Object to String...***");
        } catch (Exception e) {
            log.info("*** Failed to Convert Object to String...*** Error: " + e);
        }
        return pojoString;
    }

    public static void uploadFile(String uploadUrl, String controlName, File file) {
        given()
                .multiPart(controlName, file)
                .when()
                .post(uploadUrl)
                .then()
                .statusCode(200)
                .log().all();
    }


}
