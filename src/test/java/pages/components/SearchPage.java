package pages.components;

import pages.RepositoryPage;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {public RepositoryPage clickOnTheFirstLink () {
    $(".search-title").click();

    return new RepositoryPage();
}
}
