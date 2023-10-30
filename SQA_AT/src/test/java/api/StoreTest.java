package api;

import client.StoreClient;
import io.restassured.response.Response;
import model.Order;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

import static org.testng.Assert.assertEquals;

public class StoreTest {
    @Test
    public void getStoreInventory() {
        StoreClient storeClient = new StoreClient();
        Response response = storeClient.getStoreInventory();
        assertEquals(response.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
    }

    @Test
    public void createOrder() {
        StoreClient storeClient = new StoreClient();
        Order order = new Order();
        Response response = storeClient.createStoreOrder(order);
        assertEquals(response.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(response.contentType(), "application/json", "Error - incorrect content type");
    }

    @Test
    public void deleteOrderById() {
        StoreClient storeClient = new StoreClient();
        Order order = new Order();
        Response createOrderResponse = storeClient.createStoreOrder(order);
        Order newOrder = createOrderResponse.as(Order.class);
        Response deleteResponse = storeClient.deleteStoreOrder(newOrder.getId());
        assertEquals(deleteResponse.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        Response getOrderByIdResponse = storeClient.getStoreOrderById(newOrder.getId());
        assertEquals(getOrderByIdResponse.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND, "Error - incorrect status code");
    }

    @Test
    public void findOrderById() {
        StoreClient storeClient = new StoreClient();
        Order order = new Order();
        Response createOrderResponse = storeClient.createStoreOrder(order);
        Order newOrder = createOrderResponse.as(Order.class);
        Response getOrderByIdResponse = storeClient.getStoreOrderById(newOrder.getId());
        assertEquals(getOrderByIdResponse.statusCode(),  HttpURLConnection.HTTP_OK, "Error - incorrect status code");
        assertEquals(getOrderByIdResponse.contentType(), "application/json", "Error - incorrect content type");
    }
}
