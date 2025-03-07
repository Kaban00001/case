package demowebshop.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import demowebshop.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    private static final String MAIN_PAGE_URL = "https://demowebshop.tricentis.com/";
    private final Faker faker = new Faker();
     private final MainPage mainPage = new MainPage();

    @BeforeAll
     static void setUP() {
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void registerNewUser() {

        String password = faker.internet().password();

        Selenide.open(MAIN_PAGE_URL, MainPage.class)
                .clickRegisterButton()
                .clickMaleGender()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(password)
                .setConfirmPassword(password)
                .clickRegisterButton()
                .verifyRegistrationSuccessMessage()
                .clickContinueButton();
    }

    @AfterEach
    void logoutUser() {
        mainPage
                .clickLogoutButton();
    }
}
