package github.test;

import com.github.javafaker.Faker;
import github.pages.RegistrationPage;
import io.qameta.allure.Allure;
import my_progect.test.TestBaseGitHub;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RegistrationNegativeTest2 extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @ParameterizedTest(name = "{4}")
    @Tag("master")
    @MethodSource("dataProvider")
    void testInvalidRegistration(String email, String password, String username, String errorMessage, String text) {

        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(text));


        registrationPage
                .setEmail(email)
                .setPassword(password)
                .setUserName(username)
                .clickContinueButton();
        //.checkError(errorMessage);

    }

    static Stream<Arguments> dataProvider() {
        Faker faker = new Faker();
        return Stream.of(
                Arguments.of(
                        faker.name().username(),
                        faker.internet().password(),
                        faker.name().firstName() + faker.name().lastName(),
                        "Email is invalid or already taken",
                        "Регистрация пользователя с невалидным Email"

                ),
                Arguments.of(
                        faker.internet().emailAddress(),
                        faker.lorem().characters(3),
                        faker.name().firstName() + faker.name().lastName(),
                        "Password is too short",
                        "Регистрация пользователя с невалидным Password"

                ),
                Arguments.of(
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        faker.name().username() + "!",
                        "Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.  ",
                        "Регистрация пользователя с невалидным UserName"
                )
        );
    }
}

