package my_progect.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LogoutPage {

    private final SelenideElement setAgainInput = $(".inline-form");

    public LogoutPage clickSignOutAgain () {
        setAgainInput.click();

        return this;
}
}
