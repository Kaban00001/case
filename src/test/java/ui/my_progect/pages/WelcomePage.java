package ui.my_progect.pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

    public class WelcomePage {

        private final SelenideElement
                setButtonInput = $(".HeaderMenu-link-wrap");


        @Step("Открыть GitHub")
        public static WelcomePage openPage() {
            open("https://github.com/");

            return new WelcomePage();
        }

        @Step("Нажать на Sign In")
        public LoginPage clickButtonSignIn() {
            setButtonInput.click();

            return new LoginPage();
        }

    }

