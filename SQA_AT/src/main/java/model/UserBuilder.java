package model;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }


    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }


    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }


    public UserBuilder setUserStatus(int userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);

        return user;
    }

    public User constructRandomValidUser() {
        Faker faker = new Faker();
        String fakeUsername = faker.name().username();

        return this.setId(faker.number().numberBetween(0, 666))
                .setUserName(fakeUsername)
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(fakeUsername + faker.number().numberBetween(0, 666) + "@gmail.com")
                .setPassword(faker.pokemon().name())
                .setPhone(faker.phoneNumber().cellPhone())
                .setUserStatus(faker.number().numberBetween(0, 10))
                .build();
    }

    public List<User> constructRandomListValidUsers(int quantity) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            users.add(constructRandomValidUser());
        }

        return users;
    }
}
