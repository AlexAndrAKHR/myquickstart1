package org.example.lesson6.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.lesson5.BaseTest;
import org.example.lesson6.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Добавление товара в корзину")
public class PutProductInBasketTest extends BaseTest {

    @DisplayName("Добавить продукт в корзину")
    @ParameterizedTest (name = "Добавить {0} в корзину")
    @ValueSource(strings = {"Интерактивная игрушка Huggeland «Игривый щенок»"})
    @Severity(SeverityLevel.BLOCKER)


    void putProductInBasketTest(String productName) {

        Allure.parameter("Название товара", productName);
        webDriver.get(URL);

       new MainPage(webDriver)
               .goToProductPage("Каталог", "Детские игрушки", "Игрушки для малышей")
               .putProductInBasket(productName)
               .goToBasket()
               .checkProductsInCart(productName);
   }
}
