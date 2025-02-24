package pages.components;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

    public class WelcomePage {

        public static WelcomePage openPage() {
            open("https://github.com/");

            return new WelcomePage();
        }

        public LoginPage clickButtonSignIn() {
            $(".HeaderMenu-link-wrap").click();

            return new LoginPage();
        }

    }

