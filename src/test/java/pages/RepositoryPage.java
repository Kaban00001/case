package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class RepositoryPage {


    private final SelenideElement
            setShouldInput = $(".position-relative"),
            //setOverInput = $(".BorderGrid").$(byText("Contributors")).ancestor(".BorderGrid-row").$$("ul li")
            //Как такую шляпу можно вынести?
            setNameInput = $(".Truncate-text--expandable"),
            setIconInput = $(".AppHeader-user"),
            setSignInput = $(byTagAndText("span", "Sign out"));


        public RepositoryPage setShouldHave (String value){
            setShouldInput.shouldHave(text(value));

            return this;
        }

        public RepositoryPage hoverOverFirstContributor () {
            $(".BorderGrid")
                    .$(byText("Contributors")).ancestor(".BorderGrid-row")
                    .$$("ul li").first().hover();

            return this;

        }

        public RepositoryPage verifyContributorName (String value){
            setNameInput.shouldHave(text(value));

            return this;

        }

        public RepositoryPage clickOnTheIcon () {
            setIconInput.click();

            return this;
        }

        public LogoutPage clickSignOut () {
            setSignInput.click();

            return new LogoutPage();
        }




    }
