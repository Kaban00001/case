package api.todo.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import todo.model.AuthRequest;
import todo.model.AuthResponse;
import todo.model.TodoRequest;
import todo.model.TodoResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TODOApiClient {
    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://2.59.41.2:7320/")
            .setContentType(JSON)
            .build();
    public Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();

    public AuthResponse register() {
        return register(email, password);
    }

    @Step("Регистрация пользователя")
    public AuthResponse register(String email, String password) {
        AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setPassword(password);

        return given(spec)
                .body(request)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .extract().as(AuthResponse.class);
    }

    @Step("Логин пользователя")
    public AuthResponse login(String email, String password) {
        AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setPassword(password);

        return given(spec)
                .body(request)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);
    }

    @Step("Создание задачи")
    public TodoResponse createTodo(String title, String description, String date, String time, boolean checked) {
        TodoRequest request = new TodoRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setDate(date);
        request.setTime(time);
        request.setChecked(checked);

        return given(spec)
                .header("Authorization", "Bearer " + login(email, password).getAccessToken())
                .body(request)
                .when()
                .post("/api/todos/create")
                .then()
                .statusCode(201)
                .extract().as(TodoResponse.class);
    }

    @Step("Обновление задачи")
    public TodoResponse updateTodo(int todoId, String title, String description, String date, String time, boolean checked) {
        TodoRequest request = new TodoRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setDate(date);
        request.setTime(time);
        request.setChecked(checked);

        return given(spec)
                .header("Authorization", "Bearer " + login(email, password).getAccessToken())
                .body(request)
                .when()
                .patch("/api/todos/edit/" + todoId)
                .then()
                .statusCode(200)
                .extract().as(TodoResponse.class);
    }

    @Step("Удаление задачи")
    public void deleteTodo(int todoId) {
        given(spec)
                .header("Authorization", "Bearer " + login(email, password).getAccessToken())
                .when()
                .delete("/api/todos/delete/" + todoId)
                .then()
                .statusCode(204);
    }

    @Step("Получение задачи")
    public TodoResponse getTodo(int todoId) {
        return given(spec)
                .header("Authorization", "Bearer " + login(email, password).getAccessToken())
                .when()
                .get("/api/todos/" + todoId)
                .then()
                .statusCode(200)
                .extract().as(TodoResponse.class);

    }
}



