package api;

import client.UserClient;
import io.restassured.response.Response;
import model.User;
import model.UserBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class UserTest {

    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void createUser() {
        User user = new UserBuilder().constructRandomValidUser();
        Response response = userClient.create(user);
        assertEquals(response.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
    }

    @Test
    public void loginUser() {
        User user = new UserBuilder().constructRandomValidUser();
        Response response = userClient.login(user.getUsername(), user.getPassword());
        assertEquals(response.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
    }

    @Test
    public void createUserList() {
        List<User> randomUsers = new UserBuilder().constructRandomListValidUsers(3);
        Response response = userClient.createWithList(randomUsers);
        assertEquals(response.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
    }

    @Test
    public void logoutUser() {
        Response response = userClient.logout();
        assertEquals(response.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
    }
}
