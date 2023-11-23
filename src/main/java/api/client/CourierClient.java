package api.client;

import courier.body.CourierBody;
import courier.constants.Routes;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.Messages.*;
import static common.constants.StatusCodes.*;
import static courier.constants.Routes.CREATE;
import static courier.constants.Routes.DELETE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierClient {

    @Step("Send POST /api/v1/courier")
    public static Response sendCreateCourierRequest(CourierBody body) {
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(CREATE);
        return response;
    }
    @Step("Assert response for POST /api/v1/courier")
    public static void assertCreateCourierRequestResult(Response response, boolean expectedResult) {
        response.then().assertThat().statusCode(CREATED)
                .and()
                .body("ok", equalTo(expectedResult));
    }

    @Step("Send POST /api/v1/courier/login")
    public Response sendLoginRequest(CourierBody body) {
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(Routes.LOGIN);
        return response;
    }
    @Step("Assert response for POST /api/v1/courier/login")
    public void assertLoginRequestResult(Response response, int courierId) {
        response.then().assertThat().statusCode(OK)
                .and()
                .body("id", equalTo(courierId));
    }

    @Step
    public int setId(CourierBody courierBody) {
        int courierId = sendLoginRequest(courierBody).then().extract().body().path("id");
        return courierId;
    }

    @Step("Send DELETE /api/v1/courier/:id")
    public Response sendDeleteCourierRequest(int courierId) {
        Response response = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .pathParam("id", courierId)
                .and()
                .delete(DELETE);
        return response;
    }

    @Step("Assert response for DELETE /api/v1/courier/:id")
    public void assertDeleteCourierRequestResult(Response response, boolean expectedResult){
        response.then().assertThat().statusCode(OK)
                .and()
                .body("ok", equalTo(expectedResult));
    }

    @Step("Assert response for POST /api/v1/courier without mandatory fields")
    public void assertCreateCourierFieldsValidation(Response response) {
        response.then().assertThat().statusCode(BAD_REQUEST)
                .and()
                .body("code", equalTo(BAD_REQUEST))
                .body("message", equalTo(CREATE_EMPTY_FIELDS_VALIDATION_MESSAGE));
    }

    @Step("Assert response for POST /api/v1/courier entity already exists")
    public void assertCreateCourierEntityAlreadyExist(Response response) {
        response.then().assertThat().statusCode(CONFLICT)
                .and()
                .body("code", equalTo(CONFLICT))
                .body("message", equalTo(ENTITY_ALREADY_EXISTS_MESSAGE));
    }

    @Step("Assert response for POST /api/v1/courier/login without mandatory fields")
    public void assertLoginCourierFieldsValidation(Response response) {
        response.then().assertThat().statusCode(BAD_REQUEST)
                .and()
                .body("code", equalTo(BAD_REQUEST))
                .body("message", equalTo(LOGIN_EMPTY_FIELDS_VALIDATION_MESSAGE));
    }

    @Step("Assert response for POST /api/v1/courier/login non existent entity")
    public void assertLoginCourierNonExistentEntity(Response response) {
        response.then().assertThat().statusCode(NOT_FOUND)
                .and()
                .body("message", equalTo(LOGIN_NON_EXISTENT_ENTITY_MESSAGE));
    }
}
