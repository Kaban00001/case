package ui.my_progect.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class RepositoryPage {


    private final SelenideElement
            setShouldInput = $(".position-relative"),
            setNameInput = $(".Truncate-text--expandable"),
            setIconInput = $(".AppHeader-user"),
            setSignInput = $(byTagAndText("span", "Sign out"));

    @Step("Проверить название репозитория selenide / selenide")
    public RepositoryPage setShouldHave(String value) {
        setShouldInput.shouldHave(text(value));

        return this;
    }

    @Step("Навести курсор на первого учатсника")
    public RepositoryPage hoverOverFirstContributor() {
        $(".BorderGrid")
                .$(byText("Contributors")).ancestor(".BorderGrid-row")
                .$$("ul li").first().hover();

        return this;

    }

    @Step("Проверить имя Andrei Solntsev")
    public RepositoryPage verifyContributorName(String value) {
        setNameInput.shouldHave(text(value));

        return this;

    }

    @Step("Нажать на свою иконку")
    public RepositoryPage clickOnTheIcon() {
        setIconInput.click();

        return this;
    }

    @Step("Нажать на Sign out")
    public LogoutPage clickSignOut() {
        setSignInput.click();

        return new LogoutPage();
    }


}
