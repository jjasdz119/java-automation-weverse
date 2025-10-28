package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommunityPage {


    private WebDriver driver;

    private static final By HEADER_PROFILE = By.xpath("//a[@class='global-header-profile-_-profile']");

    public CommunityPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement headerProfile() {
        return driver.findElement(HEADER_PROFILE);
    }
}
