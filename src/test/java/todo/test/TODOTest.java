package todo.test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import todo.helpers.TestBase;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("TodoTests")
public class TODOTest extends TestBase {
    private final TODOApiClient client = new TODOApiClient();
    private final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        client.register();
    }

    @Test
    void registerTest() {
        String accessToken = client.register(faker.internet().emailAddress(), faker.internet().password());

        assertThat(accessToken).isNotNull();
    }

    @Test
    void loginTest() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        client.register(email, password);
        String accessToken = client.login(email, password);

        assertThat(accessToken).isNotNull();
    }

    @Test
    void createTodoTest() {
        String title = faker.animal().name();
        String description = faker.hacker().adjective();
        Response task = client.createTask(
                title,
                description,
                "2111-06-22",
                "21:00",
                false
        );

        assertThat((String) task.path("description")).isEqualTo(description);
        assertThat((String) task.path("title")).isEqualTo(title);
    }

    @Test
    void updateTodoTest() {
        String title = faker.animal().name();
        String description = faker.hacker().adjective();
        Response task = client.createTask(
                title,
                description,
                "2111-06-22",
                "21:00",
                false
        );

        Response updateTask = client.updateTask(
                task.path("id"),
                "Test",
                "description",
                "2110-06-22",
                "21:30",
                true
        );

        assertThat((String) updateTask.path("description")).isEqualTo("description");
        assertThat((String) updateTask.path("title")).isEqualTo("Test");
    }

    @Test
    void deleteTask() {
        int taskId = client.createTask(
                faker.animal().name(),
                faker.hacker().adjective(),
                "2111-06-22",
                "21:00",
                false
        ).path("id");

        client.deleteTask(taskId);

        Response response = client.getTask(taskId);

        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    void getTaskTest() {
        Response task = client.createTask(
                faker.animal().name(),
                faker.hacker().adjective(),
                "2111-06-22",
                "21:00",
                false
        );

        Response getResponse = client.getTask(task.path("id"));

        assertThat(getResponse.statusCode()).isEqualTo(200);
        assertThat((String) getResponse.path("title")).isEqualTo(task.path("title"));
        assertThat((String) getResponse.path("description")).isEqualTo(task.path("description"));
        assertThat((String) getResponse.path("date")).isEqualTo(task.path("date"));
        assertThat((String) getResponse.path("time")).isEqualTo(task.path("time"));
        assertThat((Boolean) getResponse.path("checked")).isEqualTo(task.path("checked"));
    }
}



