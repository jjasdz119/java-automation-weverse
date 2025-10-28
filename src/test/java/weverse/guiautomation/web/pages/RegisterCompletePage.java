package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterCompletePage {

    private WebDriver driver;

    private static final By START_BUTTON = By.xpath("//button[contains(@class, '9suU6 button')]");


    public RegisterCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement startButton() {
        return driver.findElement(START_BUTTON);
    }

}
