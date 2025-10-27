package weverse.guiautomation.web.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import weverse.guiautomation.web.pages.LoginPage;

import java.time.Duration;

//https://apidog.com/kr/blog/selenium-webdriver-kr/#java-%EC%98%88%EC%A0%9C-1 참고

public class ApiTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String BASE_URL = "https://accountapi.weverse.io/web/api/v4";

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://account.weverse.io/ko/login/credential?client_id=weverse&redirect_uri=https%3A%2F%2Fweverse.io%2F&redirect_method=COOKIE&v=4");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testGetUserEndpoint() {
        
        // 잘못된 사용자 정보 입력
        loginPage.emailInputField().sendKeys("jjasdz119@gmail.com");
        loginPage.passwordInputField().sendKeys("123456");
        loginPage.loginButton().click();

        // API 호출
//        String sessionCookies = driver.manage().getCookieNamed("session_id").getValue();    // 토큰 추출
        String responseApi = RestAssured.given()    // RestAssured API 호출
//                .cookie("session_id")
                .when()
                .get(BASE_URL + "/auth/token/by-credentials")
                .then()
                .statusCode(400)
                .extract()
                .asString();

        JsonPath jsonPath = new JsonPath(responseApi);
        String timeStamp = jsonPath.getString("timestamp"); // responseBody 키 추출

        System.out.println("추출한 timestamp: " + timeStamp);

    }

}
