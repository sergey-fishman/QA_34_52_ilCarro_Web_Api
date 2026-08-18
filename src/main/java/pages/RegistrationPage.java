package pages;

import dto.UserLombok;
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
    @FindBy(xpath = "//form//button")
    WebElement btnYalla;

    @FindBy(id = "mat-dialog-0")
    WebElement matDialogContainer;

    @FindBy(xpath = "//form/div[1]")
    WebElement firstNameContainer;
    @FindBy(xpath = "//form/div[2]")
    WebElement lastNameContainer;
    @FindBy(xpath = "//form/div[3]")
    WebElement emailContainer;
    @FindBy(xpath = "//form/div[4]")
    WebElement passwordContainer;


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

    public boolean validateTextInFirstNameContainer(String text) {
        return isTextInElementPresentSimple(firstNameContainer, text);
    }
}
