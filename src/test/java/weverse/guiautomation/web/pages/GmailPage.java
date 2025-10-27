package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GmailPage {

    private WebDriver driver;

    private static final By EMAIL_INPUT_FIELD = By.xpath("//input[@type='email']");
    private static final By NEXT_BUTTON = By.xpath("//span[text()='다음']");
    private static final By PASSWORD_INPUT_FIELD = By.xpath("//input[@type='password']");


    public GmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement emailInputField() {
        return driver.findElement(EMAIL_INPUT_FIELD);
    }

    public WebElement nextButton() {
        return driver.findElement(NEXT_BUTTON);
    }

    public WebElement passwordInputField() {
        return driver.findElement(PASSWORD_INPUT_FIELD);
    }
}
