package ui_tests;

import utils.CarFactory;
import utils.Enums.FuelType;
import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.Enums.HeaderMenu;

import static utils.PropertiesReader.getProperty;
import static utils.CarFactory.*;

public class LetTheCarWorkTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLetTheCarWorkPageWithAuth() {
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickLinkLogin();
//        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LOG_IN);
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        softAssert.assertTrue(loginPage.validateTextMessageLoginSuccess
                ("Logged in success"), "Unable to get successful login message");
        loginPage.clickBtnOK();
        letTheCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
    }

    @Test
    public void positiveTest() {
        Car car = positiveCar();
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.validateTextInMatDialogContainerIsPresent
                ("{\"city\":\"must not be blank\"}"), "If false -> text is not present");
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPoPupMessagePresent
                ("{\"city\":\"must not be blank\"}"), "If false -> text is not present");
        softAssert.assertAll();
        System.out.println(car);
    }




}
