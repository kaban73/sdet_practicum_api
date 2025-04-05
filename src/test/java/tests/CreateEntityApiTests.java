package tests;

import dto.EntityRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;

@Epic("API Тесты")
@Story("Создание сущности")
public class CreateEntityApiTests extends BaseTest{
    @Test
    @Description("Проверка успешного создания сущности")
    @Feature("Создание сущности")
    public void testCreateEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();
        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());
    }
}
