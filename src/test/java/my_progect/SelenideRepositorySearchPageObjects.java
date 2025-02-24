package my_progect;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import pages.RepositoryPage;
import pages.components.WelcomePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SelenideRepositorySearchPageObjects {
    RepositoryPage repositoryPage = new RepositoryPage();

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {

        Configuration.holdBrowserOpen = true;

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



