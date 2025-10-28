package weverse.guiautomation.web.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import weverse.guiautomation.web.pages.LoginPage;
import weverse.guiautomation.web.pages.MainPage;

public class PrepareManager {

    public static void prepareLogin(WebDriver driver) {

        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://weverse.io/");

        loginPage.emailLoginButton().click();
        loginPage.emailInputField().sendKeys("rxvpoker002@gmail.com");
        loginPage.passwordInputField().sendKeys("12e12e1!");
        loginPage.loginButton().click();
        System.out.println("로그인 준비 완료");

    }
}
