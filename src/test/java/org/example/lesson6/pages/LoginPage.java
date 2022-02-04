package org.example.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage{

    @FindBy(name = "login")
    private WebElement userNameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[contains(@class,'auth-form')]//div[contains(@class,'form-error')]")
    private WebElement errorSpan;

    @FindBy(xpath = "//div[@class='form-row']/button[contains(text(),'Войти')]")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public MainPage login(String login, String password)  {
        By authFormLocator = By.xpath("//form[contains(@class,'auth-form')]");
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));
        WebElement authForm = webDriver.findElement(authFormLocator);;
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new MainPage(webDriver);
    }

    public void checkError(String errorText) {
        assertThat (errorSpan.getText())
                .isEqualTo(errorText);
    }


}
