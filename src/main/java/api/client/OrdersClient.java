package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import order.body.OrderBodyRequest;

import static common.constants.BaseParams.*;
import static common.constants.StatusCodes.CREATED;
import static common.constants.StatusCodes.OK;
import static io.restassured.RestAssured.given;
import static order.constants.Routes.CREATE_ORDER;
import static order.constants.Routes.GET_ORDER_LIST;
import static org.hamcrest.Matchers.*;

public class OrdersClient {
    @Step("Send POST /api/v1/orders")
    public static Response sendCreateOrderRequest(OrderBodyRequest orderBodyRequest) {
        Response response = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(orderBodyRequest)
                .when()
                .post(CREATE_ORDER);
        return response;
    }
    @Step("Assert POST /api/v1/orders")
    public void assertCreateOrderRequest(Response response) {
        response.then().assertThat().statusCode(CREATED)
                .and()
                .body("track", instanceOf(Integer.class));
    }
    @Step("Send GET /api/v1/orders")
    public static Response sendGetOrderListRequest() {
        Response response = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .queryParams(pagingParams)
                .and()
                .get(GET_ORDER_LIST);
        return response;
    }
    @Step("Assert GET /api/v1/orders")
    public void assertGetOrderListRequest(Response response) {
        response.then().assertThat().statusCode(OK).and().body("orders.length", not(0));
    }
}
