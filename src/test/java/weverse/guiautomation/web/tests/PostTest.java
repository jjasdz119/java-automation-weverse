package weverse.guiautomation.web.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import weverse.guiautomation.web.common.DriverManager;
import weverse.guiautomation.web.common.PrepareManager;
import weverse.guiautomation.web.pages.BasePage;
import weverse.guiautomation.web.pages.LoginPage;
import weverse.guiautomation.web.pages.MainPage;

import java.time.Duration;


public class PostTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setup() {

        driver = DriverManager.setConfig();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        PrepareManager.prepareLogin(driver);
//        driver.navigate().to("https://weverse.io/");

        mainPage.loginButton();

        mainPage.searchCommunity().click();
        mainPage.searchCommunityList().clear();
        mainPage.communityJoinButton().click();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    // 사전조건: 생성한 계정 로그인, 임의 커뮤니티 가입, 프로필 엔드 접근(?)

    @Test
    @Order(1)
    @DisplayName("포스트 작성하기 테스트")
    void testCreatePost() {
        // 텍스트와 이미지 파일을 업로드하여 포스트를 생성한다.
        // 포스트가 등록되었는지 확인한다.

        String content = "Hello";

        driver.findElement(By.xpath("//div[contains(@class, 'profile_wrapper')]")).click();
        driver.findElement(By.xpath("//div[contains(text(), '이야기를 나눠보세요')]")).click();
        driver.findElement(By.xpath("//div[@id='wev-editor']")).sendKeys(content);
        driver.findElement(By.xpath("//label[contains(@class, 'attachment_button')][1]")).click();
        // 이미지 첨부
        String projectPath = System.getProperty("user.dir");
        String imagePath = projectPath + "/src/test/resources/sample_image.png";
        driver.findElement(By.xpath("")).sendKeys(imagePath);
        driver.findElement(By.xpath("//*//button[@class='confirm_button']")).click();
        // 포스트 '등록' 선택
        driver.findElement(By.xpath("//button[contains(@class, 'secondary3')]")).click();

        // 기대결과 넣기
        WebElement postList = driver.findElement(By.xpath("//li[contains(@class, 'post-list')]"));
        Assertions.assertTrue(postList.getText().contains(content));
    }

    @Test
    @Order(2)
    @DisplayName("포스트 편집하기 테스트")
    void testEditPost() {
        // 저장된 텍스트를 다른 내용으로 업데이트한다.
        // 이미지 파일을 제거하고 미디어 파일을 업로드한다.

        String updateContent = "Hello Update";

        driver.findElement(By.xpath("//button[@class='toolbar-_-button'][1]")).click();
        driver.findElement(By.xpath("//button[@class='menu-_-button'][1]")).click();
        driver.findElement(By.xpath("//div[@id='wev-editor']")).clear();
        driver.findElement(By.xpath("//div[@id='wev-editor']")).sendKeys(updateContent);

        // 이미지 파일 제거 후 미디어 파일 업로드
        driver.findElement(By.xpath("//button[@class='btn -del _deleteWidget']")).click();
        driver.findElement(By.xpath("//label[contains(@class, 'attachment_button')][2]")).click();

//        Assertions.assertTrue();
    }

    @Test
    @Order(3)
    @DisplayName("포스트 삭제하기 테스트")
    void testDeletePost() {
        // 생성한 포스트를 삭제한다.
        // 포스트 제거 후 등록한 포스트가 없을 때 나오는 메시지를 확인한다.

        driver.findElement(By.xpath("")).click();

        driver.findElement(By.xpath("//button[@class='toolbar-_-button'][1]")).click();
        driver.findElement(By.xpath("//button[@class='menu-_-button'][2]")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'dialog') and contains(., '확인')]")).click();

        WebElement emptyPlaceholder = driver.findElement(By.xpath("//p[contains(@class, 'empty')]"));
        Assertions.assertTrue(emptyPlaceholder.isDisplayed());
    }
}
