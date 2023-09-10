package testCases.api;

import DataProvider.CreateUserDataProvider;
import com.saucelab.baseConfig.BaseTest;
import com.saucelab.baseConfig.EnvManager;
import com.saucelab.helper.HelperApi;
import com.saucelab.helper.HelperLog;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.CreateUserPojo;
import pojo.UserAddressPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateUserTest extends BaseTest {

    HelperLog log = new HelperLog();
    String userEndpoint; // ALl the tests will use same endpoint

//    @BeforeClass(alwaysRun = true)
    public void initEndpoints() {
        userEndpoint = EnvManager.envProperties.get("endpoint.listUsers");
    }

//    @Test(description = "Verify create user", groups = "api", dataProvider = "createUser", dataProviderClass = CreateUserDataProvider.class)
    public void createUsers(String name, String job) {
        //Using HasMap to construct JSON Request Body
        Map<String, String> reqBody = new HashMap<>();
        reqBody.put("name", name);
        reqBody.put("job", job);
        System.out.println(reqBody);
        response = RestAssured
                .given()
                .baseUri(baseUrlAPI)
                .body(reqBody)
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .post(userEndpoint)
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();

        log.info("Verify create user passed");
    }

//    @Test(groups = {"pojo"})
    public void createUserReqBodyUsingPojo() {
        String userEndpoint = EnvManager.envProperties.get("endpoint.listUsers");
        UserAddressPojo userAddressPojo = UserAddressPojo.builder()
                .city("Bangalore")
                .zip(555444)
                .build();
        CreateUserPojo createUserPojo = CreateUserPojo.builder()
                .name("Avishek")
                .job("Tester")
                .address(userAddressPojo)
                .build();


        response = RestAssured
                .given()
                .body(createUserPojo)
                .log().all()
                .when()
                .post(userEndpoint)
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        System.out.println(createUserPojo.toString());
        System.out.println(HelperApi.objToString(createUserPojo));
    }

//    @Test(groups = {"pojo"})
    public void createUserReqBodyUsingMapDataProvider(String id, String name, String city, String state, String locality, String zip, String brmYear, String brmMonth, String brmFinish) {

        Map<Object, Object> payloadMain = new HashMap<Object, Object>();
        Map<String, Object> athleteAddress = new HashMap<String, Object>();
        Map<String, Object> athleteBrmStatus1 = new HashMap<String, Object>();
        Map<String, Object> athleteBrmStatus2 = new HashMap<String, Object>();

        athleteAddress.put("city", city);
        athleteAddress.put("state", state);
        athleteAddress.put("locality", locality);
        athleteAddress.put("zip", zip);

        athleteBrmStatus1.put("brmYear", brmYear);
        athleteBrmStatus1.put("brmMonth", brmMonth);
        athleteBrmStatus1.put("brmFinish", brmFinish);

        athleteBrmStatus2.put("brmYear", 2021);
        athleteBrmStatus2.put("brmMonth", "May");
        athleteBrmStatus2.put("brmFinish", false);

        payloadMain.put("id", id);
        payloadMain.put("name", name);
        payloadMain.put("Address", athleteAddress);

        List<Object> brmStatsList = new ArrayList<Object>();
        brmStatsList.add(athleteBrmStatus1);
        brmStatsList.add(athleteBrmStatus2);

        payloadMain.put("brmStats", brmStatsList);

        System.out.println(HelperApi.objToString(payloadMain));
    }

}
