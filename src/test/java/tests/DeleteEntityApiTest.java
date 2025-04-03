package tests;

import clients.EntityClient;
import dto.EntityRequest;
import dto.EntityResponse;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class DeleteEntityApiTest {
    private final EntityClient entityClient = new EntityClient();

    @Test
    public void testDeleteEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        entityClient.deleteEntity(entityId);

        List<EntityResponse> entities = entityClient.getAllEntities();

        boolean isDeletedEntity = entities.stream()
                .anyMatch(el -> el.getId() != Integer.parseInt(entityId));
        assertTrue(isDeletedEntity);
    }
}
