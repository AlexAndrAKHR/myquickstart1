package org.example.lesson6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage {


    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Step("Добавить товар {productName} в корзину")
    public ProductsPage putProductInBasket(String productName) {

        webDriver.findElement(By.xpath("//div[contains(@class,'v-listing__desc')and contains(., '" + productName + "')]"));
        webDriver.findElement(By.xpath(".//button[contains(text(),'В корзину')]")).click();
        return this;
    }
   @Step("Перейти в корзину")
    public BasketPage goToBasket() {

        new WebDriverWait(webDriver, 6)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'v-listing__hidden')]//button[contains(text(),'В корзине')]")))
                .click();
        return new BasketPage(webDriver);
    }
}
