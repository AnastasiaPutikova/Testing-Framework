package framework.api;

import static io.restassured.RestAssured.given;

import framework.util.config.ConfigManager;
import framework.util.logger.LoggerManager;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class APIManager {
    public Response doGet(String url) {
        LoggerManager.logInfo("Get request to " + url);
        return given()
                .when()
                .get(url);
    }

    public Response doPost(Object requestBody, String url) {
        LoggerManager.logInfo("Post request to " + url);
        return given()
                .header(ConfigManager.getStringValueOf("contentType"),
                        ConfigManager.getStringValueOf("requestFormat"))
                .when()
                .body(requestBody)
                .post(url);
    }
}
