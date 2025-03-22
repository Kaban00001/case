package ui.demowebshop.pages;

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
    public CartPage refreshCart() {
        refresh();

        return new CartPage();
    }

    @Step("Проверить колличество в корзине {0}")
    public CartPage verifyCartQuantity(int expectedQuantity) {
        String formattedQuantity = "(" + expectedQuantity + ")";
        cartQuantity.shouldHave(text(formattedQuantity));

        return this;
    }

    @Step("Выбрать товар в корзине")
    public CartPage selectItemInCart() {
        removeFromCartCheckbox.click();

        return this;
    }

    @Step("Нажать на кнопку обновления корзины")
    public CartPage updateCart() {
        updateCartButton.click();

        return this;
    }
}