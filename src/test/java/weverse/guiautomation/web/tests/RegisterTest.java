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
    void testRegister() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 회원정보 입력
        registerPage.emailInputField().sendKeys("rxvpoker001@gmail.com");
        registerPage.sendCodeButton().click();


        // 인증번호 추출해서 입력



        registerPage.passwordInputField().sendKeys("12e12e11");
        registerPage.passwordCheckInputField().sendKeys("12e12e1!");
        registerPage.nicknameInputField().clear();
        registerPage.nicknameInputField().sendKeys("ollie");


    }
}
