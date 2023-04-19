package framework.api;

import framework.util.config.ConfigManager;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class BaseAPISteps {
    private String getContentType(String url) {
        return APIManager.doGet(url)
                .then()
                .extract().contentType();
    }

    public int getStatusCode(String url) {
        return APIManager.doGet(url)
                .then()
                .extract().statusCode();
    }

    public boolean isContentTypeJson(String url) {
        return Objects.equals(getContentType(url),
                ConfigManager.getStringValueOf("jsonContentType"));
    }
}
