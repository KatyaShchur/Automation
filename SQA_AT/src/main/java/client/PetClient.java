package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Pet;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient {
    private static final String PET_CLIENT_URL = "/pet";
    private static final String PET_ID_URL = PET_CLIENT_URL + "/{petId}";
    private static final String PET_IMAGE_URL = PET_ID_URL + "/uploadImage";

    public Response getPetById(Integer id) {
        return given(getBaseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .get(PET_ID_URL);
    }

    public Response deletePetById(Integer id) {
        return given(getBaseRequestSpecification(ContentType.JSON))
                .pathParam("petId", id)
                .delete(PET_ID_URL);
    }

    public Response createPet(Pet pets) {
        return given(getBaseRequestSpecification(ContentType.JSON))
                .body(pets)
                .post(PET_CLIENT_URL);
    }

    public Response updatePet(Integer id, String name, String status) {
        return given(getBaseRequestSpecification(ContentType.URLENC))
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .formParam("name", name)
                .formParam("status", status)
                .post(PET_ID_URL);

    }

    public Response updatePetImage(Integer id) {
        String pathToFile = getClass().getResource("/cat.jpg").getPath();
        return given(getBaseRequestSpecification(ContentType.MULTIPART))
                .accept(ContentType.JSON)
                .multiPart("file", new File(pathToFile))
                .pathParam("petId", id)
                .post(PET_IMAGE_URL);
    }
}
