package tests;

import clients.EntityClient;
import dto.EntityRequest;
import dto.EntityResponse;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class PatchEntityApiTest {
    private final EntityClient entityClient = new EntityClient();

    @Test
    public void testUpdateFullEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        EntityRequest entityRequest = EntityRequest.builder()
                .title("Update title " + System.currentTimeMillis())
                .verified(false)
                .importantNumbers(List.of(10, 20, 30))
                .addition(TestDataGenerator.generateValidAdditionRequest())
                .build();

        entityClient.updateEntity(entityId, entityRequest);

        EntityResponse updateEntity = entityClient.getEntity(entityId);

        assertEquals(updateEntity.getTitle(), entityRequest.getTitle());
        assertEquals(updateEntity.isVerified(), entityRequest.isVerified());

        assertEquals(updateEntity.getAddition().getAdditionalInfo(),
                entityRequest.getAddition().getAdditionalInfo()
        );

        assertEquals(updateEntity.getImportantNumbers(),
                entityRequest.getImportantNumbers()
        );
    }

    @Test
    public void testUpdateOnlyTitleEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        EntityRequest entityRequest = EntityRequest.builder()
                .title("Update title " + System.currentTimeMillis())
                .build();

        entityClient.updateEntity(entityId, entityRequest);

        EntityResponse updateEntity = entityClient.getEntity(entityId);

        assertEquals(updateEntity.getTitle(), entityRequest.getTitle());
        assertEquals(updateEntity.isVerified(), request.isVerified());

        assertEquals(updateEntity.getAddition().getAdditionalInfo(),
                request.getAddition().getAdditionalInfo()
        );

        assertEquals(updateEntity.getImportantNumbers(),
                request.getImportantNumbers()
        );
    }
}
