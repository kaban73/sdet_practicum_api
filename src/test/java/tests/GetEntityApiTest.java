package tests;

import clients.EntityClient;
import dto.EntityRequest;
import dto.EntityResponse;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import static org.testng.AssertJUnit.*;

public class GetEntityApiTest {
    private final EntityClient entityClient = new EntityClient();

    @Test
    public void testGetExistEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        EntityResponse response = entityClient.getEntity(entityId);

        assertEquals(Integer.parseInt(entityId), response.getId());

        assertEquals(request.getTitle(), response.getTitle());
        assertEquals(request.isVerified(), response.isVerified());

        assertEquals(
                request.getAddition().getAdditionalInfo(),
                response.getAddition().getAdditionalInfo()
        );

        assertEquals(
                request.getImportantNumbers().size(),
                response.getImportantNumbers().size()
        );
    }

    @Test
    public void testGetNotExistEntity() {
        String fakeEntityId = "999999999999999999999";

        try {
            entityClient.getEntity(fakeEntityId);
            fail("The request should have returned 500 error");
        } catch (AssertionError e) {
            assertTrue(e.getMessage().contains("500"));
        }
    }}
