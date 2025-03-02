package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WelcomePage {
    private static final String
            BASE_URL = "https://demowebshop.tricentis.com/";
    private final SelenideElement
            registerButton = $(".ico-register");

    @Step("Открыть demowebshop {0}")
    public static WelcomePage openPage() {
        open(BASE_URL);

        return new WelcomePage();
    }

    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        return new RegistrationPage();

    }
}