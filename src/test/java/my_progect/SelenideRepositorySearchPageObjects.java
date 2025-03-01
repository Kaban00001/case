package my_progect;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RepositoryPage;
import pages.components.WelcomePage;



public class SelenideRepositorySearchPageObjects {
    RepositoryPage repositoryPage = new RepositoryPage();

    @BeforeAll
    static void setupAll() {
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {


        WelcomePage.openPage()
                .clickButtonSignIn()
                .setLogin("awerinzh@yandex.ru")
                .setPassword("averin228337")
                .clickSignInButton()
                .clickTheInputField()
                .enterInTheField("selenide")
                .clickOnTheFirstLink()
                .setShouldHave("selenide / selenide")
                .hoverOverFirstContributor()
                .verifyContributorName("Andrei Solntsev")
                .clickOnTheIcon()
                .clickSignOut()
                .clickSignOutAgain();


    }







}



