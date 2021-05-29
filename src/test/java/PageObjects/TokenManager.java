package PageObjects;

import com.qa.baseConfig.EnvManager;
import com.qa.helper.HelperApi;
import com.qa.helper.HelperLog;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.LocalTime;

public class TokenManager extends BasePage {

    //Initializing Logger and waits for the page
    static Logger log = HelperLog.getLogger();
    static HelperLog logger = new HelperLog();

    /**
     * Creating constructor and passing WebDriver
     * The passed WebDriver will be assigned to Class member WebDriver
     * Same Class member WebDriver can be used throughout the class
     */
    public TokenManager(WebDriver driver) {
        super(driver);
    }

    private LocalTime now = LocalTime.now();



    /**
     * Accepts
     *
     * @param email
     * @param password
     * @return token if success else returns null
     */
    public String getToken(String email, String password) {
        log.info("Time Now:" + now);
        log.info("Token generation Time:" + tokenGenerationTime);
//        HelperApi helperApi = new HelperApi();
        /**
         * Checking if the token is still valid If not then only it will
         * generate else it will reuse
         */
        if (authToken == null) {
            if (tokenGenerationTime == null || tokenGenerationTime.isBefore(now.minusMinutes(30))) {
                log.info("Generating New Token");
                String tokenEndpoint = EnvManager.envProperties.get("endpoint.token");
                RequestSpecification requestSpecification = RestAssured.given().baseUri(tokenEndpoint);
                Response resp = requestSpecification
                        .formParam("grant_type", "password")
                        .formParam("username", email)
                        .formParam("password", password)
                        .log().uri().log().params()
                        .when()
                        .post()
                        .then()
                        .extract().response();
                log.info(resp.asString());

                if (resp.statusCode() == 200) {
                    // Logging time for token generation
                    tokenGenerationTime = LocalTime.now();
                    authToken = HelperApi.jsonToObjectByJsonPath(resp.asString(), "access_token").toString();
                } else {
                    log.info("Failed to generate token. Error: " + resp.jsonPath().get("error"));
                    authToken = null;
                }

            } else {
                log.info("Token is still valid, reusing it. TOKEN= " + authToken);
            }
        } else {
            log.info("Custom token from {env}.properties will be used: TOKEN=" + authToken);
        }

        return authToken;
    }


}
