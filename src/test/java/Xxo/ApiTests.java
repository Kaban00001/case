package Xxo;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTests {

    private static final String BASE_URL = "http://2.59.41.2:7320";
    private static String accessToken;
    private static String refreshToken;
    private static String todoId;
    private static final Faker faker = new Faker();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Order(1)
    public void testRegisterUser() {
        String email = faker.internet().emailAddress();
        String password = "1234567";

        String requestBody = "{"
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/auth/register")
                .then()
                .log().all()
                .statusCode(201)
                .body("user.email", equalTo(email))
                .extract()
                .response();

        accessToken = response.path("accessToken");
        refreshToken = response.path("refreshToken");

        System.out.println("Registered Email: " + email);
        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);
    }

    @Test
    @Order(2)
    public void testUpdatePassword() {
        String requestBody = "{"
                + "\"password\": \"1234567\","
                + "\"newPassword\": \"123456\""
                + "}";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken=" + refreshToken)
                .body(requestBody)
                .when()
                .patch("/api/auth/update-pass")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void testCreateTodo() {
        String requestBody = "{"
                + "\"title\": \"Узнать что такое Api\","
                + "\"description\": \"Хуй знает как это сделать\","
                + "\"date\": \"2025-03-11\","
                + "\"time\": \"21:30\","
                + "\"checked\": true"
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken=" + refreshToken)
                .body(requestBody)
                .when()
                .post("/api/todos/create")
                .then()
                .log().all()
                .statusCode(201)
                .body("title", equalTo("Узнать что такое Api"))
                .body("description", equalTo("Хуй знает как это сделать"))
                .body("date", equalTo("2025-03-11"))
                .body("time", equalTo("21:30"))
                .body("checked", equalTo(true))
                .extract()
                .response();

        todoId = response.path("id").toString();
        System.out.println("Created Todo ID: " + todoId);
    }

    @Test
    @Order(4)
    public void testUpdateTodo() {
        String requestBody = "{"
                + "\"title\": \"Узнать что такое Api\","
                + "\"description\": \"Хуй знает как это сделать\","
                + "\"date\": \"2025-03-11\","
                + "\"time\": \"21:30\","
                + "\"checked\": false"
                + "}";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken=" + refreshToken)
                .body(requestBody)
                .when()
                .patch("/api/todos/edit/" + todoId)
                .then()
                .log().all()
                .statusCode(200)
                .body("title", equalTo("Узнать что такое Api"))
                .body("description", equalTo("Хуй знает как это сделать"))
                .body("date", equalTo("2025-03-11"))
                .body("time", equalTo("21:30"))
                .body("checked", equalTo(false));
    }

    @Test
    @Order(5)
    public void testDeleteTodo() {
        given()
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken=" + refreshToken)
                .when()
                .delete("/api/todos/delete/" + todoId)
                .then()
                .log().all()
                .statusCode(204);
    }
}