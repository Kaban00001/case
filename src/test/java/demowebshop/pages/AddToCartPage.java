package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AddToCartPage {

    private static final SelenideElement
            currentItem = $(".current-item");

    private final SelenideElement
            addProductButton = $("#add-to-cart-button-5"),
            shoppingCartButton = $("#topcartlink .cart-label");

    @Step("Проверить что открылась нужная карточка {0}")
    public static AddToCartPage verifyProductNameInUpperCase(String productName) {
        currentItem.shouldHave(text(productName.toUpperCase()));

        return new AddToCartPage();
    }

    @Step("Нажать на кнопку Add to cart")
    public AddToCartPage clickAddToCartButton() {
        addProductButton.click();
        return this;
    }

    @Step("Нажать на кнопку Shopping cart")
    public CartPage clickToCartButton() {
        shoppingCartButton.click();

        return new CartPage();
    }
}
