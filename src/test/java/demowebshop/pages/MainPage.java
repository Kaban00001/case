package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement
            registerButton = $(".ico-register"),
            logoutButton = $(".ico-logout");

    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegisterButton() {
        registerButton.click();

        return new RegistrationPage();
    }

    @Step("Нажать на кнопку продукта {productName}")
    public ProductCatalogPage clickOnProduct(String productName) {
        SelenideElement productLink = $(byText(productName));
        productLink.click();

        return new ProductCatalogPage();
    }

    @Step("Выйти из аккаунта")
    public MainPage clickLogoutButton() {
        logoutButton.click();

        return new MainPage();
    }
}