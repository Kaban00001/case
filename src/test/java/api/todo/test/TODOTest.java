package api.todo.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import todo.helpers.TestBase;
import todo.model.AuthResponse;
import todo.model.TodoResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class TODOTest extends TestBase {
    TODOApiClient client = new TODOApiClient();
    Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        client.register();
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    void registerTest() {
        AuthResponse response = client.register(faker.internet().emailAddress(), faker.internet().password());

        assertThat(response.getAccessToken()).isNotNull();
    }

    @Test
    @DisplayName("Логин пользователя")
    void loginTest() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        client.register(email, password);
        AuthResponse response = client.login(email, password);

        assertThat(response.getAccessToken()).isNotNull();
    }

    @Test
    @DisplayName("Создание задачи")
    void createTodoTest() {
        String title = faker.animal().name();
        String description = faker.hacker().abbreviation();

        TodoResponse response = client.createTodo(
                title,
                description,
                "2025-03-19",
                "21:00",
                false
        );

        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("Обновление задачи")
    void updateTodoTest() {
        String title = faker.animal().name();
        String description = faker.hacker().abbreviation();
        TodoResponse task = client.createTodo(
                title,
                description,
                "2025-03-19",
                "21:00",
                false
        );

        TodoResponse response = client.updateTodo(
                task.getId(),
                "Жопа",
                "Почесать жопу",
                "2025-03-20",
                "21:30",
                false
        );

        assertThat(response.getTitle()).isEqualTo("Жопа");
        assertThat(response.getDescription()).isEqualTo("Почесать жопу");
        assertThat(response.getDate()).isEqualTo("2025-03-20");
        assertThat(response.getTime()).isEqualTo("21:30");
        assertThat(response.isChecked()).isEqualTo(false);
    }

    @Test
    @DisplayName("Удаление задачи")
    void deleteTodoTest() {
        String title = faker.animal().name();
        String description = faker.hacker().abbreviation();
        TodoResponse response = client.createTodo(
                title,
                description,
                "2025-03-19",
                "21:00",
                false
        );

        client.deleteTodo(response.getId());
    }

    @Test
    @DisplayName("Получение задачи")
    void getTodoTest() {
        String title = faker.animal().name();
        String description = faker.hacker().abbreviation();
        TodoResponse task = client.createTodo(
                title,
                description,
                "2025-03-19",
                "21:00",
                false
        );

        TodoResponse response = client.getTodo(task.getId());

        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDescription()).isEqualTo(description);
        assertThat(response.getDate()).isEqualTo("2025-03-19");
        assertThat(response.getTime()).isEqualTo("21:00");
        assertThat(response.isChecked()).isEqualTo(false);
    }
}
