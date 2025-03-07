package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    private final SelenideElement
            currentItem = $(".current-item"),
            addProductButton = $("#add-to-cart-button-5"),
            shoppingCartButton = $("#topcartlink .cart-label");

    @Step("Проверить что открылась нужная карточка {0}")
    public ProductPage verifyProductNameInUpperCase(String productName) {
        currentItem.shouldHave(text(productName.toUpperCase()));

        return new ProductPage();
    }

    @Step("Нажать на кнопку Add to cart")
    public ProductPage clickAddToCartButton() {
        addProductButton.click();

        return this;
    }

    @Step("Нажать на кнопку Shopping cart")
    public CartPage clickToCartButton() {
        shoppingCartButton.click();

        return new CartPage();
    }
}
