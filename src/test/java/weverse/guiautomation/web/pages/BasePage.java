package weverse.guiautomation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver =driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
}
