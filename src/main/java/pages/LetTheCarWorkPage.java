package pages;

import dto.Car;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetTheCarWorkPage extends BasePage {

    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory
                        (driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;

    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement selectFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;

    @FindBy(id = "about")
    WebElement textArea;

    @FindBy(css = "button[type='submit']")
    WebElement btnSubmit;

    @FindBy(id = "mat-dialog-0")
    WebElement matDialogContainer;

    public void typeLocation(String text) {
        inputLocation.sendKeys(text);
    }

    public void typeCarDetailsForm(Car car) {
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        selectFuel.sendKeys(car.getFuelType());
        inputSeats.sendKeys(car.getSeats().toString());
        inputClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerial());
        inputPrice.sendKeys(car.getPrice().toString());
        textArea.sendKeys(car.getAbout());
    }

    public void typeTextArea(String text) {
        textArea.sendKeys(text);
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
        clickWait(btnSubmit);
    }

    public boolean validateTextInMatDialogContainerIsPresent(String text) {
        return isTextInElementPresent(matDialogContainer, text);
    }
}
