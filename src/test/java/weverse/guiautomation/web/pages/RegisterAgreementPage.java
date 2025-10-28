package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterAgreementPage {

    private WebDriver driver;

    private static final By AGREE_ALL_CHECKBOX = By.xpath("//span[contains(@class, 'checkbox')][1]");
    private static final By REGISTER_COMPLETE_BUTTON = By.xpath("//button[contains(@class, '9suU6 button')]");
    private static final By AGREE_SUBMIT_BUTTON = By.xpath("//button[contains(@class, 'mlCm7 button')]");


    public RegisterAgreementPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement agreeAllCheckbox() {
        return driver.findElement(AGREE_ALL_CHECKBOX);
    }

    public WebElement registerCompleteButton() {
        return driver.findElement(REGISTER_COMPLETE_BUTTON);
    }

    public WebElement agreeSubmitButton() {
        return driver.findElement(AGREE_SUBMIT_BUTTON);
    }
}
