package com.tricentis.demowebshop.pageobject;

import general.BaseGeneralPage;
import com.tricentis.demowebshop.models.LoginModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebShopRegisterPage extends BaseGeneralPage {

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadiobutton;

    @FindBy(id = "gender-female")
    private WebElement genderFemaleRadiobutton;

    @FindBy(id = "FirstName")
    private WebElement firstNameInput;

    @FindBy(id = "LastName")
    private WebElement lastNameInput;

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    public LoginModel registerUser(String firstName, String lastName, String email, String password) {
        genderFemaleRadiobutton.click();
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        registerButton.click();
        return new LoginModel(email, password);
    }
}
