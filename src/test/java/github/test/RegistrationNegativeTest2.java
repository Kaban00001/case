package github.test;

import com.github.javafaker.Faker;
import github.pages.RegistrationPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RegistrationNegativeTest2 {

    RegistrationPage registrationPage = new RegistrationPage();

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testInvalidRegistration(String email, String password, String username, String errorMessage) {

        registrationPage.openMainPage()
                .setEmail(email)
                .setPassword(password)
                .setUserName(username)
                .clickContinueButton()
                .checkError(errorMessage);

    }

    static Stream<Arguments> dataProvider() {
        Faker faker = new Faker();
        return Stream.of(
                Arguments.of(
                        faker.name().username(),
                        faker.internet().password(),
                        faker.name().firstName() + faker.name().lastName(),
                        " Email is invalid or already taken "
                ),
                Arguments.of(
                        faker.internet().emailAddress(),
                        faker.lorem().characters(3),
                        faker.name().firstName() + faker.name().lastName(),
                        " Password is too short  "
                ),
                Arguments.of(
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        faker.name().username() + "!",
                        " Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.  "
                )
        );
    }


}

