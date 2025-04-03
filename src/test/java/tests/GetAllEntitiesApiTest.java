package tests;

import clients.EntityClient;
import dto.EntityRequest;
import dto.EntityResponse;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.util.List;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertTrue;

public class GetAllEntitiesApiTest {
    private final EntityClient entityClient = new EntityClient();

    @Test
    public void testGetAllEntities() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        List<EntityResponse> entities = entityClient.getAllEntities();

        assertFalse(entities.isEmpty());

        boolean found = entities.stream()
                .anyMatch(e -> e.getId() == Integer.parseInt(entityId));
        assertTrue(found);
    }

    @Test
    public  void testGetAllEntitiesWithPagination() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        List<EntityResponse> firstPage = entityClient.getAllEntitiesWithFilters(null, null, 1, 1);

        assertEquals(firstPage.size(), 1);

        List<EntityResponse> secondPage = entityClient.getAllEntitiesWithFilters(null, null, 2, 1);

        assertNotEquals(firstPage.get(0).getId(), secondPage.get(0).getId());
    }

    @Test
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
    public void testGetAllEntitiesVerifiedFilter() {
        List<EntityResponse> verifiedEntities = entityClient.getAllEntitiesWithFilters(null, true, null, null);

        assertTrue(verifiedEntities.stream()
                .allMatch(EntityResponse::isVerified));
    }
}
