package org.example.lesson6.tests;

import org.example.lesson5.BaseTest;
import org.example.lesson6.pages.LoginPage;
import org.example.lesson6.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

public class AuthShopForChildrenTest extends BaseTest {

    String login = "hrustalevaalexa@yandex.ru";
    String password = "123360";
    private static final String URL = "https://www.korablik.ru/";


    @Test
    @DisplayName("Авторизация: существующий юзер - позитивный")
    void successfulAuthTest() {
       webDriver.get(URL);
       webDriver.manage().window().setSize(new Dimension(1300,720));

       new MainPage(webDriver).clickLoginButton()
       .login(login,password)
       .logout()
       .checkLoginButtonIsVisible();
   }

    @Test
    @DisplayName("Авторизация: некорректный пароль  - негативный")
    void incorrectPasswordFailedAuthTest() {
        webDriver.get(URL);
        webDriver.manage().window().setSize(new Dimension(1500,900));

        new MainPage(webDriver).clickLoginButton()
                .login(login,"jflglgkd");
        new LoginPage(webDriver)
                .checkError("Неверно указан адрес электронной почты или пароль");
    }

}
