package tests.api;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;

public class ListUsersTest  {

    String userEndpoint; // ALl the tests will use same endpoint



//    @Test(description = "Verify list user", groups = {"api", "api2233"})
//    public void listUsersOfPageOne() {
//        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig();
//
//        response = RestAssured
//                .given()
//                .config(config)
//                .baseUri(baseUrlAPI)
////                .log().everything()
//                .when()
//                .get(userEndpoint)
//                .then()
////                .log().everything()
//                .statusCode(200)
//                .extract().response();
//
//        // Custom message to be added in Extent report and log both
//        log.info("Verify List user passed");
//    }

//    @Test(groups = {"api", "ui"})
    public void failedTest() {
        System.out.println("This is a failed Test");
        Assert.assertEquals(1, 2);
    }
}
