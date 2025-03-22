package ui.demowebshop.test;

import ui.demowebshop.helpers.TestBase;
import ui.demowebshop.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AddProductToCartTest extends TestBase {

    private static final String POLKA_DOT_PRODUCT = "50's Rockabilly Polka Dot Top JR Plus Size";
    private final MainPage mainPage = new MainPage();

    @Test
    @Tag("ui/demowebshop")
    @DisplayName("Добавление продукта в корзину")
    void addProductToCartTest() {
        mainPage
                .clickOnProduct("Apparel & Shoes")
                .clickProductName(POLKA_DOT_PRODUCT)
                .verifyProductNameInUpperCase(POLKA_DOT_PRODUCT)
                .clickAddToCartButton()
                .clickToCartButton()
                .refreshCart()
                .verifyCartQuantity(1)
                .selectItemInCart()
                .updateCart()
                .verifyCartQuantity(0);
    }
}
