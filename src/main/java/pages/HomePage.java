package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements
                (new AjaxElementLocatorFactory
                        (driver, 10), this);
    }

    @FindBy(xpath = "//a[@href='/login?url=%2Fsearch']")
    WebElement linkLogin;
    @FindBy(xpath = "//app-navigator//a[@href='/registration?url=%2Fsearch']")
    WebElement linkRegistration;
    @FindBy(xpath = "//a[@href='/let-car-work']")
    WebElement linkLetCarWork;

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(css = "button[type='submit']")
    WebElement btnSubmit;

    @FindBy(css = "div[class='search-results'] > h3")
    WebElement searchResultsHeader;

    public boolean validateTextInSearchResultsIsPresent(String text){
        return isTextInElementPresent(searchResultsHeader, text);
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
        clickWait(btnSubmit);
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);

        String dates = startDate.getMonthValue() + "/"
                    + startDate.getDayOfMonth() + "/"
                    + startDate.getYear() + " - "
                    + endDate.getMonthValue() + "/"
                    + endDate.getDayOfMonth() + "/"
                    + endDate.getYear();

        inputDates.sendKeys(dates);
    }

    public void clickLinkLetCarWork() {
        linkLetCarWork.click();
    }

    public void clickLinkLogin() {
        linkLogin.click();
    }

    public void clickLinkRegistration() {
        linkRegistration.click();
    }
}