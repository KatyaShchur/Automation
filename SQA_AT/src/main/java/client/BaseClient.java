package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {

    private final String baseUrl = "https://petstore.swagger.io/v2";

    protected RequestSpecification baseRequestSpecification(ContentType contentType) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true)))
                .setBaseUri(baseUrl)
                .setAccept(contentType)
                .setContentType(contentType);

        return builder.build();
    }

    protected final RequestSpecification baseRequestSpecification() {
        return baseRequestSpecification(ContentType.JSON);
    }
}
