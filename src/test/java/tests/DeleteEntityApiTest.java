package tests;

import dto.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;

@Epic("API Тесты")
@Story("Удаление сущности")
public class DeleteEntityApiTest extends BaseTest {
    @Test
    @Description("Проверка удаления сущности")
    @Feature("Удаление сущности")
    public void testDeleteEntity() {
        String entityId = createEntityAndGetId();

        entityClient.deleteEntity(entityId);

        List<EntityResponse> entities = entityClient.getAllEntities();

        boolean isEntityPresent = entities.stream()
                .anyMatch(el -> el.getId() == Integer.parseInt(entityId));
        assertFalse(isEntityPresent);
    }
}
