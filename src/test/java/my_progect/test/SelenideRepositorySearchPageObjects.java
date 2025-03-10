package my_progect.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import my_progect.pages.RepositoryPage;
import my_progect.pages.MainPage;
import my_progect.pages.WelcomePage;

public class SelenideRepositorySearchPageObjects extends TestBaseGitHub {
    RepositoryPage repositoryPage = new RepositoryPage();
    MainPage mainPage;

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        mainPage = WelcomePage.openPage()
                .clickButtonSignIn()
                .setLogin("awerinzh@yandex.ru")
                .setPassword("averin228337")
                .clickSignInButton();
    }

    @Test
    @Tag("master")
    @DisplayName("Поиск репозитория Selenide в GitHub")
    void shouldFindSelenideRepositoryAtTheTop() {
        mainPage
                .clickTheInputField()
                .enterInTheField("selenide")
                .clickOnTheFirstLink()
                .setShouldHave("selenide / selenide")
                .hoverOverFirstContributor()
                .verifyContributorName("Andrei Solntsev");
    }
}



