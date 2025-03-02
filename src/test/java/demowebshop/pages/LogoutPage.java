package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LogoutPage {

    private static final SelenideElement
            logoutButton = $(".ico-logout");

    @Step("Выйти из аккаунта")
    public static WelcomePage clickLogoutButton() {
        logoutButton.click();

        return new WelcomePage();
    }
}