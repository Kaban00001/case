package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

    public class LoginPage {

        private final SelenideElement setLoginInInput = $("#login_field"),
                setPasswordInput = $("#password");
        public MainPage clickSignInButton;

        public LoginPage setLogin(String value) {
            setLoginInInput.setValue(value);

            return this;
        }

        public LoginPage setPassword(String value) {
            setPasswordInput.setValue(value);

            return this;
        }

        public MainPage clickSignInButton() {
            $("[value='Sign in']").click();

            return new MainPage();
        }
    }

