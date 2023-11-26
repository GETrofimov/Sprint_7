package courier;

import api.client.BaseTest;
import api.client.CourierClient;
import courier.body.CourierBody;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courier.constants.Credentials.*;

public class CourierLoginTest extends CourierClient {
    private int courierId;
    CourierBody courierBody = new CourierBody(LOGIN, PASSWORD, FIRST_NAME);
    @Before
    public void setUp() {
        BaseTest.setUp();
        sendCreateCourierRequest(courierBody);
    }
    @Test
    @DisplayName("Авторизация курьера")
    @Description("Авторизация")
    public void courierLoginTest() {
        Response loginResponse = sendLoginRequest(courierBody);
        courierId = setId(courierBody);
        assertLoginRequestResult(loginResponse, courierId);
    }
    @After
    public void cleanData() {
        Response deleteResponse = sendDeleteCourierRequest(courierId);
        assertDeleteCourierRequestResult(deleteResponse, true);
    }
}
