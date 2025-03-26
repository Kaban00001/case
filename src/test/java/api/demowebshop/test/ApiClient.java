package api.demowebshop.test;

import api.yustalius.helpers.PropertiesHelper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    PropertiesHelper props = new PropertiesHelper("src/test/resources/demowebshop.properties");
    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("https://demowebshop.tricentis.com")
            .build();
    private String email = props.getProperty("email");
    private String password = props.getProperty("password");

    @Step("Регистрация пользователя")
    public Response register() {
        Map<String, String> formData = new HashMap<>();
        formData.put("FirstName", "Evgeny");
        formData.put("LastName", "Averin");
        formData.put("Email", "1caban@mail.ru");
        formData.put("Password", "123456");
        formData.put("ConfirmPassword", "123456");

        return RestAssured.given(spec)
                .formParams(formData)
                .post("/register")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Логин пользователя")
    public String login() {

        return RestAssured.given(spec)
                .log().all()
                .formParams("Email", email)
                .formParams("Password", password)
                .formParams("RememberMe", "false")
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .log().all()
                .extract().cookie("NOPCOMMERCE.AUTH");
    }

    @Step("Переход в раздел Apparel & Shoes")
    public Response goToApparelShoes() {
        return RestAssured.given(spec)
                .when()
                .get("/apparel-shoes")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Переход в карточку продукта")
    public Response goToNameProduct() {
        return RestAssured.given(spec)
                .when()
                .get("/50s-rockabilly-polka-dot-top-jr-plus-size")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Добавление продукта в корзину")
    public Response AddProductToCart() {


        return RestAssured.given(spec)
                .contentType("application/x-www-form-urlencoded")
                .formParams("product_attribute_5_7_1", "1")
                .formParams("addtocart_5.EnteredQuantity", "1")
                .post("/addproducttocart/details/5/1")
                .then()
                .log().all()
                .extract().response();
    }
}
