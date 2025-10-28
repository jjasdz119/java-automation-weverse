package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import weverse.guiautomation.web.common.DriverManager;
import weverse.guiautomation.web.common.GmailService;
import weverse.guiautomation.web.pages.CommunityPage;
import weverse.guiautomation.web.pages.LoginPage;
import weverse.guiautomation.web.pages.MainPage;
import weverse.guiautomation.web.pages.ProfilePage;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void setup() {

        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CommunityPage communityPage = new CommunityPage(driver);
        GmailService service = new GmailService();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://weverse.io/");
        mainPage.loginButton().click();

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

        // 임의의 커뮤니티 가입
        mainPage.searchCommunity().click();
        mainPage.searchCommunityList().get(0).click();
        mainPage.communityJoinButton().click();
        mainPage.homeButton().click();
        mainPage.recommendedArtist().click();

        communityPage.headerProfile().click();
    }

    @AfterAll
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    @DisplayName("포스트 작성하기 테스트")
    void testCreatePost() throws InterruptedException {
        ProfilePage profilePage = new ProfilePage(driver);

        // 텍스트와 이미지 파일을 업로드하여 포스트를 생성한다.
        // 텍스트가 등록되었는지 확인한다.

        String content = "Hello";

        profilePage.createPost().click();
        profilePage.postWriteModal().sendKeys(content);

        // 이미지 첨부
        String projectPath = System.getProperty("user.dir");
        String imagePath = projectPath + "/src/test/resources/sample_image.png";
        profilePage.uploadImage().sendKeys(imagePath);
        Thread.sleep(10000);    // 물리적 대기말고 다른 조건으로 바꿔야 함

        profilePage.uploadSubmitButton().click();
        profilePage.postWriteModalSubmitButton().click();

        WebElement list = profilePage.postList();
        List<WebElement> image = list.findElements(By.tagName("img"));

        Assertions.assertTrue(list.getText().contains(content), "입력 값이 존재하지 않음");
        Assertions.assertFalse(image.isEmpty(), "이미지가 포함되어 있지 않음");

    }

    @Test
    @Order(2)
    @DisplayName("포스트 편집하기 테스트")
    void testEditPost() throws InterruptedException {
        ProfilePage profilePage = new ProfilePage(driver);

        // 저장된 텍스트를 다른 내용으로 업데이트한다.
        // 이미지 파일을 제거하고 미디어 파일을 업로드한다.
        Thread.sleep(3000);
        String updateContent = "Update post";

        profilePage.overFlow().click();
        profilePage.overFlowEdit().click();
        profilePage.postWriteModal().clear();
        profilePage.postWriteModal().sendKeys(updateContent);

        // 미디어 파일 업로드
        String projectPath = System.getProperty("user.dir");
        String videoPath = projectPath + "/src/test/resources/sample_video.mp4";
        profilePage.uploadVideo().sendKeys(videoPath);

        Thread.sleep(15000);
        profilePage.uploadSubmitButton().click();
        Thread.sleep(3000);
        profilePage.postWriteModalSubmitButton().click();
        Thread.sleep(3000);
        WebElement list = profilePage.postList();
        List<WebElement> video = list.findElements(By.tagName("svg"));

        Assertions.assertTrue(list.getText().contains(updateContent), "입력 값이 존재하지 않음");
        System.out.println("텍스트: " + updateContent);
        Assertions.assertFalse(video.isEmpty(), "동영상이 포함되어 있지 않음");

    }

    @Test
    @Order(3)
    @DisplayName("포스트 삭제하기 테스트")
    void testDeletePost() {

        // 생성한 포스트를 삭제한다.
        // 포스트 제거 후 등록한 포스트가 없을 때 나오는 메시지를 확인한다.

        driver.findElement(By.xpath("//button[@class='toolbar-_-button'][1]")).click();
        driver.findElement(By.xpath("//button[@class='menu-_-button'][2]")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'dialog') and contains(., '확인')]")).click();

        WebElement emptyPlaceholder = driver.findElement(By.xpath("//p[contains(@class, 'empty')]"));
        Assertions.assertTrue(emptyPlaceholder.isDisplayed(),"포스트가 삭제되지 않음. Placeholder 메시지가 안보이거나...");

    }
}
