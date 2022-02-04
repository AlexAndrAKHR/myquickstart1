package org.example.lesson6.tests;

import org.example.lesson5.BaseTest;
import org.example.lesson6.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PutProductInBasketTest extends BaseTest {


    @ParameterizedTest
    @ValueSource(strings = {"Интерактивная игрушка Huggeland «Игривый щенок»"})
    void putProductInBasketTest(String productName) {

       webDriver.get(URL);

       new MainPage(webDriver)
               .goToProductPage("Каталог", "Детские игрушки", "Игрушки для малышей")
               .putProductInBasket(productName)
               .goToBasket()
               .checkProductsInCart(productName);
   }
}
