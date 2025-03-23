package api.yustalius.test;

import api.yustalius.model.auth.LoginRequest;
import api.yustalius.model.auth.LoginResponse;
import api.yustalius.model.auth.RegisterRequest;
import api.yustalius.model.auth.RegisterResponse;
import api.yustalius.model.order.CreateOrderRequest;
import api.yustalius.model.order.OrderResponse;
import api.yustalius.model.order.UpdateOrderStatusRequest;
import api.yustalius.model.product.ProductRequest;
import api.yustalius.model.product.ProductResponse;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiClient {
    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080/")
            .setContentType(JSON)
            .build();
    private final Faker faker = new Faker();
    private String username = faker.internet().emailAddress();
    private String password = faker.internet().password();
    private String firstname = faker.name().firstName();
    private String lastname = faker.name().lastName();
    private int age = faker.number().numberBetween(18, 99);

    public RegisterResponse register() {
        return register(username, password, firstname, lastname, age);
    }

    @Step("Регистрация пользователя")
    public RegisterResponse register(String username, String password, String firstname, String lastname, int age) {
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setFirstName(firstname);
        request.setLastName(lastname);
        request.setAge(age);

        return given(spec)
                .body(request)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200)
                .extract().as(RegisterResponse.class);
    }

    @Step("Логин пользователя")
    public LoginResponse login(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        return given(spec)
                .body(request)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().as(LoginResponse.class);
    }

    @Step("Добавление продукта")
    public ProductResponse createProduct(String productName, String description, int price) {
        ProductRequest request = new ProductRequest();
        request.setProductName(productName);
        request.setDescription(description);
        request.setPrice(price);

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .body(request)
                .when()
                .post("/product/add")
                .then()
                .statusCode(200)
                .extract().as(ProductResponse.class);
    }

    @Step("Обновление продукта")
    public ProductResponse updateProduct(int productId, String productName, String description, int price) {
        ProductRequest request = new ProductRequest();
        request.setProductName(productName);
        request.setDescription(description);
        request.setPrice(price);

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .body(request)
                .when()
                .patch("/product/" + productId)
                .then()
                .statusCode(200)
                .extract().as(ProductResponse.class);
    }

    @Step("Получение продукта по Id")
    public Response getProduct(int productId) {

       return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .when()
                .get("/product/" + productId)
                .then()
                .extract().response();
    }


    @Step("Получение всех продуктов")
    public List<ProductResponse> getProducts() {

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .when()
                .get("/product")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", ProductResponse.class);
    }

    @Step("Удаление продукта по Id")
    public void deleteProduct(int productId) {
        given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .when()
                .delete("/product/" + productId)
                .then()
                .statusCode(200);

    }

    @Step("Добавление заказа")
    public OrderResponse createOrder(int userId, List<CreateOrderRequest.Product> products) {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setUserId(userId);
        request.setProducts(products);

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .body(request)
                .when()
                .post("/order/add")
                .then()
                .statusCode(200)
                .extract().as(OrderResponse.class);
    }

    @Step("Обновление статуса заказа")
    public OrderResponse updateStatusOrder(int orderID, String status) {
        UpdateOrderStatusRequest request = new UpdateOrderStatusRequest();
        request.setStatus(status);

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .body(request)
                .when()
                .post("/order/" + orderID + "/status")
                .then()
                .statusCode(200)
                .extract().as(OrderResponse.class);
    }


    @Step("Получение всех заказов")
    public List<OrderResponse> getOrders() {

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .when()
                .get("/order")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", OrderResponse.class);
    }

    @Step("Получение заказа по Id")
    public OrderResponse getOrder(int orderId) {

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .when()
                .get("/order/" + orderId)
                .then()
                .statusCode(200)
                .extract().as(OrderResponse.class);
    }

    @Step("Получение заказа по UserId")
    public List<OrderResponse> getOrdersUserId(int userId) {

        return given(spec)
                .header("Authorization", "Bearer " + login(username, password).getToken())
                .when()
                .get("/order/user/" + userId)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", OrderResponse.class);
    }
}
