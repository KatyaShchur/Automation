package com.epam.pageobject;

import general.BaseGeneralPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EpamAboutPage extends BaseGeneralPage {

    @FindBy(css = "[download]")
    private WebElement downloadButton;

    @FindBy (css = ".button-ui")
    private WebElement submitButton;

    @FindBy (css = "span[id*=user_first_name]")
    private WebElement userFirstName;

    @FindBy (css = "span[id*=user_last_name]")
    private WebElement userLastName;

    @FindBy (css = "span[id*=user_email]")
    private WebElement userEmail;

    @FindBy (css = "span[id*=user_phone]")
    private WebElement userPhone;

    public void clickDownloadButton() {
        scrollIntoView(downloadButton);
        clickJS(downloadButton);
        // small wait for download the file
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickSubmitButton() {
        scrollIntoView(submitButton);
        clickJS(submitButton);
    }

    public String getFirstNameValidationMessage() {
        return userFirstName.getText();
    }

    public String getLastNameValidationMessage() {
        return userLastName.getText();
    }

    public String getEmailValidationMessage() {
        return userEmail.getText();
    }

    public String getPhoneValidationMessage() {
        return userPhone.getText();
    }
}

