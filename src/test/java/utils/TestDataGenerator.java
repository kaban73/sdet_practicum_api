package utils;

import dto.AdditionRequest;
import dto.EntityRequest;
import io.qameta.allure.Step;

import java.util.Arrays;
import java.util.UUID;

public class TestDataGenerator {

    /**
     * Генерирует случайный заголовок сущности
     */
    @Step("Сгенерировать случайный заголовок")
    public static String generateRandomTitle() {
        return "Test Entity " + UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * Создает валидный EntityRequest со случайными данными
     */
    @Step("Создать валидный EntityRequest")
    public static EntityRequest generateValidEntityRequest() {
        return EntityRequest.builder()
                .addition(generateValidAdditionRequest())
                .importantNumbers(Arrays.asList(1, 2, 3))
                .title(generateRandomTitle())
                .verified(true)
                .build();
    }

    /**
     * Создает валидный AdditionRequest со случайными данными
     */
    @Step("Создать валидный AdditionRequest")
    public static AdditionRequest generateValidAdditionRequest() {
        return AdditionRequest.builder()
                .additionalInfo("Additional Info " + UUID.randomUUID().toString().substring(0, 8))
                .additionalNumber(100 + (int)(Math.random() * 100))
                .build();
    }
}
