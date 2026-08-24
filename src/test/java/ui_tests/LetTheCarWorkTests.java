package ui_tests;

import Enums.FuelType;
import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;

import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLetTheCarWorkPageWithAuth() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLinkLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        softAssert.assertTrue(loginPage.validateTextMessageLoginSuccess
                ("Logged in success"), "Unable to get successful login message");
        loginPage.clickBtnOK();
        homePage = new HomePage(getDriver());
        homePage.clickLinkLetCarWork();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
    }

    @Test
    public void positiveTest() {
        letTheCarWorkPage.typeLocation("Berlin");
        Car car = Car.builder()
                .manufacture("Toyota")
                .model("Yaris")
                .year("2026")
                .fuelType(FuelType.ELECTRIC.getValue())
                .seats("5")
                .carClass("Eco")
                .serial("QWE123RTY")
                .price("299")
                .build();
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.typeTextArea("lkdsjflsddkjfksdjf\noasjsaf282834##$$@&&\n6564so\n-=]][as[p]1@3&&^%");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.validateTextInMatDialogContainerIsPresent
                ("{\"city\":\"must not be blank\"}"), "If false -> text is not present");
        softAssert.assertAll();
    }




}
