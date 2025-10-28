package weverse.guiautomation.web.common;

import org.openqa.selenium.WebDriver;
import weverse.guiautomation.web.pages.LoginPage;

public class PrepareManager {

    public static void prepareLogin(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInputField().sendKeys("");
        loginPage.passwordInputField().sendKeys("");
        System.out.println("로그인 준비 완료");
    }
}
