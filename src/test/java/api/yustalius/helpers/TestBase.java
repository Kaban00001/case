package api.yustalius.helpers;

import org.junit.jupiter.api.BeforeAll;

import static todo.helpers.RestAssuredSpec.setupRestAssured;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        setupRestAssured();
    }
}