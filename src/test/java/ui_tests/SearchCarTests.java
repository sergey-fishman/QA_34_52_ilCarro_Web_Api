package ui_tests;

import manager.AppManager;
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
    }
}
