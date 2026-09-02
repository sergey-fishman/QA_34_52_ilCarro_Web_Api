package ui_tests;

import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.CarFactory;
import utils.Enums.HeaderMenu;
import utils.TestNGListener;

import java.time.LocalDate;

import static utils.PropertiesReader.getProperty;
import static utils.CarFactory.*;

@Listeners(TestNGListener.class)

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
                .email(getProperty("base.properties", "email"))
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
    public void addCarPositiveTest() {
        Car car = positiveCar();
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.uploadImage("cat.png");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.validateTextInMatDialogContainerIsPresent
                ("{\"city\":\"must not be blank\"}"), "If false -> text is not present");
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPoPupMessagePresent
                ("{\"city\":\"must not be blank\"}"), "If false -> text is not present");
        softAssert.assertAll();
        System.out.println(car);
    }

    // HW
    // TC 1 -> All empty fields without click
    @Test
    public void addCarNegativeEmptyFieldsTest() {
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.validateTextInMatDialogContainerIsPresent
                ("Car adding failed"));
    }

    // TC 2 -> All empty fields with click
    @Test
    public void addCarNegativeEmptyFieldsWithClickTest() {
        letTheCarWorkPage.clickCarDetailsForm();
        letTheCarWorkPage.clickBtnSubmitWithJS();
        letTheCarWorkPage.clickBtnSubmitWithJS(); // с первого раза не кликается!
        Assert.assertTrue(letTheCarWorkPage.validateTextInMatDialogContainerIsPresent
                ("Car adding failed"));
    }

    // TC 3.1 -> Any empty field with popup
    @Test
    public void addCarNegativeAnyFieldTest() {
        Car car = positiveCar();
        car.setModel("");
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.validateTextInMatDialogContainerIsPresent
                ("Car adding failed"), "If false -> text is not present");
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPoPupMessagePresent
                ("\"model\":\"must not be blank\""), "If false -> text is not present");
        softAssert.assertAll();
    }

    // TC 3.2 -> Any empty field with error message
    @Test
    public void addCarNegativeAnyFieldErrorMessageTest() {
        Car car = CarFactory.positiveCar();
        car.setFuelTypeLocators(null);
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent
                ("Fuel is required"), "If false -> text is not present");
    }

    // TC 4.1 -> non-digit inside the year input field 3 tests
    @Test
    public void addCarWrongYearFieldNegativeTest_1() {
        Car car = positiveCar();
        car.setYear("e2026");
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent
                ("Year required"), "If false -> text is not present");
    }

    // TC 4.2 -> Wrong year
    @Test()
    public void addCarWrongYearFieldNegativeTest_2() {
        Car car = positiveCar();
        car.setYear("-1");
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent
                ("Wrong year"), "If false -> text is not present");
    }

    // TC 4.3 -> Wrong year
    @Test()
    public void addCarWrongYearFieldNegativeTest_3() {
        Car car = positiveCar();
        car.setYear(String.valueOf(LocalDate.now().getYear() + 1));
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.uploadImage("zebra.png");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent
                ("Wrong year"), "If false -> text is not present");
    }
}
