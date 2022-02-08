package org.example.lesson6.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.lesson5.BaseTest;
import org.example.lesson6.pages.LoginPage;
import org.example.lesson6.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


@DisplayName("Авторизация")
public class AuthShopForChildrenTest extends BaseTest {

    String login = "hrustalevaalexa@yandex.ru";
    String password = "123360";
    private static final String URL = "https://www.korablik.ru/";


    @Test
    @DisplayName("Авторизация: существующий юзер - позитивный")
    @Severity(SeverityLevel.BLOCKER)
    void successfulAuthTest() {
       webDriver.get(URL);
       webDriver.manage().window().setSize(new Dimension(1300,720));

       new MainPage(webDriver).clickLoginButton()
       .login(login,password)
       .logout()
       .checkLoginButtonIsVisible();
   }

    @Test
    @DisplayName("Авторизация: некорректный пароль - негативный")
    @Severity(SeverityLevel.NORMAL)
    void incorrectPasswordFailedAuthTest() throws InterruptedException {
        webDriver.get(URL);
        webDriver.manage().window().setSize(new Dimension(1500,900));

        new MainPage(webDriver).clickLoginButton()
                .login(login,"jflglgkd");

        TimeUnit.SECONDS.sleep(3);

        new LoginPage(webDriver)
                .checkError("Неверно указан адрес электронной почты или пароль");
    }


    @Test
    @DisplayName("Авторизация: не заполнено обязательное поле Пароль - негативный")
    @Severity(SeverityLevel.BLOCKER)
    void noPasswordAuthTest() {
        webDriver.get(URL);
        webDriver.manage().window().setSize(new Dimension(1500,900));

        new MainPage(webDriver).clickLoginButton()
                .login(login,"");

        new WebDriverWait(webDriver, 6).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Не указаны обязательные данные')]")));
    }


    @Test
    @DisplayName("Авторизация: не заполнено обязательное поле Логин - негативный")
    @Severity(SeverityLevel.BLOCKER)
    void noLoginAuthTest() throws InterruptedException {
        webDriver.get(URL);
        webDriver.manage().window().setSize(new Dimension(1500,900));

        new MainPage(webDriver).clickLoginButton()
                .login("",password);

        new WebDriverWait(webDriver, 6).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Не указаны обязательные данные')]")));

    }

}


