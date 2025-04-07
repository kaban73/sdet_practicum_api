package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String BASE_PATH = "/api";

    /**
     * @return Базовые спецификации запроса
     */
    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .build();
    }

}
