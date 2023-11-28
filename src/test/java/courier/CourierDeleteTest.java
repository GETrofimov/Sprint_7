package courier;

import api.client.BaseTest;
import api.client.CourierClient;
import courier.body.CourierBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static courier.constants.Credentials.*;

public class CourierDeleteTest extends CourierClient {
    private int courierId;
    CourierBody courierBody = new CourierBody(LOGIN, PASSWORD, FIRST_NAME);
    @Before
    public void setUp() {
        BaseTest.setUp();
        sendCreateCourierRequest(courierBody);
        courierId = setId(courierBody);
    }
    @Test
    @DisplayName("Удаление курьера")
    @Description("Удаление сущности курьера из БД")
    public void courierDeleteTest() {
        Response deleteResponse = sendDeleteCourierRequest(courierId);
        assertDeleteCourierRequestResult(deleteResponse, true);
    }
}
