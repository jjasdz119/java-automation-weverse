package weverse.guiautomation.web.tests;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import weverse.guiautomation.web.common.GmailService;
import weverse.guiautomation.web.common.Utils;
import weverse.guiautomation.web.pages.RegisterPage;

import java.time.Duration;

public class RegisterTest {

    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://account.weverse.io/ko/signup/credential?client_id=wemember&v=4");
        registerPage = new RegisterPage(driver);
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
        registerPage.passwordInputField().sendKeys("12e12e11");
        registerPage.passwordCheckInputField().sendKeys("12e12e1!");
        registerPage.nicknameInputField().clear();
        registerPage.nicknameInputField().sendKeys("ollie");

        // Gmail 인증 번호 가져오기
        GmailService service = new GmailService();
        service.getEmailSubject();
        String authCode = service.extractAuthCode();
        System.out.println("# 인증 코드: " + authCode);
        registerPage.authCodeInputField().sendKeys(authCode);


//        registerPage.nextButton().click(); 나중에 주석 풀기
    }
}
