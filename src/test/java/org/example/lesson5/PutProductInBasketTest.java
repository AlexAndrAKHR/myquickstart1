package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PutProductInBasketTest extends BaseTest {


    @ParameterizedTest
    @ValueSource(strings = {"Игрушка на радиоуправлении Huggeland «Собака»","Интерактивная игрушка Huggeland «Игривый щенок»"})
    void putProductInBasketTest(String productName) {

        webDriver.get("https://www.korablik.ru/");

        webDriver.manage().window().setSize(new Dimension(2000, 1500));

        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//li[contains(@class,'vue-header_menu-item')]//span[contains(text(),'Каталог')]")))
                .build()
                .perform();

        webDriver.findElement(By.xpath("//div[contains(text(),'Детские игрушки')]")).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'d-category__link')]/span[contains(text(),'Игрушки для малышей')]")).click();

        webDriver.findElement(By.xpath("//div[contains(@class,'v-listing__desc')and contains(., '" + productName + "')]"));
        webDriver.findElement(By.xpath(".//button[contains(text(),'В корзину')]")).click();

        new WebDriverWait(webDriver, 6)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'v-listing__hidden')]//button[contains(text(),'В корзине')]")))
                .click();

        List<WebElement>productsInBasket = webDriver.findElements(By.xpath("//div[contains(@class,'basket_page-products_item-title')]"));


        assertThat(productsInBasket).hasSize(1);
        assertThat(productsInBasket.get(0).findElement(By.xpath(".//a[contains(@href,'/product/')]")).getText())
                .as("Название продукта в корзине должно быть " + productName)
                .isEqualTo(productName);





    }
}
