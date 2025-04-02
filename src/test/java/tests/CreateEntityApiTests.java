package tests;

import clients.EntityClient;
import dto.EntityRequest;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;

public class CreateEntityApiTests {
    private final EntityClient entityClient = new EntityClient();

    @Test
    public void testCreateEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);

        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());
    }
}
