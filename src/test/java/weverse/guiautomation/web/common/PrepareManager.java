package weverse.guiautomation.web.common;

import org.openqa.selenium.WebDriver;
import weverse.guiautomation.web.pages.LoginPage;

import java.util.Map;

public class PrepareManager {

    public static void prepareLogin(WebDriver driver) {

        LoginPage loginPage = new LoginPage(driver);

        Map<String, String> account = ConfigManager.getAccount(0);
        driver.get("https://weverse.io/");

        loginPage.emailLoginButton().click();
        loginPage.emailInputField().sendKeys(account.get("email"));
        loginPage.passwordInputField().sendKeys(account.get("password"));
        System.out.println("로그인 준비 완료");

    }
}
