package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    private WebDriver driver;

    private static final By LOGIN_BUTTON = By.xpath("//button[@type='button' and contains(., '로그인')]");
    private static final By RECOMMENDED_ARTIST = By.xpath("(//a[@target='_self'])[3]");
    private static final By SEARCH_COMMUNITY = By.xpath("//span[text()='커뮤니티 찾기']");
    private static final By SEARCH_COMMUNITY_LIST = By.xpath("//ul[contains(@class, 'search-community-list-view')]");
    private static final By COMMUNITY_JOIN_BUTTON = By.xpath("//button[contains(@class, 'join')]");
    private static final By HOME_BUTTON = By.xpath("//li[contains(@class, 'global-menu-list-') and contains(., '홈')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement homeButton() {
        return driver.findElement(HOME_BUTTON);
    }

    public WebElement loginButton() { return driver.findElement(LOGIN_BUTTON);
    }

    public WebElement recommendedArtist() {
        return driver.findElement(RECOMMENDED_ARTIST);
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
