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
public class CourierLoginValidationTest extends CourierClient {
    private static CourierBody predefinedBody = new CourierBody(LOGIN, PASSWORD);
    private CourierBody courierBody;
    private int courierId;

    public CourierLoginValidationTest(String login, String password) {
        this.courierBody = new CourierBody(login, password);
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                {predefinedBody.getLogin(), null},
                {predefinedBody.getPassword(), null}
        };
    }

    @Before
    public void setUp() {
        BaseTest.setUp();
        sendCreateCourierRequest(predefinedBody);
        Response loginResponse = sendLoginRequest(predefinedBody);
        this.courierId = setId(predefinedBody);
    }
    @Test
    @DisplayName("Проверка авторизации с незаполненными полями, авторизации для несуществующего курьера")
    @Description("Проверка авторизации с незаполненными полями, авторизации для несуществующего курьера")
    public void loginCourier() {
        Response loginResponse = sendLoginRequest(courierBody);
        assertLoginCourierFieldsValidation(loginResponse);
    }
    @After
    public void cleanData() {
        Response deleteResponse = sendDeleteCourierRequest(courierId);
        assertDeleteCourierRequestResult(deleteResponse, true);
    }
}
