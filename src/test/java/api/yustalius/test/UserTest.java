package api.yustalius.test;

import api.yustalius.model.auth.RegisterResponse;
import api.yustalius.model.error.ErrorResponse;
import api.yustalius.model.user.UserResponse;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("yustalius")
public class UserTest {
    private final ApiClient client = new ApiClient();
    private final Faker faker = new Faker();
    private RegisterResponse user = null;

    @BeforeEach
    public void setUp() {
        user = client.register();
    }

    @Test
    @DisplayName("Получение пользователя по ID")
    void getUserTest() {

        Response response1 = client.getUser(user.getId());

        UserResponse response = response1.as(UserResponse.class);

        assertThat(response.getId()).isEqualTo(user.getId());
        assertThat(response.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(response.getLastName()).isEqualTo(user.getLastName());
        assertThat(response.getAge()).isEqualTo(user.getAge());
    }

    @Test
    @DisplayName("Обновление пользователя по ID")
    void updateUserTest() {
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        int age = faker.number().numberBetween(18, 99);

        UserResponse response = client.updateUser(user.getId(), firstname, lastname, age);

        assertThat(response.getId()).isEqualTo(user.getId());
        assertThat(response.getFirstName()).isEqualTo(firstname);
        assertThat(response.getLastName()).isEqualTo(lastname);
        assertThat(response.getAge()).isEqualTo(age);
    }


    @Test
    @DisplayName("Удаление пользователя по ID")
    void deleteUserTest() {

        client.deleteUser(user.getId());

        Response response = client.getUser(user.getId());
        ErrorResponse error = response.as(ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(404);
        assertThat(error.getErrorCode()).isEqualTo("USER_NOT_FOUND");
    }
}
