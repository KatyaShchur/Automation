package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class StoreClient extends BaseClient{
    private static final String STORE_INVENTORY_URL = "/store/inventory";
    private static final String STORE_ORDER_URL = "/store/order";
    private static final String STORE_ORDER_ID_URL = STORE_ORDER_URL + "/{orderId}";

    public Response getStoreInventory() {
        return given(getBaseRequestSpecification(ContentType.JSON))
                .get(STORE_INVENTORY_URL);
    }

    public Response createStoreOrder(Order order) {
        return given(getBaseRequestSpecification())
                .body(order)
                .post(STORE_ORDER_URL);
    }

    public Response deleteStoreOrder(Long id) {
        return  given(getBaseRequestSpecification())
                .pathParam("orderId", id)
                .delete(STORE_ORDER_ID_URL);
    }

    public Response getStoreOrderById(Long id) {
        return given(getBaseRequestSpecification())
                .pathParam("orderId", id)
                .get(STORE_ORDER_ID_URL);
    }

}
