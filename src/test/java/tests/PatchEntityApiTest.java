package tests;

import dto.EntityRequest;
import dto.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.util.List;

import static org.testng.AssertJUnit.*;

@Epic("API Тесты")
@Story("Обновление сущностей")
public class PatchEntityApiTest extends BaseTest {
    @Test
    @Description("Полное обновление сущности")
    @Feature("Обновление сущности")
    public void testUpdateFullEntity() {
        String entityId = createEntityAndGetId();

        EntityRequest entityRequest = EntityRequest.builder()
                .title("Update title " + System.currentTimeMillis())
                .verified(false)
                .importantNumbers(List.of(10, 20, 30))
                .addition(TestDataGenerator.generateValidAdditionRequest())
                .build();

        entityClient.updateEntity(entityId, entityRequest);

        EntityResponse updateEntity = entityClient.getEntity(entityId);

        assertEntityMatchesRequest(updateEntity, entityRequest);
    }

    @Test
    @Description("Обновление только заголовка")
    @Feature("Частичное обновление сущности")
    public void testUpdateOnlyTitleEntity() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();

        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());

        EntityRequest entityRequest = EntityRequest.builder()
                .title("Update title " + System.currentTimeMillis())
                .verified(request.isVerified())
                .importantNumbers(request.getImportantNumbers())
                .addition(request.getAddition())
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
