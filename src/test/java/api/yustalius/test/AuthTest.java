package api.yustalius.test;

import api.yustalius.model.auth.LoginResponse;
import api.yustalius.model.auth.RegisterResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("yustalius")
@Execution(ExecutionMode.CONCURRENT)
public class AuthTest {
    private final ApiClient client = new ApiClient();
    private final Faker faker = new Faker();

    @Test
    @DisplayName("Регистрация нового пользователя")
    void registerTest() {
        String username = faker.name().username();
        String password = faker.internet().password();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        int age = faker.number().numberBetween(18, 99);

        RegisterResponse response = client.register(username, password, firstname, lastname, age);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getFirstName()).isEqualTo(firstname);
        assertThat(response.getLastName()).isEqualTo(lastname);
        assertThat(response.getAge()).isEqualTo(age);
    }

    @Test
    @DisplayName("Логин пользователя")
    void loginTest() {
        String username = faker.name().username();
        String password = faker.internet().password();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        int age = faker.number().numberBetween(18, 99);

        client.register(username, password, firstname, lastname, age);

        LoginResponse response = client.login(username, password);

        assertThat(response.getToken()).isNotNull();
    }
}


