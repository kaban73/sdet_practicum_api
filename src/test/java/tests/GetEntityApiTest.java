package tests;

import dto.EntityRequest;
import dto.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

@Epic("API Тесты")
@Story("Получение сущности")
public class GetEntityApiTest extends BaseTest {
    @Test
    @Description("Проверка получения существующей сущности")
    @Feature("Получение сущности")
    public void testGetExistEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);

        EntityResponse response = entityClient.getEntity(entityId);

        assertEntityMatchesRequest(response, request);
    }
}

