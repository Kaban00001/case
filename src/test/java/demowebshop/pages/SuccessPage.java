package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SuccessPage {

    private static final SelenideElement
            registrationSuccessMessage = $(".result");

    private final SelenideElement
            continueButton = $("[value='Continue']");

    @Step("Проверить что появилось приветсвие при регистрации {0}")
    public static SuccessPage verifyRegistrationSuccessMessage(String expectedMessage) {
        registrationSuccessMessage.shouldHave(text(expectedMessage));

        return new SuccessPage();
    }

    @Step("Нажать на кнопку Continue")
    public ApparelAndShoesPage clickContinueButton() {
        continueButton.click();

        return new ApparelAndShoesPage();
    }
}
