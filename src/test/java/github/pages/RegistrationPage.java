package github.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    String registrationPageUrl = "https://github.com/signup";
    private final SelenideElement
            emailInput = $("[data-target='text-suggester.input']"),
            passwordInput = $("[placeholder='Password']"),
            usernameInput = $("#login"),
            continueButton = $(byTagAndText("span", "Continue"));

    public RegistrationPage openMainPage() {
        open(registrationPageUrl);

        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public RegistrationPage setPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    public RegistrationPage setUserName(String username) {
        usernameInput.setValue(username);

        return this;
    }

    public RegistrationPage clickContinueButton() {
        continueButton.click();

        return this;
    }

    public RegistrationPage checkError(String errorMessage) {
        $(byText(errorMessage)).shouldBe(visible);

        return this;
    }
}
