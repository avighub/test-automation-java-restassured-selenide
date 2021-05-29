package testCases.api;

import com.qa.baseConfig.EnvManager;
import com.qa.helper.HelperLog;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListUsersTest extends com.qa.baseConfig.BaseTest {

    HelperLog log = new HelperLog();
    String userEndpoint; // ALl the tests will use same endpoint

    @BeforeClass(alwaysRun = true)
    public void initEndpoints() {
        userEndpoint = EnvManager.envProperties.get("endpoint.listUsers");
    }


    @Test(description = "Verify list user", groups ={"api","api2233"} )
    public void listUsersOfPageOne() {
        response = RestAssured
                .given()
                .log().all()
                .when()
                .get(userEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        // Custom message to be added in Extent report and log both
        log.info("Verify List user passed");
    }

}
