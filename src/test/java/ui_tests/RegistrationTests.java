package ui_tests;

import data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.PropertiesReader;
import utils.TestNGListener;

import static utils.UserFactory.*;

@Listeners(TestNGListener.class)

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void gotoRegistrationPage() {
        new HomePage(getDriver()).clickLinkRegistration();
        registrationPage = new RegistrationPage(getDriver());
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
                .isTextInPoPupContainerPresent("You are logged in success"));
        softAssert.assertAll();
    }

    // TC 1 -> User fails to reg if he bypasses text fields completely.
    // Expected result -> Reg failed, Button Yalla not clickable,
    // Text fields are highlighted red and error messages appear.
    // Test failed as expected after 20 sec
    // Each element from isTextInErrorPresent takes 5 sec to load -> AjaxElementLocatorFactory
    @Test
    public void registrationNegativeEmptyFieldsNoClickTest() {
        registrationPage.clickCheckboxWithActions();
        registrationPage.clickBtnYalla();
        softAssert.assertFalse(registrationPage.isBtnYallaEnabled(), "If False -> Btn yalla is enabled");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"),
                "Error message contains text \"Name is required\"");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"),
                "Error message contains text \"Last name is required\"");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"),
                "Error message contains text \"Email is required\"");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"),
                "Error message contains text \"Password is required\"");
        softAssert.assertAll();
    }

    // TC 1.1 Experimental test case -> Click on enabled Button Yalla with JS
    @Test
    public void registrationEmptyFieldsNoClickWithYallaEnabledNegativeTest() {
        registrationPage.clickCheckboxWithActions();
        registrationPage.clickBtnYallaWithJS();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPoPupContainerPresent("Registration failed"),
                "Text in container has message: \"Registration failed\"");
        Assert.assertTrue(registrationPage
                .validateTextInMatDialogContainerIsPresent("Registration failed"),
                "Text in container has message: \"Registration failed\"");
    }

    // TC 2 -> User fails to reg if he only clicks on text fields but leaves them empty.
    // Expected result -> Reg failed, Button Yalla not clickable,
    // Text fields are highlighted red and error messages appear.
    // Test passed
    @Test
    public void registrationNegativeEmptyFieldsTest() {
        registrationPage.clickOnTextInputFields();
        registrationPage.clickCheckboxWithActions();
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
    @Test(dataProvider = "dataProviderWrongPassword",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeInvalidPasswordTest(UserLombok user) {
        registrationPage.clickCheckboxWithActions();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        softAssert.assertFalse(registrationPage
                .isBtnYallaEnabled(), "If False -> Btn yalla is enabled");
        softAssert.assertTrue(registrationPage
                .isTextInErrorPresent("Password must"));
        softAssert.assertAll();
    }

    // TC 4 -> User fails to register with wrong email format -> 11 tests
    // all tests passed
    @Test(dataProvider = "dataProviderWrongEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongEmailFormatTest(UserLombok user) {
        registrationPage.clickCheckboxWithActions();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        softAssert.assertFalse(registrationPage
                .isBtnYallaEnabled(), "If False -> Btn yalla is enabled");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Wrong email format"),
                "If false -> message Wrong email format is not present");
        softAssert.assertAll();
    }

    // TC 5 -> User fails to register with already existing email
    // passed
    @Test
    public void registrationNegativeExistingEmailTest() {
        registrationPage.clickCheckboxWithActions();
        UserLombok user = positiveUser();
        user.setEmail(PropertiesReader
                .getProperty("base.properties", "email"));
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                        .isTextInPoPupContainerPresent("User already exists"),
                "If false -> text in popUp message is not present");
    }

    // TC 6 -> User fails to register without signing the checkbox
    @Test
    public void registrationNegativeNoCheckboxTest() {
        UserLombok user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        Assert.assertFalse(registrationPage.isBtnYallaEnabled());
    }
}
