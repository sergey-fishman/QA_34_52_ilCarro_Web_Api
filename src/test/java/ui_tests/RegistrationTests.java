package ui_tests;

import data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.UserFactory;

import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void gotoRegistrationPage() {
        new HomePage(getDriver()).clickLinkRegistration();
        registrationPage = new RegistrationPage(getDriver());
        logger.info("start registration test");
    }

    @Test
    public void checkbox() {
        registrationPage.clickCheckbox();
    }

    @Test
    public void checkboxWithJS() {
        registrationPage.clickCheckboxJS();
    }

    @Test
    public void checkboxWithActions() {
        registrationPage.clickCheckboxWithActions();
    }

    // HW_06 -> User registers with correct data -> Test passed
    @Test
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxWithActions();
        softAssert.assertTrue(registrationPage
                .isBtnYallaEnabled(), "If False -> Btn yalla is not enabled");
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage
                        .validateTextInMatDialogContainerIsPresent("Registered"),
                "If False -> Text 'Registered' is not present");
        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPoPupMessagePresent("You are logged in success"));
        softAssert.assertAll();
    }

    // TC 1 -> User fails to reg if he bypasses text fields completely.
    // Expected result -> Reg failed, Button Yalla not clickable,
    // Text fields are highlighted red and error messages appear.
    // Test failed as expected
    // Each element from isTextInErrorPresent takes 10 sec to load -> AjaxElementLocatorFactory
    @Test
    public void registrationNegativeEmptyFieldsNoClickTest() {
        registrationPage.clickBtnYalla();
        softAssert.assertFalse(registrationPage
                .isBtnYallaEnabled(), "If False -> Btn yalla is enabled");
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Name is required"), "Name is required error not found");
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Last name is required"),"Last name is required error not found");
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Email is required"),"Email is required error not found");
        Assert.assertTrue(registrationPage
                .isTextInErrorPresent("Password is required"),"Password is required error not found");
        softAssert.assertAll();
    }
    // TC 2 -> User fails to reg if he only clicks on text fields but leaves them empty.
    // Expected result -> Reg failed, Button Yalla not clickable,
    // Text fields are highlighted red and error messages appear.
    // Test passed
    @Test
    public void registrationNegativeEmptyFieldsTest() {
        registrationPage.clickCheckbox();
        registrationPage.clickOnTextInputFields();
        registrationPage.clickBtnYalla();
        softAssert.assertFalse(registrationPage
                .isBtnYallaEnabled(), "If False -> Btn yalla is enabled");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"));
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"));
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"));
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"));
        softAssert.assertAll();
    }
    // TC 3 -> User fails to reg with invalid password.
    // Expected result -> Reg failed, Button Yalla not clickable,
    // Password input field is highlighted red and an error message appears.
    // 7 tests passed
    @Test(dataProvider = "dataProviderWrongPasswordOrEmail",
    dataProviderClass = UserDataProvider.class)
    public void registrationNegativeInvalidPasswordTest(UserLombok user) {
        registrationPage.clickCheckbox(); // always first, otherwise not clickable
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        softAssert.assertFalse(registrationPage
                .isBtnYallaEnabled(), "If False -> Btn yalla is enabled");
        softAssert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must"));
        softAssert.assertAll();
    }
}
