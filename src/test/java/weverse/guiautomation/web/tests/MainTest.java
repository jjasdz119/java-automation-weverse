package weverse.guiautomation.web.tests;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.json.Json;
import weverse.guiautomation.web.pages.MainPage;
import weverse.guiautomation.web.pages.RegisterPage;

import java.time.Duration;

public class MainTest {


    private WebDriver driver;
    private MainPage mainPage;
    private final String BASE_URL = "https://global.apis.naver.com/weverse/wevweb/users/v1.0";

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://weverse.io/");
        mainPage = new MainPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
