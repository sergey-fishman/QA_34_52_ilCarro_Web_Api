package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
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
    }

    @Test
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        softAssert.assertTrue(registrationPage
                .isBtnYallaEnabled("If False -> Btn yalla is not enabled"));
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage
                .validateTextInMatDialogContainerIsPresent("Registered"),
                "If False -> Text 'Registered' is not present");
        softAssert.assertAll();
    }
}
