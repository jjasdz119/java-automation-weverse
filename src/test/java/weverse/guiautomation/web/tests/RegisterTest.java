package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import weverse.guiautomation.web.common.DriverManager;
import weverse.guiautomation.web.common.GmailService;
import weverse.guiautomation.web.pages.RegisterAgreementPage;
import weverse.guiautomation.web.pages.RegisterCompletePage;
import weverse.guiautomation.web.pages.RegisterPage;

import java.time.Duration;

public class RegisterTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private RegisterAgreementPage registierAgreementPage;
    private RegisterCompletePage registerCompletePage;

    @BeforeEach
    public void setup() {

        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://account.weverse.io/ko/signup/credential?client_id=wemember&v=4");

        registerPage = new RegisterPage(driver);
        registierAgreementPage = new RegisterAgreementPage(driver);
        registerCompletePage = new RegisterCompletePage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("이메일 신규 가입 테스트")
    void testRegister() throws InterruptedException {

        // 회원정보 입력
        registerPage.emailInputField().sendKeys("rxvpoker001@gmail.com");   // Gmail API 연결해놓은 이메일
        registerPage.sendCodeButton().click();
        registerPage.passwordInputField().sendKeys("12e12e1!");
        registerPage.passwordCheckInputField().sendKeys("12e12e1!");
        registerPage.nicknameInputField().clear();
        registerPage.nicknameInputField().sendKeys("ollie");

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
}
