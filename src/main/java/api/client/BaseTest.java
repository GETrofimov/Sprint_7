package api.client;

import common.constants.BaseParams;
import courier.constants.Routes;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.StatusCodes.OK;
import static courier.constants.Routes.DELETE;
import static io.restassured.RestAssured.given;

public class BaseTest {
    @Step
    public static void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }

    @Step
    public static void deleteCourier(int courierId) {
        Response delete = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .pathParam("id", courierId)
                .and()
                .delete(DELETE);
        delete.then().statusCode(OK);
    }
}
