package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private static final By EMAIL_INPUT_FIELD = By.xpath("//input[@type='text']");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("//input[@type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//span[@class='button_text__8s9QP']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement emailInputField() {
        return driver.findElement(EMAIL_INPUT_FIELD);
    }

    public WebElement passwordInputField() {
        return driver.findElement(PASSWORD_INPUT_FIELD);
    }

    public WebElement loginButton() {
        return driver.findElement(LOGIN_BUTTON);
    }
}
