package courier;

import common.constants.BaseParams;
import courier.body.Body;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static courier.constants.Credentials.*;
import static courier.constants.Routes.CREATE;
import static common.constants.StatusCodes.BAD_REQUEST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static common.constants.Messages.*;

@RunWith(Parameterized.class)
public class CourierCreateFieldsValidationTest {

    private String login;

    private String password;

    public CourierCreateFieldsValidationTest(String login, String password) {
        this.login = login;
        this.password = password;
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
        RestAssured.baseURI = BaseParams.BASE_URI;
    }
    @Test
    @DisplayName("Проверка создания курьера без обязательных атрибутов")
    @Description("Проверка создания курьера без обязательных атрибутов")
    public void createNewCourier() {
        Body body = new Body();
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(CREATE);
        response.then().assertThat().statusCode(BAD_REQUEST)
                .and()
                .body("code", equalTo(BAD_REQUEST))
                .body("message", equalTo(CREATE_EMPTY_FIELDS_VALIDATION_MESSAGE));
    }
}
