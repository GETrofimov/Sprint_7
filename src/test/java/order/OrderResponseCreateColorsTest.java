package order;

import common.constants.BaseParams;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import order.body.OrderBodyRequest;
import order.constants.Colors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static common.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static common.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static common.constants.StatusCodes.CREATED;
import static io.restassured.RestAssured.given;
import static order.constants.BodyData.*;
import static order.constants.Colors.GREY;
import static order.constants.MetroStations.SOKOLNIKI;
import static order.constants.RentPeriods.TWO_DAYS;
import static order.constants.Routes.CREATE_ORDER;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(Parameterized.class)
public class OrderResponseCreateColorsTest {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color = new ArrayList<String>();
    private int trackNumber;

    public OrderResponseCreateColorsTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color.add(color);

    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {FIRST_NAME, LAST_NAME, ADRESS, SOKOLNIKI, PHONE, TWO_DAYS, DELIVERY_DATE, COMMENT, Colors.BLACK},
                {FIRST_NAME, LAST_NAME, ADRESS, SOKOLNIKI, PHONE, TWO_DAYS, DELIVERY_DATE, COMMENT, GREY},
                {FIRST_NAME, LAST_NAME, ADRESS, SOKOLNIKI, PHONE, TWO_DAYS, DELIVERY_DATE, COMMENT, null}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }

    @Test
    @DisplayName("Проверка создания заказа с указанием цветов")
    @Description("Проверка создания заказа с указанием цветов")
    public void createOrderTest() {
        OrderBodyRequest body = new OrderBodyRequest();
        Response response = given()
                .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                .and()
                .body(body)
                .when()
                .post(CREATE_ORDER);
        response.then().assertThat().statusCode(CREATED)
                .and()
                .body("track", instanceOf(Integer.class));
        this.trackNumber = response.then().extract().path("track");

    }
}
