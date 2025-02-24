package pages;

import pages.components.LogoutPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RepositoryPage {

        //Страница репозиториев https://github.com/selenide/selenide
        public RepositoryPage setShouldHave (String value){
            $(".position-relative").shouldHave(text(value));

            return this;
        }
        //Страница репозиториев https://github.com/selenide/selenide
        public RepositoryPage hoverOverFirstContributor () {
            $(".BorderGrid")
                    .$(byText("Contributors")).ancestor(".BorderGrid-row")
                    .$$("ul li").first().hover();

            return this;

        }
        //Страница репозиториев https://github.com/selenide/selenide
        public RepositoryPage verifyContributorName (String value){
            $(".Truncate-text--expandable").shouldHave(text(value));

            return this;

        }
        //Страница репозиториев https://github.com/selenide/selenide
        public RepositoryPage clickOnTheIcon () {
            $(".AppHeader-user").click();

            return this;
        }

        //Страница репозиториев https://github.com/selenide/selenide
        public LogoutPage clickSignOut () {
            $(byTagAndText("span", "Sign out")).click();

            return new LogoutPage();
        }




    }
