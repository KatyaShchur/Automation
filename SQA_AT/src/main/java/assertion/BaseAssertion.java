package assertion;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import java.net.HttpURLConnection;

public class BaseAssertion {

    public static void checkResponse(Response response, int statusCode) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(response.statusCode())
                .as("Has status code")
                .isEqualTo(statusCode);
        softAssert.assertThat(response.contentType())
                .as("Has content type")
                .isEqualTo("application/json");
        softAssert.assertAll();
    }

    public static void check200Response(Response response) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(response.statusCode())
                .as("Has status code")
                .isEqualTo(HttpURLConnection.HTTP_OK);
        softAssert.assertThat(response.contentType())
                .as("Has content type")
                .isEqualTo("application/json");
        softAssert.assertAll();
    }

    public static void check404Response(Response response) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(response.statusCode())
                .as("Has status code")
                .isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        softAssert.assertAll();
    }
}
