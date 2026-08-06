package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickLinkLogin();
    }

    @Test()
    public void LoginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username("test321@gmail.com")
                .password("Test12345$")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
    }
}
