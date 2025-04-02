package clients;

import config.ApiConfig;
import dto.EntityRequest;
import dto.EntityResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EntityClient {
    private static final String BASE_PATH = "/api";

    /**
     * Создание новой сущности
     * @param entityRequest DTO с данными для создания
     * @return ID созданной сущности
     */
    public String createEntity(EntityRequest entityRequest) {
        return given()
                .spec(ApiConfig.getRequestSpecification())
                .body(entityRequest)
                .when()
                .post(BASE_PATH + "/create")
                .then()
                .statusCode(200)
                .extract()
                .asString()
                ;
    }

    /**
     * Получение сущности по ID
     * @param id ID сущности
     * @return DTO с данными сущности
     */
    public EntityResponse getEntity(String id) {
        return given()
                .when()
                .get(BASE_PATH + "/get/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);
    }

    /**
     * Получение списка всех сущностей
     * @return Список DTO сущностей
     */
    public List<EntityResponse> getAllEntities() {
        return given()
                .when()
                .get(BASE_PATH + "/getAll")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", EntityResponse.class);
    }

    /**
     * Получение списка сущностей с фильтрацией
     * @param title Фильтр по заголовку
     * @param verified Фильтр по статусу verified
     * @param page Номер страницы
     * @param perPage Количество элементов на странице
     * @return Список DTO сущностей
     */
    public List<EntityResponse> getAllEntitiesWithFilters(
            String title,
            Boolean verified,
            Integer page,
            Integer perPage) {

        return given()
                .queryParam("title", title)
                .queryParam("verified", verified)
                .queryParam("page", page)
                .queryParam("perPage", perPage)
                .when()
                .get(BASE_PATH + "/getAll")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", EntityResponse.class);
    }

    /**
     * Обновление сущности
     * @param id ID сущности
     * @param entityRequest DTO с данными для обновления
     */
    public void updateEntity(String id, EntityRequest entityRequest) {
        given()
                .body(entityRequest)
                .when()
                .patch(BASE_PATH + "/patch/{id}", id)
                .then()
                .statusCode(204);
    }

    /**
     * Удаление сущности
     * @param id ID сущности
     */
    public void deleteEntity(String id) {
        given()
                .when()
                .delete(BASE_PATH + "/delete/{id}", id)
                .then()
                .statusCode(204);
    }
}
