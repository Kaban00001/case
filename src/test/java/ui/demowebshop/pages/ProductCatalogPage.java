package ui.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductCatalogPage {

    private final SelenideElement
            productsPerPageDropdown = $("select#products-pagesize");

    private final ElementsCollection
            productItems = $$(".product-item");

    @Step("Выбрать количество продуктов на странице: {count}")
    public ProductCatalogPage selectProductsPerPage(int count) {
        productsPerPageDropdown.selectOptionContainingText(String.valueOf(count));

        return this;
    }

    @Step("Проверить, что на странице отображается {count} продуктов")
    public ProductCatalogPage verifyProductsCount(int count) {
        productItems.shouldHave(size(count));

        return this;
    }

    @Step("Нажать на товар для перехода в его карточку")
    public ProductPage clickProductName(String productName) {
        $(byText(productName)).click();

        return new ProductPage();
    }
}