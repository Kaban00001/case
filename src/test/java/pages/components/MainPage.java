package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public MainPage clickTheInputField () {
        $(".placeholder").click();

        return this;
    }

    public SearchPage enterInTheField (String value){
        $("#query-builder-test").setValue(value).pressEnter();

        return new SearchPage();
    }
}
