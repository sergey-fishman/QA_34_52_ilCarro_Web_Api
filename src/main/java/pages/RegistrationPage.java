package pages;

import dto.UserLombok;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory
                        (driver, 10), this);
    }

    @FindBy(xpath = "//form/div[1]/input")
    WebElement inputFirstName;
    @FindBy(xpath = "//form/div[2]/input")
    WebElement inputLastName;
    @FindBy(xpath = "//form/div[3]/input")
    WebElement inputEmail;
    @FindBy(xpath = "//form/div[4]/input")
    WebElement inputPassword;
    @FindBy(xpath = "//div[contains(@class, 'checkbox')]")
    WebElement checkbox;
    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBoxInput;
    @FindBy(xpath = "//form//button")
    WebElement btnYalla;

    @FindBy(id = "mat-dialog-0")
    WebElement matDialogContainer;

    public void typeRegistrationForm(UserLombok user) {
        inputFirstName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickOnTextInputFields() {
        inputFirstName.click();
        inputLastName.click();
        inputEmail.click();
        inputPassword.click();
    }

    public void clickCheckbox() {
        checkbox.click();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean isBtnYallaEnabled() {
        return btnYalla.isEnabled();
    }

    public boolean validateTextInMatDialogContainerIsPresent(String text) {
        return isTextInElementPresentSimple(matDialogContainer, text);
    }

    public void clickCheckboxJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxInput);
    }
}