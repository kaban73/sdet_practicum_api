package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("API Тесты")
@Story("Создание сущности")
public class CreateEntityApiTests extends BaseTest{
    @Test
    @Description("Проверка успешного создания сущности")
    @Feature("Создание сущности")
    public void testCreateEntity() {
        createEntityAndGetId();
    }
}
