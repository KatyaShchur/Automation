package api;

import client.PetClient;
import io.restassured.response.Response;
import model.Pet;
import model.PetBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;

import static org.testng.Assert.*;

public class PetTest {

    private PetClient petClient;

    private Integer testPetId = 110;

    @BeforeClass
    public void beforeClass() {
        petClient = new PetClient();
    }

    @Test
    public void createPet() {
        Pet pet = buildPetModel();
        PetClient petClient = new PetClient();
        Response response = petClient.createPet(pet);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);

        response = petClient.getPetById(testPetId);
        Pet newPet = response.as(Pet.class);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newPet.getName(), pet.getName(), "Incorrect pet name");
        softAssert.assertEquals(newPet.getId(), pet.getId(), "Incorrect pet id ");
        softAssert.assertEquals(newPet.getStatus(), pet.getStatus(), "Incorrect pet status");
        softAssert.assertAll();
    }

    @Test
    public void updatePetsImage() {
        createPet();

        Response response = petClient.updatePetImage(testPetId);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
    }

    @Test
    public void updatePetsNameStatus() {
        createPet();

        String updatedPetName = "test";
        String updatedPetStatus = "sold";
        Response response = petClient.updatePet(testPetId, updatedPetName, updatedPetStatus);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);

        response = petClient.getPetById(testPetId);
        Pet newPet = response.as(Pet.class);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newPet.getName(), updatedPetName, "Incorrect pet name");
        softAssert.assertEquals(newPet.getStatus(), updatedPetStatus, "Incorrect pet status");
        softAssert.assertAll();
    }

    @Test
    public void deletePetById() {
        createPet();

        Response response = petClient.deletePetById(testPetId);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        response = petClient.getPetById(testPetId);
        assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND);
    }

    private Pet buildPetModel() {
        Pet pet = new PetBuilder().setId(testPetId)
                .setName("Jack")
                .setStatus("available")
                .build();
        return pet;
    }
}
