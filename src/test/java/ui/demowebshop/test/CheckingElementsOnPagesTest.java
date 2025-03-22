package ui.demowebshop.test;

import com.codeborne.selenide.Selenide;
import ui.demowebshop.helpers.TestBase;
import ui.demowebshop.pages.ProductCatalogPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CheckingElementsOnPagesTest extends TestBase {
    private static final String PRODUCT_CALALOGE_URL = "https://demowebshop.tricentis.com/apparel-shoes";

    @Test
    @Tag("ui/demowebshop")
    @DisplayName("Проверка отображения 4, 8 и 12 продуктов на странице")
    void verifyProductsPerPage() {
        Selenide.open(PRODUCT_CALALOGE_URL, ProductCatalogPage.class)
                .selectProductsPerPage(4)
                .verifyProductsCount(4)
                .selectProductsPerPage(8)
                .verifyProductsCount(8)
                .selectProductsPerPage(12)
                .verifyProductsCount(12);
    }
}

