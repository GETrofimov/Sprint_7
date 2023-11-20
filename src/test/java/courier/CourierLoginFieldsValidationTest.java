package courier;

import common.constants.BaseParams;
import courier.body.Body;
import courier.body.PredefinedBody;
import courier.constants.Routes;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.Messages.LOGIN_EMPTY_FIELDS_VALIDATION_MESSAGE;
import static common.constants.StatusCodes.*;
import static courier.constants.Routes.CREATE;
import static courier.constants.Routes.DELETE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CourierLoginFieldsValidationTest {

    private String login;
    private String password;
    private static PredefinedBody exampleBody = new PredefinedBody();

    public CourierLoginFieldsValidationTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                {exampleBody.getLogin(), null},
                {exampleBody.getPassword(), null}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
        RestAssured.defaultParser = Parser.JSON;

        Response courier = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(exampleBody)
                .when()
                .post(CREATE);
        courier.then().statusCode(CREATED);
    }
    @Test
    @DisplayName("Проверка авторизации с незаполненными полями")
    @Description("Проверка авторизации с незаполненными полями")
    public void loginCourier() {
        Body body = new Body();
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(Routes.LOGIN);
        response.then().assertThat().statusCode(BAD_REQUEST)
                .and()
                .body("code", equalTo(BAD_REQUEST))
                .body("message", equalTo(LOGIN_EMPTY_FIELDS_VALIDATION_MESSAGE));
    }

    @After
    public void deleteCourier() {
        String id = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(exampleBody)
                .when()
                .post(Routes.LOGIN)
                .then().extract().body().path("id").toString();


        Response delete = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .pathParam("id", id)
                .and()
                .delete(DELETE);
        delete.then().statusCode(OK);
    }
}
