package ui.demowebshop.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import ui.demowebshop.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    private static final String MAIN_PAGE_URL = "https://demowebshop.tricentis.com/";
    private final Faker faker = new Faker();
    private final MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        String testEnv = System.getProperty("test.env", "local"); // по умолчанию local

        Configuration.pageLoadTimeout = 100000;
        Configuration.browserSize = "1920x1080";

        System.out.println("Test environment: " + testEnv);

        if ("selenoid".equals(testEnv)) {
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        } else {
            Configuration.browser = "chrome";
        }
    }

    @BeforeEach
    void registerNewUser() {

        String password = faker.internet().password();

        Selenide.open(TestBase.MAIN_PAGE_URL, MainPage.class)
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
    void tearDown() {
        logoutUser();
        addAttachments();
        Selenide.closeWebDriver();
    }

    private void logoutUser() {
        mainPage.clickLogoutButton();
    }

    private void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}