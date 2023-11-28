package order;


import api.client.BaseTest;
import api.client.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class OrderResponseCourierBodyResponseTest extends OrdersClient {
    @Before
    public void setUp() {
        BaseTest.setUp();
    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    @Description("Проверка получения списка заказов")
    public void checkResponseOrderListTest() {

        Response getResponse = sendGetOrderListRequest();
        assertGetOrderListRequest(getResponse);
    }
}
