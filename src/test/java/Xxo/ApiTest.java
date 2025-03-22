package Xxo;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private static final String BASE_URL = "http://2.59.41.2:7320";
    private static final Faker faker = new Faker();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    private String[] registerUser() {
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

        String accessToken = response.path("accessToken");
        String refreshToken = response.path("refreshToken");

        System.out.println("Registered Email: " + email);
        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);

        return new String[]{accessToken, refreshToken};
    }

    @Test
    public void testUpdatePassword() {
        String[] tokens = registerUser();
        String accessToken = tokens[0];
        String refreshToken = tokens[1];

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
    public void testCreateTodo() {
        String[] tokens = registerUser();
        String accessToken = tokens[0];
        String refreshToken = tokens[1];

        String requestBody = "{"
                + "\"title\": \"Узнать что такое Api\","
                + "\"description\": \"Хуй знает как это сделать\","
                + "\"date\": \"2025-03-11\","
                + "\"time\": \"21:30\","
                + "\"checked\": true"
                + "}";

        given()
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
                .body("checked", equalTo(true));
    }

    @Test
    public void testUpdateTodo() {
        String[] tokens = registerUser();
        String accessToken = tokens[0];
        String refreshToken = tokens[1];

        String createRequestBody = "{"
                + "\"title\": \"Узнать что такое Api\","
                + "\"description\": \"Хуй знает как это сделать\","
                + "\"date\": \"2025-03-11\","
                + "\"time\": \"21:30\","
                + "\"checked\": true"
                + "}";

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken=" + refreshToken)
                .body(createRequestBody)
                .when()
                .post("/api/todos/create")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();

        String todoId = createResponse.path("id").toString();

        String updateRequestBody = "{"
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
                .body(updateRequestBody)
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
    public void testDeleteTodo() {
        String[] tokens = registerUser();
        String accessToken = tokens[0];
        String refreshToken = tokens[1];

        String createRequestBody = "{"
                + "\"title\": \"Узнать что такое Api\","
                + "\"description\": \"Хуй знает как это сделать\","
                + "\"date\": \"2025-03-11\","
                + "\"time\": \"21:30\","
                + "\"checked\": true"
                + "}";

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken=" + refreshToken)
                .body(createRequestBody)
                .when()
                .post("/api/todos/create")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();

        String todoId = createResponse.path("id").toString();

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