package demowebshop.test;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import demowebshop.pages.LogoutPage;
import demowebshop.pages.RegistrationPage;
import demowebshop.pages.SuccessPage;
import demowebshop.pages.WelcomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected final Faker faker = new Faker();

    @BeforeEach
    void registerNewUser() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        Configuration.holdBrowserOpen = true;

        WelcomePage
                .openPage()
                .clickRegisterButton();
        RegistrationPage
                .clickMaleGender()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(password)
                .clickRegisters();
        SuccessPage
                .verifyRegistrationSuccessMessage(" Your registration completed ")
                .clickContinueButton();
    }

    @AfterEach
    void logoutUser() {
        LogoutPage
                .clickLogoutButton();
    }
}
