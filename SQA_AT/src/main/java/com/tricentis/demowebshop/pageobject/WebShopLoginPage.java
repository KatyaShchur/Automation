package com.tricentis.demowebshop.pageobject;

import general.BaseGeneralPage;
import com.tricentis.demowebshop.models.LoginModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebShopLoginPage extends BaseGeneralPage {

    @FindBy(css = ".register-button")
    private WebElement registerButton;

    @FindBy (id = "Email")
    private WebElement emailInput;

    @FindBy (id = "Password")
    private WebElement passwordInput;

    @FindBy (css = ".login-button")
    private WebElement loginButton;

    public WebShopRegisterPage clickRegisterButton() {
        registerButton.click();
        return new WebShopRegisterPage();
    }

    public WebShopMainPage login(LoginModel loginModel) {
        emailInput.sendKeys(loginModel.getEmail());
        passwordInput.sendKeys(loginModel.getPassword());
        loginButton.click();
        return new WebShopMainPage();
    }
}
