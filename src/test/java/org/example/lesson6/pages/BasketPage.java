package org.example.lesson6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketPage extends BasePage{


    public BasketPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить,что продукт в корзине соответствует {productNames}")
    public void checkProductsInCart(String... productNames) {
        List<String> actualProducts = webDriver.findElements(By.xpath("//div[contains(@class,'basket_page-products_item-title')]"))
                .stream()
                .map(el -> el.findElement(By.xpath(".//a[contains(@href,'/product/')]")).getText())
                .collect(Collectors.toList());


        assertThat(actualProducts)
                .as("Название продукта в корзине должно соответствовать ожидаемому ")
                .containsExactlyInAnyOrder(productNames);
    }
}
