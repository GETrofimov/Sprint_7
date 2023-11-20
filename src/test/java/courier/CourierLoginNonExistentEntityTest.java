package courier;

import common.constants.BaseParams;
import courier.body.Body;
import courier.constants.Routes;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.Messages.LOGIN_NON_EXISTENT_ENTITY_MESSAGE;
import static common.constants.StatusCodes.NOT_FOUND;
import static courier.constants.Credentials.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierLoginNonExistentEntityTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }

    @Test
    @DisplayName("Проверка авторизации несуществующего курьера")
    @Description("Проверка авторизации несуществующего курьера")
    public void loginNonExistentEntity() {
        Body body = new Body(NON_EXISTENT_LOGIN, PASSWORD);
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(Routes.LOGIN);
        response.then().assertThat().statusCode(NOT_FOUND)
                .and()
                .body("message", equalTo(LOGIN_NON_EXISTENT_ENTITY_MESSAGE));
    }
}
