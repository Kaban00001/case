package ui.my_progect.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement
            setLoginInInput = $("#login_field"),
            setPasswordInput = $("#password"),
            setValueInput = $("[value='Sign in']");

    @Step("Ввести Login {0}")
    public LoginPage setLogin(String value) {
        setLoginInInput.setValue(value);

        return this;
    }

    @Step("Ввести Password {0}")
    public LoginPage setPassword(String value) {
        setPasswordInput.setValue(value);

        return this;
    }

    @Step("Нажать на SignIn")
    public MainPage clickSignInButton() {
        setValueInput.click();

        return new MainPage();
    }
}

