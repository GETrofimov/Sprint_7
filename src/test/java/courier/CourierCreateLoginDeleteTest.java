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

public class CourierCreateLoginDeleteTest extends CourierClient {
    private int courierId;
    CourierBody courierBody = new CourierBody(LOGIN, PASSWORD, FIRST_NAME);

    @Before
    public void setUp() {
        BaseTest.setUp();
    }

    @Test
    @DisplayName("Создание, авторизация, удаление курьера")
    @Description("Базовое создание сущности курьера в бд, попытка повторно создать одинаковую сущность, авторизация, удаление сущности из бд")

    public void createNewCourier() {
        Response createResponse = sendCreateCourierRequest(courierBody);
        assertCreateCourierRequestResult(createResponse, true);

        Response createSameEntityResponse = sendCreateCourierRequest(courierBody);
        assertCreateCourierEntityAlreadyExist(createSameEntityResponse);

        Response loginResponse = sendLoginRequest(courierBody);
        courierId = setId(courierBody);
        assertLoginRequestResult(loginResponse, courierId);

        Response deleteResponse = sendDeleteCourierRequest(courierId);
        assertDeleteCourierRequestResult(deleteResponse, true);
    }


}
