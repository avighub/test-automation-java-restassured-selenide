package testCases.api;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.saucelab.baseConfig.BaseTest;
import com.saucelab.baseConfig.EnvManager;
import com.saucelab.helper.HelperLog;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListUsersTest extends BaseTest {

    HelperLog log = new HelperLog();
    String userEndpoint; // ALl the tests will use same endpoint

    @BeforeClass(alwaysRun = true)
    public void initEndpoints() {
        userEndpoint = EnvManager.envProperties.get("endpoint.listUsers");
    }


//    @Test(description = "Verify list user", groups = {"api", "api2233"})
    public void listUsersOfPageOne() {
        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig();

        response = RestAssured
                .given()
                .config(config)
                .baseUri(baseUrlAPI)
//                .log().everything()
                .when()
                .get(userEndpoint)
                .then()
//                .log().everything()
                .statusCode(200)
                .extract().response();

        // Custom message to be added in Extent report and log both
        log.info("Verify List user passed");
    }

//    @Test(groups = {"api", "ui"})
    public void failedTest() {
        System.out.println("This is a failed Test");
        Assert.assertEquals(1, 2);
    }
}
