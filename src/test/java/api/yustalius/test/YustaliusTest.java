package api.yustalius.test;

import api.yustalius.helpers.PropertiesHelper;
import api.yustalius.model.auth.LoginResponse;
import api.yustalius.model.auth.RegisterResponse;
import api.yustalius.model.error.ErrorResponse;
import api.yustalius.model.order.CreateOrderRequest;
import api.yustalius.model.order.OrderResponse;
import api.yustalius.model.product.ProductResponse;
import api.yustalius.model.user.UserResponse;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YustaliusTest {
    PropertiesHelper props = new PropertiesHelper("src/test/resources/config.properties");
    private final ApiClient client = new ApiClient();
    private final Faker faker = new Faker();
    private RegisterResponse user = null;

    @BeforeEach
    public void setUp() {
        user = client.register();
    }

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

    @Test
    @DisplayName("Добавление заказа")
    void createOrderTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse productResponse = client.createProduct(productName, description, price);
        int productId = productResponse.getId();

        List<CreateOrderRequest.Product> products = new ArrayList<>();
        CreateOrderRequest.Product product = new CreateOrderRequest.Product();
        product.setProductId(productId);
        int requestedQuantity = faker.number().numberBetween(1, 10);
        product.setQuantity(requestedQuantity);
        product.setPrice(price);
        products.add(product);

        OrderResponse response = client.createOrder(user.getId(), products);

        assertThat(response.getOrderId()).isNotNull();
        assertThat(response.getUserId()).isEqualTo(user.getId());
        assertThat(response.getStatus()).isEqualTo("PENDING");
        assertThat(response.getProducts())
                .anyMatch(p ->
                        p.getProductId() == productId &&
                                p.getQuantity() == requestedQuantity &&
                                p.getPrice() == price
                );
    }

    @Test
    @DisplayName("Обновление статуса заказа")
    void UpdateStatusOrderTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse productResponse = client.createProduct(productName, description, price);
        int productId = productResponse.getId();

        List<CreateOrderRequest.Product> products = new ArrayList<>();
        CreateOrderRequest.Product product = new CreateOrderRequest.Product();
        product.setProductId(productId);
        int requestedQuantity = faker.number().numberBetween(1, 10);
        product.setQuantity(requestedQuantity);
        product.setPrice(price);
        products.add(product);

        OrderResponse orderResponse = client.createOrder(user.getId(), products);
        int orderId = orderResponse.getOrderId();

        OrderResponse response = client.updateStatusOrder(orderId, "APPROVED");

        assertThat(response.getOrderId()).isEqualTo(orderId);
        assertThat(response.getUserId()).isEqualTo(user.getId());
        assertThat(response.getStatus()).isEqualTo("APPROVED");
    }

    @Test
    @DisplayName("Получение всех заказов")
    void getOrdersTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        String productName1 = faker.commerce().productName();
        String description1 = faker.commerce().promotionCode();
        int price1 = faker.number().numberBetween(1, 1000);

        client.createProduct(productName, description, price);
        client.createProduct(productName1, description1, price1);

        List<OrderResponse> response = client.getOrders();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Получение заказа по ID")
    void getOrderTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse productResponse = client.createProduct(productName, description, price);
        int productId = productResponse.getId();

        List<CreateOrderRequest.Product> products = new ArrayList<>();
        CreateOrderRequest.Product product = new CreateOrderRequest.Product();
        product.setProductId(productId);
        int requestedQuantity = faker.number().numberBetween(1, 10);
        product.setQuantity(requestedQuantity);
        product.setPrice(price);
        products.add(product);

        OrderResponse orderResponse = client.createOrder(user.getId(), products);
        int orderId = orderResponse.getOrderId();

        OrderResponse response = client.getOrder(orderId);

        assertThat(response.getOrderId()).isEqualTo(orderId);
        assertThat(response.getUserId()).isEqualTo(user.getId());
        assertThat(response.getStatus()).isEqualTo("PENDING");
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    @DisplayName("Получение заказа по UserID")
    void getOrdersUserIdTest() {
        String productName = faker.commerce().productName();
        String description = faker.commerce().promotionCode();
        int price = faker.number().numberBetween(1, 1000);

        ProductResponse productResponse = client.createProduct(productName, description, price);
        int productId = productResponse.getId();

        List<CreateOrderRequest.Product> products = new ArrayList<>();
        CreateOrderRequest.Product product = new CreateOrderRequest.Product();
        product.setProductId(productId);
        int requestedQuantity = faker.number().numberBetween(1, 10);
        product.setQuantity(requestedQuantity);
        product.setPrice(price);
        products.add(product);

        OrderResponse orderResponse = client.createOrder(user.getId(), products);
        int orderId = orderResponse.getOrderId();

        List<OrderResponse> response = client.getOrdersUserId(user.getId());

        assertThat(response).isNotEmpty();
        assertThat(response)
                .anyMatch(responses -> responses.getOrderId() == orderId);
        assertThat(response)
                .anyMatch(responses -> responses.getUserId() == user.getId());
        assertThat(response)
                .anyMatch(responses -> "PENDING".equals(responses.getStatus()));
        assertThat(response)
                .anyMatch(responses ->
                        responses.getProducts().stream()
                                .anyMatch(p ->
                                        p.getProductId() == productId &&
                                                p.getQuantity() == requestedQuantity &&
                                                p.getPrice() == price
                                ));
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


