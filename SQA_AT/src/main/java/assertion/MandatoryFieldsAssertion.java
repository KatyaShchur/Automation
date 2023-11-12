package assertion;

import com.epam.pageobject.EpamAboutPage;
import org.assertj.core.api.SoftAssertions;

public class MandatoryFieldsAssertion {

    private static final String VALIDATION_MESSAGE = "This is a required field";

    public static void verifyMandatoryFields(EpamAboutPage epamAboutPage) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(epamAboutPage.getFirstNameValidationMessage())
                .as("Has first name")
                .isEqualTo(VALIDATION_MESSAGE);
        softAssert.assertThat(epamAboutPage.getLastNameValidationMessage())
                .as("Has last name")
                .isEqualTo(VALIDATION_MESSAGE);
        softAssert.assertThat(epamAboutPage.getEmailValidationMessage())
                .as("Has email")
                .isEqualTo(VALIDATION_MESSAGE);
        softAssert.assertThat(epamAboutPage.getPhoneValidationMessage())
                .as("Has phone number")
                .isEqualTo(VALIDATION_MESSAGE);
        softAssert.assertAll();
    }
}
