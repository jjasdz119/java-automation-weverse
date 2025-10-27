package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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

        loginPage.emailInputField().sendKeys("jjasdz119@gmail.com");
        loginPage.passwordInputField().sendKeys("12e12e1!");
        loginPage.loginButton().click();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void testLogin() {
        loginPage.emailInputField().sendKeys("jjasdz119@gmail.com");
        loginPage.passwordInputField().sendKeys("12e12e1!");
        loginPage.loginButton().click();
    }

//    @Test
//    @Order(2)
//    void testExtractWID() {
//        String endpoint = BASE_URL + "users/account/me";
//        String response = performGetRequest(endpoint);
//
//        JSONObject jsonResponse = new JSONObject(response);
//
//        System.out.println(jsonResponse.getString("wid"));
//    }


    @Test
    void testSearchCommunity() {
        loginPage.emailInputField().sendKeys("jjasdz119@gmail.com");
        loginPage.passwordInputField().sendKeys("12e12e1");
        loginPage.loginButton().click();
        mainPage.searchCommunity().click();
//        mainPage.searchCommunityList().get(1).click();
        mainPage.communityJoinButton().click();
    }
}
