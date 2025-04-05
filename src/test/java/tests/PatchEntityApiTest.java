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
    private static final String TITLE_PREFIX = "Update title ";
    private static final boolean UPDATED_VERIFIED_STATUS = false;
    private static final List<Integer> UPDATED_NUMBERS = List.of(10, 20, 30);
    @Test
    @Description("Полное обновление сущности")
    @Feature("Обновление сущности")
    public void testUpdateFullEntity() {
        String entityId = createEntityAndGetId();

        EntityRequest entityRequest = EntityRequest.builder()
                .title(TITLE_PREFIX + System.currentTimeMillis())
                .verified(UPDATED_VERIFIED_STATUS)
                .importantNumbers(UPDATED_NUMBERS)
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
                .title(TITLE_PREFIX + System.currentTimeMillis())
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
