package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static utils.PropertiesReader.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties","baseUrl"));
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