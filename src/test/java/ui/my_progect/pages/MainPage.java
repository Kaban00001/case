package ui.my_progect.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

    public class MainPage {

        private final SelenideElement
                setClickInput = $(".placeholder"),
                setEnterInput = $("#query-builder-test");

        @Step("Нажать на поле ввода")
        public MainPage clickTheInputField () {
            setClickInput.click();

        return this;
        }

        @Step("Ввести в поиск selenide и нажать Enter")
        public SearchPage enterInTheField (String value){
            setEnterInput.setValue(value).pressEnter();

        return new SearchPage();
        }
}
