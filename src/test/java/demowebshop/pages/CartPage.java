package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class CartPage {

    private final SelenideElement
            cartQuantity = $("span.cart-qty"),
            removeFromCartCheckbox = $("[name='removefromcart']"),
            updateCartButton = $("[value='Update shopping cart']");

    @Step("Обновить страницу")
    public static CartPage refreshCart() {
        refresh();

        return new CartPage();
    }

    @Step("Проверить колличество в корзине {0}")
    public CartPage verifyCartQuantity(String expectedQuantity) {
        cartQuantity.shouldHave(text(expectedQuantity));

        return this;
    }

    @Step("Выбрать товар в корзине")
    public CartPage removeItemFromCart() {
        removeFromCartCheckbox.click();

        return this;
    }

    @Step("Удалить товар в корзине")
    public CartPage updateCart() {
        updateCartButton.click();

        return this;
    }
}