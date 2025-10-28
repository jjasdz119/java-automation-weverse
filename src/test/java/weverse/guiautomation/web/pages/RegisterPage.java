package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

    private WebDriver driver;

    private static final By EMAIL_INPUT_FIELD = By.xpath("//input[@type='email']");
    private static final By SEND_CODE_BUTTON = By.xpath("//button[contains(@class, 'Auth')]");
    private static final By AUTH_CODE_INPUT_FIELD = By.xpath("//input[contains(@placeholder, '인증')]");
    private static final By AUTH_CODE_CHECK = By.xpath("//button[contains(@class, 'AuthForm') and contains(., '인증코드')]");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("(//input[@type='password'])[1]");
    private static final By PASSWORD_CHECK_INPUT_FIELD = By.xpath("(//input[@type='password'])[2]");
    private static final By NICKNAME_INPUT_FIELD = By.xpath("(//input[@type='text'])[2]");
    private static final By NEXT_BUTTON = By.xpath("//button[contains(@class, '9suU6')]");



    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement emailInputField() {
        return driver.findElement(EMAIL_INPUT_FIELD);
    }

    public WebElement authCodeInputField() {
        return driver.findElement(AUTH_CODE_INPUT_FIELD);
    }

    public WebElement authCodeCheck() {
        return driver.findElement(AUTH_CODE_CHECK);
    }

    public WebElement passwordInputField() {
        return driver.findElement(PASSWORD_INPUT_FIELD);
    }

    public WebElement passwordCheckInputField() {
        return driver.findElement(PASSWORD_CHECK_INPUT_FIELD);
    }

    public WebElement nicknameInputField() {
        return driver.findElement(NICKNAME_INPUT_FIELD);
    }

    public WebElement sendCodeButton() {
        return driver.findElement(SEND_CODE_BUTTON);
    }

    public WebElement nextButton() {
        return driver.findElement(NEXT_BUTTON);
    }
}
