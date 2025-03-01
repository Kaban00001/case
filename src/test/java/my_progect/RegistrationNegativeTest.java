package my_progect;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationNegativeTest {



    private final Faker faker = new Faker();

    private String generateUsernameWithDashAndNumbers() {
            String username = faker.name().username();
            username = username.replace(".", "");
            String numbers = faker.number().digits(3);
            return username + "-" + numbers;
    }



    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;

    }


    @Test
    void testInvalidEmail() {

        String invalidEmail = faker.name().username();
        String validPassword = faker.internet().password();
        String validUsername = generateUsernameWithDashAndNumbers();


        open("https://github.com/signup");
        $("[data-target='text-suggester.input']").setValue(invalidEmail);
        $("[placeholder='Password']").setValue(validPassword);
        $("#login").setValue(validUsername);
        $(byTagAndText("span", "Continue")).click();
        $$(".error").shouldHave(CollectionCondition.sizeGreaterThan(0));

    }

        @Test
        void testInvalidPassword() {

            String validEmail = faker.internet().emailAddress();
            String invalidPassword = faker.lorem().characters(3);
            String validUsername = generateUsernameWithDashAndNumbers();


            open("https://github.com/signup");
            $("[data-target='text-suggester.input']").setValue(validEmail);
            $("[placeholder='Password']").setValue(invalidPassword);
            $("#login").setValue(validUsername);
            $(byTagAndText("span", "Continue")).click();
            $$(".error").shouldHave(CollectionCondition.sizeGreaterThan(0));

        }

    @Test
    void testInvalidUserName() {

        String validEmail = faker.internet().emailAddress();
        String validPassword = faker.internet().password();
        String invalidUsername = faker.name().username();


        open("https://github.com/signup");
        $("[data-target='text-suggester.input']").setValue(validEmail);
        $("[placeholder='Password']").setValue(validPassword);
        $("#login").setValue(invalidUsername);
        $(byTagAndText("span", "Continue")).click();
        $$(".error").shouldHave(CollectionCondition.sizeGreaterThan(0));

    }



}
