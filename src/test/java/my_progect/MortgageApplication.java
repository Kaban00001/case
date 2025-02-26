package my_progect;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class MortgageApplication {

    @Test
    void shouldOpenAlfaBankWebsite () {

        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadTimeout = 1000000000;


        open("https://alfabank.ru/");
        $("[data-test-id='TabsHeader-mrt']").scrollTo().click();
        $("[data-test-id='go-to-form-button']").scrollTo().click();
        $("[data-test-id='form-phone-input']").scrollTo().setValue("9106395833");
        $("[data-test-id='checkboxSOPD-checkbox-area']").scrollTo().click();
        $("[data-test-id='mortgage-form-submit-button']").click();
        $("[data-test-id='input__lastname']").setValue("Аверина");
        $("[data-test-id='input__firstname']").setValue("Мария");
        $("[data-test-id='input__contacts-email-field']").setValue("awerinzh@yandex.ru");
        $("[data-test-id='button__signup']").click();
        $(byTagAndText("label", "Введите текст с картинки"));











    }


}

