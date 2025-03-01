package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

    public class LoginPage {

        private final SelenideElement
                setLoginInInput = $("#login_field"),
                setPasswordInput = $("#password"),
                setValueInput = $("[value='Sign in']");


        public LoginPage setLogin(String value) {
            setLoginInInput.setValue(value);

            return this;
        }

        public LoginPage setPassword(String value) {
            setPasswordInput.setValue(value);

            return this;
        }

        public MainPage clickSignInButton() {
            setValueInput.click();

            return new MainPage();
        }
    }

