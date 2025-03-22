package ui.my_progect.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LogoutPage {

    private final SelenideElement setAgainInput = $(".inline-form");

    @Step("Повторно нажать на Sign out")
    public LogoutPage clickSignOutAgain () {
        setAgainInput.click();

        return this;
}
}
