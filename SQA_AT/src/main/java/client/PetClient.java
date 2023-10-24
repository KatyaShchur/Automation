package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Pet;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient {
    private final String petClientUrl = "/pet";
    private final String petIdUrl = petClientUrl + "/{petId}";
    private final String petImageUrl = petIdUrl + "/uploadImage";

    public Response getPetById(Integer id) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .get(petIdUrl);
    }

    public Response deletePetById(Integer id) {
        return given(baseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .delete(petIdUrl);
    }

    public Response createPet(Pet pets) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(pets)
                .post(petClientUrl);
    }

    public Response updatePet(Integer id, String name, String status) {
        return given(baseRequestSpecification(ContentType.URLENC))
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .formParam("name", name)
                .formParam("status", status)
                .post(petIdUrl);

    }

    public Response updatePetImage(Integer id) {
        String pathToFile = getClass().getResource("/cat.jpg").getPath();
        return given(baseRequestSpecification(ContentType.MULTIPART))
                .accept(ContentType.JSON)
                .multiPart("file", new File(pathToFile))
                .pathParam("petId", id)
                .post(petImageUrl);
    }
}
