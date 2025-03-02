package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ApparelAndShoesPage {

    private static final SelenideElement
            apparelAndShoesLink = $(byText("Apparel & Shoes"));

    @Step("Нажать на кнопку Apparel&Shoes")
    public static ProductPage clickApparelAndShoes() {
        apparelAndShoesLink.click();

        return new ProductPage();
    }
}
