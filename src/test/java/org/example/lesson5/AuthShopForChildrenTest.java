package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AuthShopForChildrenTest extends BaseTest{

    @Test
    @DisplayName("Авторизация: существующий юзер - позитивный")

   void successfulAuthTest() {

       webDriver.get("https://www.korablik.ru/");
       webDriver.manage().window().setSize(new Dimension(1300,720));

       webDriver.findElement(By.linkText("Профиль")).click();
       By authFormLocator = By.xpath("//form[contains(@class,'auth-form')]");

       new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));
       WebElement authForm = webDriver.findElement(authFormLocator);;
       authForm.findElement(By.name("login")).sendKeys("hrustalevaalexa@yandex.ru");
       authForm.findElement(By.name("password")).sendKeys("123360");
       authForm.findElement(By.xpath("//div[@class='form-row']/button[contains(text(),'Войти')]")).click();

       new WebDriverWait(webDriver, 10).until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath("//*[contains(text(), 'Добро пожаловать')]"))));

       webDriver.findElement(By.xpath("//a[contains(@class,'vue-header_links-profile')]/div[contains(text(),'Александра')]")).click();
       webDriver.findElement(By.xpath("//a[contains(@class,'lk_user-info_exit')]")).click();

       new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Профиль")));


   }

    @Test
    @DisplayName("Авторизация: некорректный пароль  - негативный")
    void incorrectPasswordFailedAuthTest() {

        webDriver.get("https://www.korablik.ru/");
        webDriver.manage().window().setSize(new Dimension(1300,720));

        webDriver.findElement(By.linkText("Профиль")).click();
        By authFormLocator = By.xpath("//form[contains(@class,'auth-form')]");

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));
        WebElement authForm = webDriver.findElement(authFormLocator);;
        authForm.findElement(By.name("login")).sendKeys("hrustalevaalexa@yandex.ru");
        authForm.findElement(By.name("password")).sendKeys("invalid password");
        authForm.findElement(By.xpath("//div[@class='form-row']/button[contains(text(),'Войти')]")).click();

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='\n" +
                "      Неверно указан адрес электронной почты или пароль\n" +
                "    ']")));

    }

}
