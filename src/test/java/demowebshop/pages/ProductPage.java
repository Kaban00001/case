package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    @Step("Нажать на товар для перехода в его карточку")
    public static AddToCartPage clickProductName(String productName) {
        $(byText(productName)).click();

        return new AddToCartPage();
    }
}
