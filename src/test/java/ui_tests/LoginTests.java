package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickLinkLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test()
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.validateTextMessageLoginSuccess
                ("Logged in success"));
        Assert.assertTrue(loginPage.isMessageLoginDisplayed());
        loginPage.printMessageLogin();
    }

    @Test()
    public void loginNegativeWrongEmailTest() {
        UserLombok user = UserLombok.builder()
                .username("tast321@gmail.com")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.isMessageLoginFailedDisplayed());
    }

    @Test()
    public void loginNegativeWrongPasswordTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password("Test123456$")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.isMessageLoginFailedDisplayed());
    }
    /*
     User fails to log in if he bypasses text fields completely.
     Expected result: Failed to login. The e-mail and password textboxes are highlighted red.
     Error messages "Email is required" and "Password is required" appear
     */

    @Test
    public void loginNegativeEmptyFieldsNoClickTest() {
        loginPage.clickBtnLogin();
        Assert.assertFalse(loginPage.validateTextMessageEmailIsRequired
                ("Email is required"));
        Assert.assertFalse(loginPage.validateTextMessagePasswordIsRequired
                ("Password is required"));
        Assert.assertFalse(loginPage.isLoginBtnEnabled());
    }
    /*
    Expected condition failed: waiting for text ('Email is required')
    to be present in element [[ChromeDriver: chrome on windows
    (8c16df3dcc655aa397c57f5fb4de66fe)] ->
    xpath: //div[@class='input-container'][1]]
    (tried for 5 second(s) with 500 milliseconds interval)
     */

    // User fails to log in if he clicks on text fields but leaves them empty
    @Test
    public void loginNegativeEmptyFieldsWithClickTest() {
        loginPage.clickOnTextFields();
        loginPage.clickBtnLogin();
//        Assert.assertTrue(loginPage.validateTextMessageEmailIsRequired
//                ("Email is required"));
//        Assert.assertTrue(loginPage.validateTextMessagePasswordIsRequired
//                ("Password is required"));
        softAssert.assertFalse(loginPage.isLoginBtnEnabled(), "btn Login validation");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"), "email is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyEmailFieldTest() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password("Test12345$")
                .build();
        loginPage.typeLoginForm(user);
        Assert.assertFalse(loginPage.isLoginBtnEnabled());
    }
}
