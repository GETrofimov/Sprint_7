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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static courier.constants.Credentials.*;

@RunWith(Parameterized.class)
public class CourierCreateValidationTest extends CourierClient {
    private int courierId;
    private CourierBody courierBody;

    public CourierCreateValidationTest(String login, String password) {
        this.courierBody = new CourierBody(login, password);
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                {LOGIN, null},
                {PASSWORD, null}
        };
    }

    @Before
    public void setUp() {
        BaseTest.setUp();
    }
    @Test
    @DisplayName("Проверка создания и попытки авторизации курьера без обязательных атрибутов")
    @Description("Проверка создания курьера без обязательных атрибутов, попытка авторизации без обязательных атрибутов")
    public void createCourierWithoutParams() {
        Response responseCreate = sendCreateCourierRequest(courierBody);
        assertCreateCourierFieldsValidation(responseCreate);
    }
    @After
    public void cleanData() {
        try {
            courierId = setId(courierBody);
            sendDeleteCourierRequest(courierId);
        } catch (Exception e) {}
    }
}
