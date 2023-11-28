package common.constants;

import java.util.HashMap;

public class BaseParams {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    public static final String BASE_CONTENT_TYPE_HEADER = "Content-type";
    public static final String BASE_CONTENT_TYPE_VALUE = "application/json";
    public static HashMap<String, Integer> pagingParams = new HashMap<String, Integer>() {{
        put("limit", 10);
        put("page", 0);
    }};
}
