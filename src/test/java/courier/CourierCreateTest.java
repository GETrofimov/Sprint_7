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

public class CourierCreateTest extends CourierClient {
    private int courierId;
    CourierBody courierBody = new CourierBody(LOGIN, PASSWORD, FIRST_NAME);

    @Before
    public void setUp() {
        BaseTest.setUp();
    }
    @Test
    @DisplayName("Создание курьера")
    @Description("Базовое создание сущности курьера")
    public void createNewCourier() {
        Response createResponse = sendCreateCourierRequest(courierBody);
        assertCreateCourierRequestResult(createResponse, true);
        this.courierId = setId(courierBody);
    }
    @Test
    @DisplayName("Создание 2 одинаковых сущностей курьера")
    @Description("Создание 2 одинаковых сущностей курьера")
    public void createTwoSameEntities() {
        Response createResponse = sendCreateCourierRequest(courierBody);
        assertCreateCourierRequestResult(createResponse, true);

        this.courierId = setId(courierBody);

        Response createSameEntityResponse = sendCreateCourierRequest(courierBody);
        assertCreateCourierEntityAlreadyExist(createSameEntityResponse);
    }
    @After
    public void cleanData() {
        Response deleteResponse = sendDeleteCourierRequest(courierId);
        assertDeleteCourierRequestResult(deleteResponse, true);
    }
}
