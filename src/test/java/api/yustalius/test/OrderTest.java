package api.yustalius.test;

import api.yustalius.model.auth.RegisterResponse;
import api.yustalius.model.order.CreateOrderRequest;
import api.yustalius.model.order.OrderResponse;
import api.yustalius.model.product.ProductResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("yustalius")
public class OrderTest {
    private final ApiClient client = new ApiClient();
    private final Faker faker = new Faker();
    private RegisterResponse user = null;

    @BeforeEach
    public void setUp() {
        user = client.register();
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
}
