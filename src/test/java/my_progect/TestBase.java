package my_progect;

import org.junit.jupiter.api.AfterEach;
import pages.RepositoryPage;


public class TestBase {
    RepositoryPage repositoryPage = new RepositoryPage();


    @AfterEach
    void logoutAll() {
        repositoryPage
                .clickOnTheIcon()
                .clickSignOut()
                .clickSignOutAgain();
    }



}
