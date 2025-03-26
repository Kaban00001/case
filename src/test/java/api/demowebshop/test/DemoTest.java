package api.demowebshop.test;

import api.demowebshop.model.AddToCartResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest {

    private final ApiClient client = new ApiClient();

    @Test
    @DisplayName("Логин пользователя")
    void loginTest() {

        String coolie = client.login();

        assertThat(coolie).isNotNull();
    }

    @Test
    @DisplayName("Переход в раздел Apparel & Shoes")
    void goToApparelShoesTest() {

        Response response = client.goToApparelShoes();

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Переход в карточку продукта")
    void goToNameProductTest() {

        Response response = client.goToNameProduct();

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Добавление продукта в корзину")
    void AddProductToCartTest() {

        Response response = client.AddProductToCart();

        AddToCartResponse cart = response.as(AddToCartResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(cart.getUpdatetopcartsectionhtml()).isNotNull();
    }
}
