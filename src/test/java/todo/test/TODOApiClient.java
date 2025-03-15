package todo.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TODOApiClient {
    private final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://2.59.41.2:7320")
            .setContentType(JSON)
            .build();
    private final Faker faker = new Faker();
    private final String
            email = faker.internet().emailAddress(),
            password = faker.internet().password();

    @Step("Регистрация пользователя для подготовки тестов")
    public int register() {
        String requestBody = "{"
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        return given(spec)
                .body(requestBody)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .extract().response()
                .path("user.id");
    }

    @Step("Регистрация пользователя")
    public String register(String email, String password) {
        String requestBody = "{"
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        return given(spec)
                .body(requestBody)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .extract().response()
                .path("accessToken");
    }

    @Step("Логин пользователя")
    public String login(String email, String password) {
        String requestBody = "{"
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        return given(spec)
                .body(requestBody)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract().response()
                .path("accessToken");
    }

    @Step("Создание задачи")
    public Response createTask(String title, String description, String date, String time, boolean checked) {
        String requestBody = "{"
                + "\"title\": \"" + title + "\","
                + "\"description\": \"" + description + "\","
                + "\"date\": \"" + date + "\","
                + "\"time\": \"" + time + "\","
                + "\"checked\": " + checked
                + "}";

        return given(spec)
                .header("Authorization", "Bearer " + login(email, password))
                .body(requestBody)
                .when()
                .post("/api/todos/create")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    @Step("Обновление задачи")
    public Response updateTask(int todoId, String title, String description, String date, String time, boolean checked) {
        String requestBody = "{"
                + "\"title\": \"" + title + "\","
                + "\"description\": \"" + description + "\","
                + "\"date\": \"" + date + "\","
                + "\"time\": \"" + time + "\","
                + "\"checked\": " + checked
                + "}";

        return given(spec)
                .header("Authorization", "Bearer " + login(email, password))
                .body(requestBody)
                .when()
                .patch("/api/todos/edit/" + todoId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    @Step("Удаление задачи")
    public void deleteTask(int todoId) {
        given(spec)
                .header("Authorization", "Bearer " + login(email, password))
                .when()
                .delete("/api/todos/delete/" + todoId)
                .then()
                .statusCode(204);

    }

    @Step("Получение задачи")
    public Response getTask(int todoId) {
        return given(spec)
                .header("Authorization", "Bearer " + login(email, password))
                .when()
                .get("/api/todos/" + todoId)
                .then()
                .extract()
                .response();
    }
}










