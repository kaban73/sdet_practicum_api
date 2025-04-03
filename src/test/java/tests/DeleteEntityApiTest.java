package tests;

import clients.EntityClient;
import dto.EntityRequest;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

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

        try {
            entityClient.getEntity(entityId);
            fail("Entity was not deleted");
        } catch (AssertionError e) {
            assertTrue(e.getMessage().contains("500"));
        }
    }
}
