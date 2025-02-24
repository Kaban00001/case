package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class LogoutPage {

    public LogoutPage clickSignOutAgain () {
    $(".inline-form").click();

        return this;
}
}
