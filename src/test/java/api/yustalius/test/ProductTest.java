package api.yustalius.test;

import api.yustalius.model.auth.RegisterResponse;
import api.yustalius.model.error.ErrorResponse;
import api.yustalius.model.product.ProductResponse;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("yustalius")
public class ProductTest {
    private final ApiClient client = new ApiClient();
    private final Faker faker = new Faker();
    RegisterResponse user = null;

    @BeforeEach
    public void setUp() {
        user = client.register();
    }

    @Test
    @DisplayName("Добавление продукта")
    void createTodoTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse response = client.createProduct(productName, description, price);

        assertThat(response.getProductName()).isEqualTo(productName);
        assertThat(response.getDescription()).isEqualTo(description);
        assertThat(response.getPrice()).isEqualTo(price);
    }

    @Test
    @DisplayName("Обновление продукта")
    void updateProductTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse productResponse = client.createProduct(productName, description, price);
        int productId = productResponse.getId();

        String updateProductName = faker.commerce().productName();
        String updateDescription = faker.commerce().promotionCode();
        int updatePrice = faker.number().numberBetween(1, 1000);

        ProductResponse updatedProduct = client.updateProduct(productId, updateProductName, updateDescription, updatePrice);

        assertThat(updatedProduct.getId()).isEqualTo(productId);
        assertThat(updatedProduct.getProductName()).isEqualTo(updateProductName);
        assertThat(updatedProduct.getDescription()).isEqualTo(updateDescription);
        assertThat(updatedProduct.getPrice()).isEqualTo(updatePrice);
    }

    @Test
    @DisplayName("Получение продукта по ID")
    void getProductTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse productResponse = client.createProduct(productName, description, price);
        int productId = productResponse.getId();


        Response response = client.getProduct(productId);
        ProductResponse todo = response.as(ProductResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(todo.getId()).isEqualTo(productId);
        assertThat(todo.getProductName()).isEqualTo(productName);
        assertThat(todo.getDescription()).isEqualTo(description);
        assertThat(todo.getPrice()).isEqualTo(price);
    }

    @Test
    @DisplayName("Получение всех продуктов")
    void getProductsTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        String productName1 = faker.commerce().productName();
        String description1 = faker.commerce().promotionCode();
        int price1 = faker.number().numberBetween(1, 1000);

        client.createProduct(productName, description, price);
        client.createProduct(productName1, description1, price1);

        List<ProductResponse> response = client.getProducts();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Удаление продукта по ID")
    void deleteProductTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse createdProduct = client.createProduct(productName, description, price);
        int productId = createdProduct.getId();

        client.deleteProduct(productId);

        Response response = client.getProduct(productId);

        ErrorResponse error = response.as(ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(404);
        assertThat(error.getErrorCode()).isEqualTo("PRODUCT_NOT_FOUND");


    }
}
