package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.example.lesson6.listener.ActionEventListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;

public class BaseTest {
    protected static final String URL = "https://www.korablik.ru/";
    protected EventFiringWebDriver webDriver;



    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        webDriver = new EventFiringWebDriver (WebDriverManager.chromedriver().capabilities(chromeOptions).create());
        webDriver.register(new ActionEventListener());
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterEach
    void tearDown() {
        step("Отображение логов браузера", ()  -> {
            for (LogEntry logEntry : webDriver.manage().logs().get(LogType.BROWSER)) {
                Allure.addAttachment("Logs", logEntry.getMessage());

            }
        });

        webDriver.quit();
    }
}
