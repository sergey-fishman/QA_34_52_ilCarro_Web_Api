package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.Enums.FuelTypeLocators;

import java.io.File;

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
    WebElement inputSelectFuel;
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

    @FindBy(id = "photos")
    WebElement inputPhotos;

    @FindBy(css = "button[type='submit']")
    WebElement btnSubmit;

    @FindBy(id = "mat-dialog-0")
    WebElement matDialogContainer;

    private void selectFuelTypeByLocator(FuelTypeLocators fuelTypeLocators) {
        inputSelectFuel.click();
        if (fuelTypeLocators != null) {
            driver.findElement(By.xpath(fuelTypeLocators.getLocator())).click();
        } else {
            pause(1000);
            inputSelectFuel.click();
        }

    }

    public void typeCarDetailsForm(Car car) {
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        selectFuelTypeByLocator(car.getFuelTypeLocators());
        inputSeats.sendKeys(car.getSeats().toString());
//        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerial());
        inputPrice.sendKeys(String.valueOf(car.getPrice()));
        textArea.sendKeys(car.getAbout());
    }

    public void clickCarDetailsForm() {
        inputLocation.click();
        inputManufacture.click();
        inputModel.click();
        inputYear.click();
        inputSelectFuel.click();
        inputSeats.click();
        inputClass.click();
        inputSerialNumber.click();
        inputPrice.click();
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
        clickWait(btnSubmit);
    }


    public void uploadImage(String filename) {
//        inputPhotos.sendKeys("C:\\AutoProjects\\QA_34_52_ilCarro_Web_Api\\src\\test\\resources\\cat.png");
        inputPhotos.sendKeys(new File("src/test/resources/" + filename)
                .getAbsolutePath());
    }

    public boolean validateTextInMatDialogContainerIsPresent(String text) {
        return isTextInElementPresent(matDialogContainer, text);
    }
}
