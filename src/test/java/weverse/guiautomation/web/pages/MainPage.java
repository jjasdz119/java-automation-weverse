package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    private WebDriver driver;

    private static final By SEARCH_COMMUNITY = By.xpath("//span[text()='커뮤니티 찾기']");
    private static final By SEARCH_COMMUNITY_LIST = By.xpath("//ul[contains(@class, 'search-community-list-view')]");
    private static final By COMMUNITY_JOIN_BUTTON = By.xpath("//button[contains(@class, 'join')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement searchCommunity() {
        return driver.findElement(SEARCH_COMMUNITY);
    }

    public List<WebElement> searchCommunityList() {
        return driver.findElements(SEARCH_COMMUNITY_LIST);
    }

    public WebElement communityJoinButton() {
        return driver.findElement(COMMUNITY_JOIN_BUTTON);
    }
}
