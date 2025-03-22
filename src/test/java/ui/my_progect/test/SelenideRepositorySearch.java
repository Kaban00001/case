package ui.my_progect.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SelenideRepositorySearch {

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {

        Configuration.holdBrowserOpen = true;
        //Открыть гитхаб
        open("https://github.com/");
        //Нажать на Sign in
        $(".HeaderMenu-link-wrap").click();
        //Ввести почту для входа
        $("#login_field").setValue("awerinzh@yandex.ru");
        //Ввести пароль для входа
        $("#password").setValue("averin228337");
        $("[value='Sign in']").click();
        //Нажать на поле ввода
        $(".placeholder").click();
        //Ввести в поиск "selenide" и нажать Enter
        $("#query-builder-test").setValue("selenide").pressEnter();
        //Нажать на первую ссылку
        $(".search-title").click();
        //Сделать проверку надписи "selenide/selenide"
        $(".position-relative").shouldHave(text("selenide / selenide"));
        //Подвести мышку к первому аватару из блока contributors
        $(".BorderGrid").$(byText("Contributors")).ancestor(".BorderGrid-row").$$("ul li").first().hover();
        //Проверить надпись "Andrei Solntsev"
        $(".Truncate-text--expandable").shouldHave(text("Andrei Solntsev"));
        //Нажать в правом верхнем углу на крайнюю иконку
        $(".AppHeader-user").click();
        //Нажать Sign out
        $(byTagAndText("span", "Sign out")).click();
        //Повторно нажать Sign out получилось
        $(".inline-form").click();


    }



}
