package ui.github.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    private final SelenideElement
            emailInput = $("[data-target='text-suggester.input']"),
            passwordInput = $("[placeholder='Password']"),
            usernameInput = $("#login"),
            continueButton = $(byTagAndText("span", "Continue"));

    @Step("Ввести Email {0}")
    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    @Step("Ввести password {0}")
    public RegistrationPage setPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    @Step("Ввести username {0}")
    public RegistrationPage setUserName(String username) {
        usernameInput.setValue(username);

        return this;
    }

    @Step("Нажать на кнопку Continue ")
    public RegistrationPage clickContinueButton() {
        continueButton.click();

        return this;
    }

    @Step("Проверить что появилась ошибка {0}")
    public RegistrationPage checkError(String errorMessage) {
        $(byText(errorMessage)).shouldBe(visible);

        return this;
    }
}
