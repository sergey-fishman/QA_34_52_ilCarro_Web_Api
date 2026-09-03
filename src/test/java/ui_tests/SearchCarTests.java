package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestNGListener;

import java.time.LocalDate;

@Listeners(TestNGListener.class)

public class SearchCarTests extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void setHomePage(){
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.validateTextInSearchResultsIsPresent
                ("No available cars"), "Text 'No available cars' must be present");
    }

    // TC 2 wrong start date
    @Test
    public void searchCarWrongStartDateNegativeTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't pick date before today"),
                "Error message is present: \"You can't pick date before today\"");
    }

    // TC 3 same start and end dates
    @Test
    public void searchCarSameStartEndDatesNegativeTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent
                        ("You can't book car for less than a day"),
                "Error message is present: \"You can't book car for less than a day\"");
    }
}
