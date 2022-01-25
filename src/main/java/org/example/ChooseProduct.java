package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChooseProduct {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://www.korablik.ru/");

        webDriver.manage().window().setSize(new Dimension(1300, 720));

        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//li[contains(@class,'vue-header_menu-item')]//span[contains(text(),'Каталог')]")))
                .build()
                .perform();

        webDriver.findElement(By.xpath("//div[contains(text(),'Детские игрушки')]")).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'d-category__link')]/span[contains(text(),'Игрушки для малышей')]")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[contains(@class,'v-listing__desc')]"));
        products.get(1).findElement(By.xpath("//button[contains(text(),'В корзину')]")).click();


        new WebDriverWait(webDriver, 4)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'v-listing__hidden')]//button[contains(text(),'В корзине')]")))
                .click();

        System.out.println("Actual basket size = " + webDriver.findElements(By.xpath("//div[contains(@class,'basket_page-products_item-title')]")).size());
        System.out.println("Expected basket size = 1");
        webDriver.quit();

    }
}

