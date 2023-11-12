package api;

import client.UserClient;
import io.restassured.response.Response;
import model.User;
import model.UserBuilder;
import org.testng.annotations.Test;
import java.util.List;

import static assertion.BaseAssertion.check200Response;

public class UserTest {

    private UserClient userClient = new UserClient();

    @Test
    public void createUser() {
        User user = new UserBuilder().constructRandomValidUser();
        Response response = userClient.create(user);
        check200Response(response);
    }

    @Test
    public void loginUser() {
        User user = new UserBuilder().constructRandomValidUser();
        Response response = userClient.login(user.getUsername(), user.getPassword());
        check200Response(response);
    }

    @Test
    public void createUserList() {
        List<User> randomUsers = new UserBuilder().constructRandomListValidUsers(3);
        Response response = userClient.createUsersByProvidedUsersList(randomUsers);
        check200Response(response);
    }

    @Test
    public void logoutUser() {
        Response response = userClient.logout();
        check200Response(response);
    }
}
