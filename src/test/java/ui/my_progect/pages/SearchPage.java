package ui.my_progect.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private final SelenideElement setFirstInput = $(".search-title");

    @Step("Нажать на первый репозиторий")
    public RepositoryPage clickOnTheFirstLink () {
        setFirstInput.click();

    return new RepositoryPage();
}
}
