package pages;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

    public class WelcomePage {

        private final SelenideElement
                setButtonInput = $(".HeaderMenu-link-wrap");



        public static WelcomePage openPage() {
            open("https://github.com/");

            return new WelcomePage();
        }

        public LoginPage clickButtonSignIn() {
            setButtonInput.click();

            return new LoginPage();
        }

    }

