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
    private static final Boolean FILTER_VERIFIED_TRUE = true;
    private static final Boolean NO_FILTER = null;

    private static final int FIRST_PAGE = 1;
    private static final int SECOND_PAGE = 2;
    private static final int ITEMS_PER_PAGE = 1;
    private static final Integer NO_PAGE = null;

    private static final String UNIQUE_TITLE_PREFIX = "UNIQUE_TITLE_";
    private static final String NO_TITLE = null;

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

        List<EntityResponse> firstPage = entityClient.getAllEntitiesWithFilters(
                NO_TITLE, NO_FILTER, FIRST_PAGE, ITEMS_PER_PAGE
        );

        assertEquals(
                ITEMS_PER_PAGE,
                firstPage.size()
        );

        List<EntityResponse> secondPage = entityClient.getAllEntitiesWithFilters(
                NO_TITLE, NO_FILTER, SECOND_PAGE, ITEMS_PER_PAGE
        );

        assertNotEquals(
                firstPage.get(0).getId(),
                secondPage.get(0).getId()
        );
    }

    @Test
    @Description("Фильтрация по заголовку")
    @Feature("Получение сущностей по заголовку")
    public void testGetAllEntitiesWithTitleFilter() {
        String uniqueTitle = UNIQUE_TITLE_PREFIX + System.currentTimeMillis();

        entityClient.createEntity(
                EntityRequest.builder()
                        .title(uniqueTitle)
                        .build()
        );

        List<EntityResponse> filtered = entityClient.getAllEntitiesWithFilters(
                uniqueTitle, NO_FILTER, NO_PAGE, NO_PAGE
        );

        assertEquals(
                filtered.size(),
                1
        );
        assertEquals(
                filtered.get(0).getTitle(),
                uniqueTitle
        );
    }

    @Test
    @Description("Фильтрация по статусу verified")
    @Feature("Получение сущностей по статусу verified")
    public void testGetAllEntitiesVerifiedFilter() {
        List<EntityResponse> verifiedEntities = entityClient.getAllEntitiesWithFilters(
                NO_TITLE, FILTER_VERIFIED_TRUE, NO_PAGE, NO_PAGE
        );

        assertTrue(
                verifiedEntities.stream()
                .allMatch(EntityResponse::isVerified)
        );
    }
}
