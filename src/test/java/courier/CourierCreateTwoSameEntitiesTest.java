package courier;

import common.constants.BaseParams;
import courier.body.Body;
import courier.constants.Routes;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.StatusCodes.*;
import static courier.constants.Credentials.*;
import static common.constants.Messages.ENTITY_ALREADY_EXISTS_MESSAGE;
import static courier.constants.Routes.CREATE;
import static courier.constants.Routes.DELETE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateTwoSameEntitiesTest {
    private static Body body = new Body(LOGIN, PASSWORD);
    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }
    @Test
    @DisplayName("Проверка создания двух одинаковых сущностей курьера")
    @Description("Проверка создания двух одинаковых сущностей курьера")
    public void createNewCourier() {
        Response firstResponse =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(CREATE);
        firstResponse.then().assertThat().statusCode(CREATED)
                .and()
                .body("ok", equalTo(true));

        Response secondResponse =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(CREATE);
        secondResponse.then().assertThat().statusCode(CONFLICT)
                .and()
                .body("message", equalTo(ENTITY_ALREADY_EXISTS_MESSAGE));
    }

    @After
    public void deleteCourier() {
        String id = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(body)
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
