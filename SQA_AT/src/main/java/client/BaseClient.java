package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    protected RequestSpecification getBaseRequestSpecification(ContentType contentType) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true)))
                .setBaseUri(BASE_URL)
                .setAccept(contentType)
                .setContentType(contentType);

        return builder.build();
    }

    protected RequestSpecification getBaseRequestSpecification() {
        return getBaseRequestSpecification(ContentType.JSON);
    }
}
