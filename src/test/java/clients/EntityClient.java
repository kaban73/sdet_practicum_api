package clients;

import config.ApiConfig;
import dto.EntityRequest;
import dto.EntityResponse;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EntityClient {
    /**
     * Создание новой сущности
     * @param entityRequest DTO с данными для создания
     * @return ID созданной сущности
     */
    @Step("Создать новую сущность")
    public String createEntity(EntityRequest entityRequest) {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .body(entityRequest)
                .when()
                .post("/create")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString()
                ;
    }

    /**
     * Получение сущности по ID
     * @param id ID сущности
     * @return DTO с данными сущности
     */
    @Step("Получить сущность по ID = {id}")
    public EntityResponse getEntity(String id) {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .when()
                .get("/get/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(EntityResponse.class);
    }

    /**
     * Получение списка всех сущностей
     * @return Список DTO сущностей
     */
    @Step("Получить список всех сущностей")
    public List<EntityResponse> getAllEntities() {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .when()
                .get("/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("entity", EntityResponse.class);
    }

    /**
     * Получение списка сущностей с фильтрацией
     * @param title Фильтр по заголовку
     * @param verified Фильтр по статусу verified
     * @param page Номер страницы
     * @param perPage Количество элементов на странице
     * @return Список DTO сущностей
     */
    @Step("Получить отфильтрованный список сущностей [title: {title}, verified: {verified}, page: {page}, perPage: {perPage}]")
    public List<EntityResponse> getAllEntitiesWithFilters(
            String title,
            Boolean verified,
            Integer page,
            Integer perPage) {

        return given()
                .spec(ApiConfig.getRequestSpecification())
                .queryParam("title", title)
                .queryParam("verified", verified)
                .queryParam("page", page)
                .queryParam("perPage", perPage)
                .when()
                .get("/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("entity", EntityResponse.class);
    }

    /**
     * Обновление сущности
     * @param id ID сущности
     * @param entityRequest DTO с данными для обновления
     */
    @Step("Обновить сущность с ID = {id}")
    public void updateEntity(String id, EntityRequest entityRequest) {
        given()
                .spec(ApiConfig.getRequestSpecification())
                .body(entityRequest)
                .when()
                .patch("/patch/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    /**
     * Удаление сущности
     * @param id ID сущности
     */
    @Step("Удалить сущность с ID = {id}")
    public void deleteEntity(String id) {
        given()
                .spec(ApiConfig.getRequestSpecification())
                .when()
                .delete("/delete/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
