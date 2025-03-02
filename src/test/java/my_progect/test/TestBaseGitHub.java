package my_progect.test;

import org.junit.jupiter.api.AfterEach;
import my_progect.pages.RepositoryPage;

public class TestBaseGitHub {
    RepositoryPage repositoryPage = new RepositoryPage();

    @AfterEach
    void logoutAll() {
        repositoryPage
                .clickOnTheIcon()
                .clickSignOut()
                .clickSignOutAgain();
    }

}
