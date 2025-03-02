package demowebshop.test;

import demowebshop.pages.AddToCartPage;
import demowebshop.pages.ApparelAndShoesPage;
import demowebshop.pages.CartPage;
import demowebshop.pages.ProductPage;
import org.junit.jupiter.api.Test;

public class AddProductToCartTest extends TestBase {

    @Test
    void loginAndAddProductToCart() {
        ApparelAndShoesPage
                .clickApparelAndShoes();
        ProductPage
                .clickProductName("50's Rockabilly Polka Dot Top JR Plus Size");
        AddToCartPage
                .verifyProductNameInUpperCase("50's Rockabilly Polka Dot Top JR Plus Size")
                .clickAddToCartButton()
                .clickToCartButton();
        CartPage
                .refreshCart()
                .verifyCartQuantity("(1)")
                .removeItemFromCart()
                .updateCart()
                .verifyCartQuantity("(0)");
    }
}
