package unittest.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonPayloadCreateUsingMap {

    @Test
    public void createSimpleJsonPayloadUsingMap() {
        String baseUrl = "https://reqres.in/api";
        Map<String, String> payload = new HashMap<>();
        payload.put("email", "eve.holt@reqres.in");
        payload.put("password", "cityslicka");

        RestAssured.given()
                .baseUri(baseUrl)
                .body(payload)
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .post("login")
                .then()
                .log().all();

    }

    @Test
    public void createSingleArrayJsonPayloadUsingMap() {
        String baseUrl = "https://reqres.in/api";
        Map<String, Object> payloadFull = new HashMap<>();
        payloadFull.put("email", "eve.holt@reqres.in");
        payloadFull.put("password", "cityslicka");

        Map<String, String> payloadAddress = new HashMap<>();
        payloadAddress.put("City", "Bangalore");
        payloadAddress.put("State", "Karnataka");
        payloadFull.put("Address",payloadAddress);

        RestAssured.given()
                .baseUri(baseUrl)
                .body(payloadFull)
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .post("login")
                .then()
                .log().all();

    }
    @Test
    public void createMultiArrayJsonPayloadUsingMap() {
        String baseUrl = "https://reqres.in/api";
        Map<String, Object> payloadFull = new HashMap<>();
        payloadFull.put("email", "eve.holt@reqres.in");
        payloadFull.put("password", "cityslicka");

        Map<String, String> payloadAddress = new HashMap<>();
        payloadAddress.put("City", "Bangalore");
        payloadAddress.put("State", "Karnataka");

        Map<String, String> payloadAddress2 = new HashMap<>();
        payloadAddress2.put("City", "Hyderabad");
        payloadAddress2.put("State", "Telengana");

        ArrayList<Object> addressList=new ArrayList<>();
        addressList.add(payloadAddress);
        addressList.add(payloadAddress2);

        payloadFull.put("Address",addressList);

        RestAssured.given()
                .baseUri(baseUrl)
                .body(payloadFull)
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .post("login")
                .then()
                .log().all();

    }


}
