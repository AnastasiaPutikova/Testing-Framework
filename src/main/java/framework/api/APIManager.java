package framework.api;

import static io.restassured.RestAssured.given;

import framework.util.config.ConfigManager;
import io.restassured.response.Response;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class APIManager {
    public Response doGet(String url) {
        return given()
                .when()
                .get(url);
    }

    public Response doPost(Object requestBody, String url) {
        return given()
                .header(ConfigManager.getStringValueOf("contentType"),
                        ConfigManager.getStringValueOf("requestFormat"))
                .when()
                .body(requestBody)
                .post(url);
    }

    public <T> List<T> getAllRecords(String url, Class<T> genericType) {
        return doGet(url)
                .then()
                .extract().body().jsonPath().getList(ConfigManager
                        .getStringValueOf("jsonRoot"), genericType);
    }
}
