package api;

import client.PetClient;
import io.restassured.response.Response;
import model.Pet;
import model.PetBuilder;
import org.testng.annotations.Test;

import static assertion.BaseAssertion.*;
import static assertion.PetAssertion.verifyPet;

public class PetTest {

    private PetClient petClient = new PetClient();

    private Integer testPetId = 110;

    @Test
    public void createPet() {
        Pet pet = buildPetModel();
        PetClient petClient = new PetClient();
        Response response = petClient.createPet(pet);
        check200Response(response);

        response = petClient.getPetById(testPetId);
        Pet newPet = response.as(Pet.class);
        check200Response(response);
        verifyPet(newPet, pet);
    }

    @Test
    public void updatePetsImage() {
        createPet();

        Response response = petClient.updatePetImage(testPetId);
        check200Response(response);
    }

    @Test
    public void updatePetsNameStatus() {
        createPet();

        String updatedPetName = "test";
        String updatedPetStatus = "sold";
        Response response = petClient.updatePet(testPetId, updatedPetName, updatedPetStatus);
        check200Response(response);

        response = petClient.getPetById(testPetId);
        Pet newPet = response.as(Pet.class);
        check200Response(response);
        verifyPet(newPet, updatedPetName, updatedPetStatus);
    }

    @Test
    public void deletePetById() {
        createPet();

        Response response = petClient.deletePetById(testPetId);
        check200Response(response);
        response = petClient.getPetById(testPetId);
        check404Response(response);
    }

    private Pet buildPetModel() {
        Pet pet = new PetBuilder().setId(testPetId)
                .setName("Jack")
                .setStatus("available")
                .build();
        return pet;
    }
}
