package utils;

import dto.AdditionRequest;
import dto.AdditionResponse;
import dto.EntityRequest;
import dto.EntityResponse;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestDataGenerator {

    /**
     * Генерирует случайный заголовок сущности
     */
    public static String generateRandomTitle() {
        return "Test Entity " + UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * Создает валидный EntityRequest со случайными данными
     */
    public static EntityRequest generateValidEntityRequest() {
        return EntityRequest.builder()
                .addition(generateValidAdditionRequest())
                .importantNumbers(Arrays.asList(1, 2, 3))
                .title(generateRandomTitle())
                .verified(true)
                .build();
    }

    /**
     * Создает валидный EntityResponse со случайными данными
     */
    public static EntityResponse generateValidEntityResponse(int id) {
        return EntityResponse.builder()
                .id(id)
                .addition(generateValidAdditionResponse(id))
                .importantNumbers(Arrays.asList(10, 20, 30))
                .title(generateRandomTitle())
                .verified(false)
                .build();
    }

    /**
     * Создает валидный AdditionRequest со случайными данными
     */
    public static AdditionRequest generateValidAdditionRequest() {
        return AdditionRequest.builder()
                .additionalInfo("Additional Info " + UUID.randomUUID().toString().substring(0, 8))
                .additionalNumber(100 + (int)(Math.random() * 100))
                .build();
    }

    /**
     * Создает валидный AdditionResponse со случайными данными
     */
    public static AdditionResponse generateValidAdditionResponse(int id) {
        return AdditionResponse.builder()
                .additionalInfo("Additional Response Info " + UUID.randomUUID().toString().substring(0, 8))
                .additionalNumber(200 + (int)(Math.random() * 100))
                .build();
    }

    /**
     * Генерирует список EntityResponse для тестирования /getAll
     */
    public static List<EntityResponse> generateEntityResponseList(int count) {
        EntityResponse[] entities = new EntityResponse[count];
        for (int i = 0; i < count; i++) {
            entities[i] = generateValidEntityResponse(i + 1);
        }
        return Arrays.asList(entities);
    }
}
