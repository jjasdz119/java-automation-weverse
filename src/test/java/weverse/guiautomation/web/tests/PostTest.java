package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import weverse.guiautomation.web.common.PrepareManager;
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
        PrepareManager.prepareLogin(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testLogin() {
        loginPage.emailInputField().sendKeys();
        loginPage.passwordInputField().sendKeys();
        loginPage.loginButton().click();
    }

    // 사전조건: 생성한 계정 로그인, 임의 커뮤니티 가입, 프로필 엔드 접근(?)

    @Test
    @DisplayName("포스트 작성하기 테스트")
    void testCreatePost() {
        // 텍스트와 이미지 파일을 업로드하여 포스트를 생성한다.
        // 포스트가 등록되었는지 확인한다.
    }

    @Test
    @DisplayName("포스트 편집하기 테스트")
    void testEditPost() {
        // 저장된 텍스트를 다른 내용으로 업데이트한다.
        // 이미지 파일을 제거하고 미디어 파일을 업로드한다.
    }

    @Test
    @DisplayName("포스트 삭제하기 테스트")
    void testDeletePost() {
        // 생성한 포스트를 삭제한다.
        // 포스트 제거 후 등록한 포스트가 없을 때 나오는 메시지를 확인한다.
    }
}
