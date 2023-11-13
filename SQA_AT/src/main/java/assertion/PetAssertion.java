package assertion;

import model.Pet;
import org.assertj.core.api.SoftAssertions;

public class PetAssertion {

    public static void verifyPet(Pet actualPet, Pet expectedPet) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(actualPet.getName())
                .as("Has pet name")
                .isEqualTo(expectedPet.getName());
        softAssert.assertThat(actualPet.getId())
                .as("Has pet id")
                .isEqualTo(expectedPet.getId());
        softAssert.assertThat(actualPet.getStatus())
                .as("Has pet status")
                .isEqualTo(expectedPet.getStatus());
        softAssert.assertAll();
    }

    public static void verifyPet(Pet actualPet, String expectedPetName, String expectedPetStatus) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(actualPet.getName())
                .as("Has pet name")
                .isEqualTo(expectedPetName);
        softAssert.assertThat(actualPet.getStatus())
                .as("Has pet status")
                .isEqualTo(expectedPetStatus);
        softAssert.assertAll();
    }
}
