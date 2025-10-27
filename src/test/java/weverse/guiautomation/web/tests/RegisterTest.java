package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import weverse.guiautomation.web.pages.GmailPage;
import weverse.guiautomation.web.pages.RegisterPage;

import java.time.Duration;

public class RegisterTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private GmailPage gmailPage;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://account.weverse.io/ko/signup/credential?client_id=wemember&v=4");
        registerPage = new RegisterPage(driver);
        gmailPage = new GmailPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("이메일 신규 가입 테스트")
    void testRegister() {
        registerPage.emailInputField().sendKeys("rxvpoker001@gmail.com");
        registerPage.sendCodeButton().click();
        registerPage.passwordInputField().sendKeys("12e12e");
        registerPage.passwordCheckInputField().sendKeys("12e12e");
        registerPage.nicknameInputField().clear();
        registerPage.nicknameInputField().sendKeys("ollie");

        // 브라우저 새 탭에서 Gmail 접근하기
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://gmail.com");
        gmailPage.emailInputField().sendKeys("rxvpoker001@gmail.com");
        gmailPage.nextButton().click();

        gmailPage.passwordInputField().sendKeys("Tmzkdlzx1!");

        driver.switchTo().window(originalWindow);

    }
}
