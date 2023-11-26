package order;

import api.client.BaseTest;
import api.client.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.body.Color;
import order.body.OrderBodyRequest;
import order.constants.Colors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static order.constants.BodyData.*;
import static order.constants.Colors.GREY;
import static order.constants.MetroStations.SOKOLNIKI;
import static order.constants.RentPeriods.TWO_DAYS;;

@RunWith(Parameterized.class)
public class OrderResponseCreateColorsTest extends OrdersClient {
    private OrderBodyRequest orderBodyRequest;

    public OrderResponseCreateColorsTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.orderBodyRequest = new OrderBodyRequest(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, new Color(color));
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {FIRST_NAME, LAST_NAME, ADDRESS, SOKOLNIKI, PHONE, TWO_DAYS, DELIVERY_DATE, COMMENT, Colors.BLACK},
                {FIRST_NAME, LAST_NAME, ADDRESS, SOKOLNIKI, PHONE, TWO_DAYS, DELIVERY_DATE, COMMENT, GREY},
                {FIRST_NAME, LAST_NAME, ADDRESS, SOKOLNIKI, PHONE, TWO_DAYS, DELIVERY_DATE, COMMENT, null}
        };
    }

    @Before
    public void setUp() {
        BaseTest.setUp();
    }

    @Test
    @DisplayName("Проверка создания заказа с указанием цветов")
    @Description("Проверка создания заказа с указанием цветов")
    public void createOrderTest() {
        Response createResponse = sendCreateOrderRequest(orderBodyRequest);
        assertCreateOrderRequest(createResponse);
    }
}
