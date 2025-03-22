package ui.github.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import ui.demowebshop.helpers.Attach;
import ui.github.pages.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    private static final String REGISTRATION_PAGE_URL = "https://github.com/signup";

    @BeforeAll
    static void setUP() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

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
    void open() {
        Selenide.open(REGISTRATION_PAGE_URL, RegistrationPage.class);
    }

    @AfterEach
    void tearDown() {
        addAttachments();
        Selenide.closeWebDriver();
    }

    private void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
