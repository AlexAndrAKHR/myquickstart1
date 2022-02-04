package org.example.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {


    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }


    public LoginPage clickLoginButton() {
        webDriver.findElement(By.linkText("Профиль")).click();
        return new LoginPage(webDriver);

    }

    public MainPage logout() {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath("//*[contains(text(), 'Добро пожаловать')]"))));
        webDriver.findElement(By.xpath("//a[contains(@class,'vue-header_links-profile')]/div[contains(text(),'Александра')]")).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'lk_user-info_exit')]")).click();
        return new MainPage(webDriver);

    }
    public MainPage checkLoginButtonIsVisible() {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Профиль")));
        return this;
    }


    public ProductsPage goToProductPage(String tab1, String tab2, String tab3) {
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//li[contains(@class,'vue-header_menu-item')]//span[contains(text(),'"+ tab1 +"')]")))
                .build()
                .perform();

        webDriver.findElement(By.xpath("//div[contains(text(),'"+ tab2 +"')]")).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'d-category__link')]/span[contains(text(),'"+ tab3 +"')]")).click();
        return new ProductsPage(webDriver);

    }



}
