package weverse.guiautomation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {

    private WebDriver driver;

    private static final By CREATE_POST_BUTTON  = By.xpath("//div[contains(text(), '이야기를 나눠보세요')]");
    private static final By POST_WRITE_MODAL = By.xpath("//div[@id='wev-editor']");
    private static final By UPLOAD_IMAGE = By.xpath("//input[@id='weuii']");
    private static final By UPLOAD_SUBMIT_BUTTON = By.xpath("//*//button[@class='confirm_button']");
    private static final By POST_WRITE_MODAL_SUBMIT_BUTTON = By.xpath("//button[contains(@class, 'secondary3')]");
    private static final By POST_LIST = By.xpath("//li[contains(@class, 'post-list')]");
    private static final By OVERFLOW = By.xpath("//button[@class='toolbar-_-button'][1]");
    private static final By OVERFLOW_EDIT = By.xpath("//button[@class='menu-_-button'][1]");
    private static final By UPLOAD_VIDEO = By.xpath("//input[@id='weuvi']");
    private static final By FORM = By.xpath("");

    // -------------------------------------------------

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement createPost() {
        return driver.findElement(CREATE_POST_BUTTON);
    }

    public WebElement postWriteModal() {
        return driver.findElement(POST_WRITE_MODAL);
    }

    public WebElement uploadImage() {
        return driver.findElement(UPLOAD_IMAGE);
    }

    public WebElement uploadSubmitButton() {
        return driver.findElement(UPLOAD_SUBMIT_BUTTON);
    }

    public WebElement postWriteModalSubmitButton() {
        return driver.findElement(POST_WRITE_MODAL_SUBMIT_BUTTON);
    }

    public WebElement postList() {
        return driver.findElement(POST_LIST);
    }

    public WebElement overFlow() {
        return driver.findElement(OVERFLOW);
    }

    public WebElement overFlowEdit() {
        return driver.findElement(OVERFLOW_EDIT);
    }

    public WebElement uploadVideo() {
        return driver.findElement(UPLOAD_VIDEO);
    }

}
