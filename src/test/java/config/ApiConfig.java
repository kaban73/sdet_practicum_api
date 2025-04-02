package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class ApiConfig {
    private static final String BASE_URL = "http://localhost:8080";

    static {
        configureRestAssured();
    }

    @BeforeSuite
    public void setup() {
        configureRestAssured();
    }

    /**
     * Конфигурация RestAssured с базовыми настройками
     */
    private static void configureRestAssured() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * @return Базовые спецификации запроса
     */
    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

}
