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
import static courier.constants.Credentials.LOGIN;
import static courier.constants.Credentials.PASSWORD;
import static courier.constants.Routes.CREATE;
import static courier.constants.Routes.DELETE;
import static common.constants.StatusCodes.OK;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierDeleteTest {
    private static Body body = new Body(LOGIN, PASSWORD);
    private String id;


    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;

        given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(body)
                .when()
                .post(CREATE);

        this.id = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(body)
                .post(Routes.LOGIN)
                .then().extract().body().path("id").toString();
    }

    @Test
    @DisplayName("Проверка удаления курьера")
    @Description("Проверка удаления курьера")
    public void courierDelete() {
        Response response = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .pathParam("id", id)
                .and()
                .delete(DELETE);
        response.then().assertThat().statusCode(OK)
                .and()
                .body("ok", equalTo(true));
    }
}
