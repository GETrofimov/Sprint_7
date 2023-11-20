package order;


import common.constants.BaseParams;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.StatusCodes.OK;
import static io.restassured.RestAssured.given;
import static order.constants.Routes.GET_ORDER_LIST;

public class OrderResponseBodyResponseTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    @Description("Проверка получения списка заказов")
    public void checkResponseOrderListTest() {
        HashMap<String, Integer> pagingParams = new HashMap<String, Integer>() {{
            put("limit", 10);
            put("page", 0);
        }};

        Response response = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .queryParams(pagingParams)
                .and()
                .get(GET_ORDER_LIST);
        response.then().assertThat().statusCode(OK);
    }
}
