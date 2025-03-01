package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

    public class MainPage {

        private final SelenideElement
                setClickInput = $(".placeholder"),
                setEnterInput = $("#query-builder-test");

        public MainPage clickTheInputField () {
            setClickInput.click();

        return this;
        }

        public SearchPage enterInTheField (String value){
            setEnterInput.setValue(value).pressEnter();

        return new SearchPage();
        }
}
