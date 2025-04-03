package tests;

import dto.EntityRequest;
import dto.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertTrue;

@Epic("API Тесты")
@Story("Получение списка сущностей")
public class GetAllEntitiesApiTest extends BaseTest{
    @Test
    @Description("Получение всех сущностей")
    @Feature("Получение всех сущностей")
    public void testGetAllEntities() {
        String entityId = createEntityAndGetId();

        List<EntityResponse> entities = entityClient.getAllEntities();

        assertFalse(entities.isEmpty());

        boolean found = entities.stream()
                .anyMatch(e -> e.getId() == Integer.parseInt(entityId));
        assertTrue(found);
    }

    @Test
    @Description("Проверка пагинации")
    @Feature("Получение сущностей через пагинацию")
    public  void testGetAllEntitiesWithPagination() {
        createEntityAndGetId();

        List<EntityResponse> firstPage = entityClient.getAllEntitiesWithFilters(null, null, 1, 1);

        assertEquals(firstPage.size(), 1);

        List<EntityResponse> secondPage = entityClient.getAllEntitiesWithFilters(null, null, 2, 1);

        assertNotEquals(firstPage.get(0).getId(), secondPage.get(0).getId());
    }

    @Test
    @Description("Фильтрация по заголовку")
    @Feature("Получение сущностей по заголовку")
    public void testGetAllEntitiesWithTitleFilter() {
        String uniqueTitle = "UNIQUE_TITLE_" + System.currentTimeMillis();

        entityClient.createEntity(
                EntityRequest.builder()
                        .title(uniqueTitle)
                        .verified(true)
                        .importantNumbers(List.of(1, 2, 3))
                        .addition(null)
                        .build()
        );

        List<EntityResponse> filtered = entityClient.getAllEntitiesWithFilters(
                uniqueTitle, null, null, null
        );

        assertEquals(filtered.size(), 1);
        assertEquals(filtered.get(0).getTitle(), uniqueTitle);
    }

    @Test
    @Description("Фильтрация по статусу verified")
    @Feature("Получение сущностей по статусу verified")
    public void testGetAllEntitiesVerifiedFilter() {
        List<EntityResponse> verifiedEntities = entityClient.getAllEntitiesWithFilters(null, true, null, null);

        assertTrue(verifiedEntities.stream()
                .allMatch(EntityResponse::isVerified));
    }
}
