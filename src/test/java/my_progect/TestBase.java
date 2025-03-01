package my_progect;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.RepositoryPage;
import pages.components.MainPage;
import pages.components.WelcomePage;

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
