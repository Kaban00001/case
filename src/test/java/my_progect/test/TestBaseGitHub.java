package my_progect.test;

import com.codeborne.selenide.Selenide;
import demowebshop.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import my_progect.pages.RepositoryPage;

public class TestBaseGitHub {
    RepositoryPage repositoryPage = new RepositoryPage();

    @AfterEach
    void tearDown() {
        logoutAll();
        addAttachments();
        Selenide.closeWebDriver();
    }

    private void logoutAll() {
        repositoryPage
                .clickOnTheIcon()
                .clickSignOut()
                .clickSignOutAgain();
    }

    private void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
