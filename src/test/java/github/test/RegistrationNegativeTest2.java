package github.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import github.pages.RegistrationPage;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RegistrationNegativeTest2 {
    private static final String REGISTRATION_PAGE_URL = "https://github.com/signup";

    @BeforeAll
    static void setUP() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true) // Включить скриншоты
                .savePageSource(true));
    }

    @ParameterizedTest(name = "{3}")
    @Tag("master")
    @MethodSource("dataProvider")
    void testInvalidRegistration(String email, String password, String username, String errorMessage) {

        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(errorMessage));
        Allure.parameter("Текст ошибки", errorMessage.replace("\n", "<br>"));

        Selenide.open(REGISTRATION_PAGE_URL, RegistrationPage.class)
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
                        "Невалидный Email\nEmail is invalid or already taken"


                ),
                Arguments.of(
                        faker.internet().emailAddress(),
                        faker.lorem().characters(3),
                        faker.name().firstName() + faker.name().lastName(),
                        "Невалидный Password\nPassword is too short"

                ),
                Arguments.of(
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        faker.name().username() + "!",
                        "Невалидный UserName\nUsername may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.  "

                )
        );
    }


}

