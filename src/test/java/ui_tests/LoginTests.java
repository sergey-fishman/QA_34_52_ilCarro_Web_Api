package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickLinkLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test()
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username("test321@gmail.com")
                .password("Test12345$")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.validateTextMessageLoginSuccess
                ("Logged in success"));
        loginPage.printMessageLogin();
    }
    /*
     User fails to log in if he bypasses text fields completely.
     Expected result: Failed to login. The e-mail and password textboxes are highlighted red.
     Error messages "Email is required" and "Password is required" appear
     */

    @Test
    public void loginNegativeEmptyFieldsTest() {
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.validateTextMessageEmailIsRequired
                ("Email is required"));
        Assert.assertTrue(loginPage.validateTextMessagePasswordIsRequired
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
        Assert.assertTrue(loginPage.validateTextMessageEmailIsRequired
                ("Email is required"));
        Assert.assertTrue(loginPage.validateTextMessagePasswordIsRequired
                ("Password is required"));
        Assert.assertFalse(loginPage.isLoginBtnEnabled());
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
