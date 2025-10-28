package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private static final By EMAIL_LOGIN_BUTTON = By.xpath("//button[@type='button' and contains(., '이메일')]");
    private static final By EMAIL_INPUT_FIELD = By.xpath("//input[@type='text']");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("//input[@type='password']");
    private static final By AUTH_INPUT_FIELD = By.xpath("//input[contains(@placeholder, '인증')]");
    private static final By LOGIN_BUTTON = By.xpath("//span[@class='button_text__8s9QP']");
    private static final By VERIFY_AUTH_BUTTON = By.xpath("//button[contains(@class, 'AuthLoginCredential')]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement emailLoginButton() {
        return driver.findElement(EMAIL_LOGIN_BUTTON);
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

    public WebElement authInputField() {
        return driver.findElement(AUTH_INPUT_FIELD);
    }

    public WebElement verifyAuthButton() {
        return driver.findElement(VERIFY_AUTH_BUTTON);
    }
}
