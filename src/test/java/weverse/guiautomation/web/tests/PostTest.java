package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import weverse.guiautomation.web.pages.LoginPage;
import weverse.guiautomation.web.pages.MainPage;

import java.time.Duration;

public class PostTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://account.weverse.io/ko/login/credential?client_id=wemember&v=4");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

//    @Test
//    void testLogin() {
//        loginPage.emailInputField().sendKeys("jjasdz119@gmail.com");
//        loginPage.passwordInputField().sendKeys("Tmzkdlzx1!");
//        loginPage.loginButton().click();
//    }


    @Test
    void testSearchCommunity() {
        loginPage.emailInputField().sendKeys("jjasdz119@gmail.com");
        loginPage.passwordInputField().sendKeys("Tmzkdlzx1!");
        loginPage.loginButton().click();


        mainPage.searchCommunity().click();
//        mainPage.searchCommunityList().get(1).click();
        mainPage.communityJoinButton().click();
    }
}
