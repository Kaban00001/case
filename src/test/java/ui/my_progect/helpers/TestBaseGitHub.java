package ui.my_progect.helpers;

import com.codeborne.selenide.Selenide;
import ui.demowebshop.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import ui.my_progect.pages.RepositoryPage;

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
