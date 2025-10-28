package weverse.guiautomation.web.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import weverse.guiautomation.web.common.DriverManager;
import weverse.guiautomation.web.common.GmailService;
import weverse.guiautomation.web.pages.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private RegisterAgreementPage registierAgreementPage;
    private RegisterCompletePage registerCompletePage;

    @BeforeEach
    public void setup() {

        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        MainPage mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        registierAgreementPage = new RegisterAgreementPage(driver);
        registerCompletePage = new RegisterCompletePage(driver);
        GmailService service = new GmailService();
        LoginPage loginPage = new LoginPage(driver);

//        driver.get("https://account.weverse.io/ko/login/");
        driver.navigate().to("https://weverse.io/");
        mainPage.loginButton().click();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    @DisplayName("이메일 신규 가입 테스트")
    void testRegister() throws InterruptedException {

        String email = "rxvpoker001@gmail.com";
        String password = "12e12e1!";
        String passwordCheck = "12e12e1!";
        String nickname = "ollie";

        // 회원정보 입력
        registerPage.emailInputField().sendKeys(email);   // Gmail API 연결해놓은 이메일
        registerPage.sendCodeButton().click();
        registerPage.passwordInputField().sendKeys(password);
        registerPage.passwordCheckInputField().sendKeys(passwordCheck);
        registerPage.nicknameInputField().clear();
        registerPage.nicknameInputField().sendKeys(nickname);

        // Gmail 인증 번호 가져오기
        GmailService service = new GmailService();
        service.getEmailSubject();
        String authCode = service.extractAuthCode();
        System.out.println("# 인증 코드: " + authCode);
        registerPage.authCodeInputField().sendKeys(authCode);
        registerPage.authCodeCheck().click();

        registerPage.nextButton().click();

        // 이용약관 동의
        registierAgreementPage.agreeAllCheckbox().click();
        registierAgreementPage.registerCompleteButton().click();
        registierAgreementPage.agreeSubmitButton();

        // 가입완료 화면 > 메인으로 랜딩
        registerCompletePage.startButton().click();
    }

    @Test
    @Order(2)
    @DisplayName("로그인 및 WID 식별")
    void testLoginAndExtractWID() {
        LoginPage loginPage = new LoginPage(driver);
        GmailService service = new GmailService();

        // 로그인
        loginPage.emailLoginButton().click();
        loginPage.emailInputField().sendKeys("rxvpoker001@gmail.com");
        loginPage.passwordInputField().sendKeys("12e12e1!");
        loginPage.loginButton().click();

        // 인증 코드 입력
        service.getEmailSubject();
        String authCode = service.extractAuthCode();
        System.out.println("# 인증 코드: " + authCode);
        loginPage.authInputField().sendKeys(authCode);
        loginPage.verifyAuthButton().click();
        loginPage.modalSubmitButton().click();

        // #------------------------------------------임시

        String sessionCookies = driver.manage().getCookieNamed("session_id").getValue();    // 토큰 추출

        String appIdValue = "be4d79eb8fc7bd008ee82c8ec4ff6fd4";
        String languageValue = "ko";
        String osValue = "WEB";
        String platformValue = "WEB";
        String wpfValue = "pc";
        String wmdValue = "2txXOiK9Z3lIncJoqnYq6PIF9PE%3D";
        String wmsgpadValue = "1761581325797";

        // 쿼리 파라미터 생성
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("appId", appIdValue);
        queryParams.put("language", languageValue);
        queryParams.put("os", osValue);
        queryParams.put("platform", platformValue);
        queryParams.put("wpf", wpfValue);
        queryParams.put("wmd", wmdValue);
        queryParams.put("wmsgpad", wmsgpadValue);

        // RestAssured API 호출
        String BASE_URL = "123";

        String responseApi = RestAssured
                .given().queryParams(queryParams)
                .when().get(BASE_URL + "/user")
                .then().statusCode(400).extract().asString();

        JsonPath jsonPath = new JsonPath(responseApi);
        String timeStamp = jsonPath.getString("timestamp"); // responseBody 키 추출
        System.out.println("추출한 timestamp: " + timeStamp);

    }
}
