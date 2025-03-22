package ui.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    private static final String REGISTRATION_SUCCESS_MESSAGE = " Your registration completed ";

    private final SelenideElement
            maleGenderButton = $("#gender-male"),
            firstNameInpun = $("#FirstName"),
            lastNameInput = $("#LastName"),
            emailInput = $("#Email"),
            passwordInput = $("#Password"),
            passwordConfirmInput = $("#ConfirmPassword"),
            registersButton = $("#register-button"),
            registrationSuccessMessage = $(".result"),
            continueButton = $("[value='Continue']");

    @Step("Выбрать мужской пол {0}")
    public RegistrationPage clickMaleGender() {
        maleGenderButton.click();

        return new RegistrationPage();
    }

    @Step("Ввести имя {0}")
    public RegistrationPage setFirstName(String firstName) {
        firstNameInpun.setValue(firstName);

        return this;
    }

    @Step("Ввести фамилию {0}")
    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    @Step("Ввести Email {0}")
    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    @Step("Ввести Password {0}")
    public RegistrationPage setPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    @Step("Повторно ввести Password {0}")
    public RegistrationPage setConfirmPassword(String password) {
        passwordConfirmInput.setValue(password);

        return this;
    }

    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegisterButton() {
        registersButton.click();

        return this;
    }

    @Step("Проверить что появилось приветсвие при регистрации {0}")
    public RegistrationPage verifyRegistrationSuccessMessage() {
        registrationSuccessMessage.shouldHave(text(REGISTRATION_SUCCESS_MESSAGE));

        return this;
    }

    @Step("Нажать на кнопку Continue")
    public MainPage clickContinueButton() {
        continueButton.click();

        return new MainPage();
    }
}
