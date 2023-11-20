package courier;

import common.constants.BaseParams;
import courier.body.Body;
import courier.constants.Routes;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.StatusCodes.CREATED;
import static common.constants.StatusCodes.OK;
import static courier.constants.Routes.CREATE;
import static courier.constants.Credentials.*;
import static courier.constants.Routes.DELETE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateTest {
    Body body = new Body(LOGIN, PASSWORD, FIRST_NAME);
    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }
    @Test
    @DisplayName("Создание курьера")
    @Description("Базовое создание сущности курьера в бд")

    public void createNewCourier() {
        Response response = sendPostRequest();
        assertResult(response, true);
    }

    @Step("Send POST /api/v1/courier")
    public Response sendPostRequest() {
        Response response =
                    given()
                            .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                            .and()
                            .body(body)
                            .when()
                            .post(CREATE);
        return response;
        }

    @Step("Assert response")
        public void assertResult(Response response, boolean expectedResult) {
        response.then().assertThat().statusCode(CREATED)
                .and()
                .body("ok", equalTo(expectedResult));
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
