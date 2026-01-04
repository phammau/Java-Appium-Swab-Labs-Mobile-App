package maupham.com;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {
    private final By byUserName;
    private final By byPassword;
    private final By byBtnLogin;
    private final By byError;
    private final By byErrorPasswordEmpty;
    private By byErrorUsernameEmpty;

    public LoginPage(AndroidDriver driver) {
        super(driver);
        this.byUserName = AppiumBy.accessibilityId("test-Username");
        this.byPassword = AppiumBy.accessibilityId("test-Password");
        this.byBtnLogin = AppiumBy.accessibilityId("test-LOGIN");
        this.byError = AppiumBy.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
        this.byErrorPasswordEmpty = AppiumBy.xpath("//android.widget.TextView[@text=\"Password is required\"]");
        this.byErrorUsernameEmpty = AppiumBy.xpath("//android.widget.TextView[@text=\"Username is required\"]");
    }

    public void inputUsername(String name) {
        driver.findElement(byUserName).sendKeys(name);
    }

    public void inputPassword(String pass) {
        driver.findElement(byPassword).sendKeys(pass);
    }

    public void clickBtnLogin() {
        driver.findElement(byBtnLogin).click();
    }

    public String getError() {
        return driver.findElement(byError).getText();
    }

    public String getErrorPasswordEmpty() {
        return driver.findElement(byErrorPasswordEmpty).getText();
    }

    public String getErrorUsernameEmpty() {
        return driver.findElement(byErrorUsernameEmpty).getText();
    }

    public void loginAuto() throws InterruptedException {
        inputUsername("standard_user");
        inputPassword("secret_sauce");
        Thread.sleep(500);
        clickBtnLogin();
    }
}
