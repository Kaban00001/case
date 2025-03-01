package my_progect;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RepositoryPage;
import pages.components.MainPage;
import pages.components.WelcomePage;



public class SelenideRepositorySearchPageObjects extends TestBase {
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



