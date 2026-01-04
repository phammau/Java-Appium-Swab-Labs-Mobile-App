package maupham.com;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void beforeTest() throws Exception {
        setUp(); // dung cua basetest
    }
    
    @Test
    public void test01() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickBtnLogin();

        var productPage = new ProductPage(driver);
        Thread.sleep(5000);
        assertTrue(productPage.isDisplayedOk());
    }

    @Test
    public void test02() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("12345");
        loginPage.clickBtnLogin();
        Assert.assertEquals((loginPage.getError()), "Username and password do not match any user in this service.");
    }

    @Test
    public void test03() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("ABC");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickBtnLogin();
        Assert.assertEquals((loginPage.getError()), "Username and password do not match any user in this service.");
    }

    @Test
    public void test04() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("ABC");
        loginPage.inputPassword("12345");
        loginPage.clickBtnLogin();
        Assert.assertEquals((loginPage.getError()), "Username and password do not match any user in this service.");
    }

    @Test
    public void test05() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("");
        loginPage.clickBtnLogin();
        Assert.assertEquals((loginPage.getErrorPasswordEmpty()), "Password is required");
    }

    @Test
    public void test06() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickBtnLogin();
        Assert.assertEquals((loginPage.getErrorUsernameEmpty()), "Username is required");
    }
    
    @Test
    public void test07() throws Exception {
        var loginPage = new LoginPage(driver);
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickBtnLogin();
        Assert.assertEquals((loginPage.getErrorUsernameEmpty()), "Username is required");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

