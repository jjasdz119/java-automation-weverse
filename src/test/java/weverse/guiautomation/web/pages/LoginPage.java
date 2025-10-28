package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private static final By EMAIL_LOGIN_BUTTON = By.xpath("//button[@type='button' and contains(., '이메일')]");
    private static final By EMAIL_INPUT_FIELD = By.xpath("//input[@type='text']");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("//input[@type='password']");
    private static final By AUTH_INPUT_FIELD = By.xpath("//input[@placeholder= '인증코드 6자리']");
    private static final By VERIFY_AUTH_BUTTON = By.xpath("//button[contains(@class, 'AuthLogin') and contains(., '인증코드 확인')]");
    private static final By MODAL_SUBMIT_BUTTON = By.xpath("//button[contains(@class, 'dialog-button') and contains(., '확인')]");
    private static final By LOGIN_BUTTON = By.xpath("//span[@class='button_text__8s9QP']");


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

    public WebElement modalSubmitButton() {
        return driver.findElement(MODAL_SUBMIT_BUTTON);
    }
}
