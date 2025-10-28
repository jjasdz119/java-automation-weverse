package weverse.guiautomation.web.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import weverse.guiautomation.web.common.GmailService;
import weverse.guiautomation.web.pages.LoginPage;
import weverse.guiautomation.web.pages.MainPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

//https://apidog.com/kr/blog/selenium-webdriver-kr/#java-%EC%98%88%EC%A0%9C-1 참고

public class ApiTest {

    private static final Log log = LogFactory.getLog(ApiTest.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private static final String BASE_URL = "https://global.apis.naver.com/weverse/wevweb/dm/v2.0/";

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://account.weverse.io/ko/login/credential?client_id=weverse&redirect_uri=https%3A%2F%2Fweverse.io%2F&redirect_method=COOKIE&v=4");
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testGetUserEndpoint() {
        // 로그인 프로세스
        loginPage.emailInputField().sendKeys("rxvpoker002@gmail.com");
        loginPage.passwordInputField().sendKeys("12e12e1!");
        loginPage.loginButton().click();
        // 인증코드 입력
        loginPage.authInputField().click();
        GmailService gmail = new GmailService();
        String auth = gmail.extractAuthCode();
        System.out.println("# 인증 코드: " + auth);
        loginPage.authInputField().sendKeys(auth);
        loginPage.verifyAuthButton().click();


        // API 호출
//        String sessionCookies = driver.manage().getCookieNamed("session_id").getValue();    // 토큰 추출

//        String appIdValue = "be4d79eb8fc7bd008ee82c8ec4ff6fd4";
//        String languageValue = "ko";
//        String osValue = "WEB";
//        String platformValue = "WEB";
//        String wpfValue = "pc";
//        String wmdValue = "2txXOiK9Z3lIncJoqnYq6PIF9PE%3D";
//        String wmsgpadValue = "1761581325797";
//
//        // 쿼리 파라미터 생성
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("appId", appIdValue);
//        queryParams.put("language", languageValue);
//        queryParams.put("os", osValue);
//        queryParams.put("platform", platformValue);
//        queryParams.put("wpf", wpfValue);
//        queryParams.put("wmd", wmdValue);
//        queryParams.put("wmsgpad", wmsgpadValue);
//
//        // RestAssured API 호출
//        String responseApi = RestAssured
//                .given().queryParams(queryParams)
//                .when().get(BASE_URL + "/user")
//                .then().statusCode(400).extract().asString();
//
//        JsonPath jsonPath = new JsonPath(responseApi);
//        String timeStamp = jsonPath.getString("timestamp"); // responseBody 키 추출
//        System.out.println("추출한 timestamp: " + timeStamp);
    }
}
