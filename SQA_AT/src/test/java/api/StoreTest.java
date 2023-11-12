package api;

import client.StoreClient;
import io.restassured.response.Response;
import model.Order;
import org.testng.annotations.Test;

import static assertion.BaseAssertion.*;

public class StoreTest {

    StoreClient storeClient = new StoreClient();
    Order order = new Order();

    @Test
    public void getStoreInventory() {
        Response response = storeClient.getStoreInventory();
        check200Response(response);
    }

    @Test
    public void createOrder() {
        Response response = storeClient.createStoreOrder(order);
        check200Response(response);
    }

    @Test
    public void deleteOrderById() {
        Response createOrderResponse = storeClient.createStoreOrder(order);
        Order newOrder = createOrderResponse.as(Order.class);
        Response deleteResponse = storeClient.deleteStoreOrder(newOrder.getId());
        check200Response(deleteResponse);
        Response getOrderByIdResponse = storeClient.getStoreOrderById(newOrder.getId());
        check404Response(getOrderByIdResponse);
    }

    @Test
    public void findOrderById() {
        Response createOrderResponse = storeClient.createStoreOrder(order);
        Order newOrder = createOrderResponse.as(Order.class);
        Response getOrderByIdResponse = storeClient.getStoreOrderById(newOrder.getId());
        check200Response(getOrderByIdResponse);
    }
}
