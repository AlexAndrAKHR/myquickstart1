package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class LoginAndLockout
{
    public static void main( String[] args ) throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://www.korablik.ru/");
        webDriver.manage().window().setSize(new Dimension(1300,720));

        webDriver.findElement(By.linkText("Профиль")).click();
        By authFormLocator = By.xpath("//form[contains(@class,'auth-form')]");

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));
        WebElement authForm = webDriver.findElement(authFormLocator);;
        authForm.findElement(By.name("login")).sendKeys("hrustalevaalexa@yandex.ru");
        authForm.findElement(By.name("password")).sendKeys("123360");
        authForm.findElement(By.xpath("//div[@class='form-row']/button[contains(text(),'Войти')]")).click();

        webDriver.findElement(By.xpath("//a[contains(@class,'vue-header_links-profile')]/div[contains(text(),'Александра')]")).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'lk_user-info_exit')]")).click();

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Профиль")));


        webDriver.quit();







    }
}
