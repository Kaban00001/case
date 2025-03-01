package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.RepositoryPage;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private final SelenideElement setFirstInput = $(".search-title");


    public RepositoryPage clickOnTheFirstLink () {
        setFirstInput.click();

    return new RepositoryPage();
}
}
