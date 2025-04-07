package tests;

import clients.EntityClient;
import dto.EntityRequest;
import dto.EntityResponse;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.TestDataGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class BaseTest {
    protected EntityClient entityClient;
    private final List<String> createdEntityIds = new ArrayList<>();

    @BeforeClass
    @Step("Инициализация тестового клиента")
    public void init() {
        entityClient = new EntityClient();
    }

    @Step("Создание новой сущности и получение её ID")
    protected String createEntityAndGetId() {
        EntityRequest request = TestDataGenerator.generateValidEntityRequest();
        String entityId = entityClient.createEntity(request);
        assertNotNull(entityId);
        assertFalse(entityId.isEmpty());
        createdEntityIds.add(entityId);
        return entityId;
    }

    @Step("Проверка соответствия ответа запросу")
    protected void assertEntityMatchesRequest(EntityResponse response, EntityRequest request) {
        assertEquals(request.getTitle(), response.getTitle());
        assertEquals(request.isVerified(), response.isVerified());
        assertNotNull(request.getAddition());
        assertEquals(
                request.getAddition().getAdditionalInfo(),
                response.getAddition().getAdditionalInfo()
        );
        assertEquals(
                request.getAddition().getAdditionalNumber(),
                response.getAddition().getAdditionalNumber()
        );
        assertEquals(
                request.getImportantNumbers().size(),
                response.getImportantNumbers().size()
        );
    }

    @AfterClass
    @Step("Очистка тестовых данных")
    public void cleanup() {
        for (String entityId : createdEntityIds) {
            try {
                entityClient.deleteEntity(entityId);
            } catch (AssertionError ignored) {}
        }
        createdEntityIds.clear();
    }
}
