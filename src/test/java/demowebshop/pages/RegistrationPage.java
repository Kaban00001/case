package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    private static final SelenideElement
            maleGenderButton = $("#gender-male");

    private final SelenideElement
            firstNameInpun = $("#FirstName"),
            lastNameInput = $("#LastName"),
            emailInput = $("#Email"),
            passwordInput = $("#Password"),
            passwordConfirmInput = $("#ConfirmPassword"),
            registersButton = $("#register-button");

    @Step("Выбрать мужской пол {0}")
    public static RegistrationPage clickMaleGender() {
        maleGenderButton.click();

        return new RegistrationPage();
    }

    @Step("Ввести фамилию {0}")
    public RegistrationPage setFirstName(String firstName) {
        firstNameInpun.setValue(firstName);

        return this;
    }

    @Step("Ввести имя {0}")
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
    public SuccessPage clickRegisters() {
        registersButton.click();

        return new SuccessPage();
    }
}
