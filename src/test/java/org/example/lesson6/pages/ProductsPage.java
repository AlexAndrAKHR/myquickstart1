package org.example.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage {


    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductsPage putProductInBasket(String productName) {

        webDriver.findElement(By.xpath("//div[contains(@class,'v-listing__desc')and contains(., '" + productName + "')]"));
        webDriver.findElement(By.xpath(".//button[contains(text(),'В корзину')]")).click();
        return this;
    }

    public BasketPage goToBasket() {

        new WebDriverWait(webDriver, 6)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'v-listing__hidden')]//button[contains(text(),'В корзине')]")))
                .click();
        return new BasketPage(webDriver);
    }
}
