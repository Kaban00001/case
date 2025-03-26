package api.demowebshop.test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class RegisrationTest {

    private final ApiClient client = new ApiClient();
    private final Faker faker = new Faker();

    @Test
    void registerTest() {
        Response response = client.register();

    }
}
