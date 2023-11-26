package api.client;

import common.constants.BaseParams;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class BaseTest {
    @Step("Set URI for test")
    public static void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }
}
