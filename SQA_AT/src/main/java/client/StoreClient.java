package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class StoreClient extends BaseClient{
    private final String storeInventoryUrl = "/store/inventory";
    private final String storeOrderUrl = "/store/order";
    private final String storeOrderIdUrl = storeOrderUrl + "/{orderId}";

    public Response getStoreInventory() {
        return given(baseRequestSpecification(ContentType.JSON))
                .get(storeInventoryUrl);
    }

    public Response createStoreOrder(Order order) {
        return given(baseRequestSpecification())
                .body(order)
                .post(storeOrderUrl);
    }

    public Response deleteStoreOrder(Long id) {
        return  given(baseRequestSpecification())
                .pathParam("orderId", id)
                .delete(storeOrderIdUrl);
    }

    public Response getStoreOrderById(Long id) {
        return given(baseRequestSpecification())
                .pathParam("orderId", id)
                .get(storeOrderIdUrl);
    }

}
