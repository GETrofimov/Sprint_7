package courier;

import api.client.BaseTest;
import api.client.CourierClient;
import courier.body.CourierBody;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static courier.constants.Credentials.*;

public class CourierLoginValidationNonExistentEntity extends CourierClient {
    private CourierBody courierBody = new CourierBody(NON_EXISTENT_LOGIN, PASSWORD);
    @Before
    public void setUp() {
        BaseTest.setUp();
    }
    @Test
    public void loginNonExistentCourierTest() {
        Response loginResponse = sendLoginRequest(courierBody);
        assertLoginCourierNonExistentEntity(loginResponse);
    }
}
