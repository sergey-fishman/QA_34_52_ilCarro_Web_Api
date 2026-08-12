package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        PageFactory.initElements
                (new AjaxElementLocatorFactory
                        (driver,10),this);
    }

    @FindBy(xpath = "//form/div[1]/input")
    WebElement inputEmail;
    @FindBy(xpath = "//form/div[2]/input")
    WebElement inputPassword;
    @FindBy(xpath = "//form/button")
    WebElement btnLogin;

    @FindBy(xpath = "//div[@class='input-container'][1]")
    WebElement emailInputContainer;
    @FindBy(xpath = "//div[@class='input-container'][2]")
    WebElement passwordInputContainer;


    @FindBy(xpath = "//h2[@class='message']")
    WebElement messageLoginSuccess;
    @FindBy(xpath = "//h1[text()='Login failed']")
    WebElement messageLoginFailed;

    public void typeLoginForm(UserLombok user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickOnTextFields() {
        inputEmail.click();
        inputPassword.click();
    }

    public void clickBtnLogin(){
        btnLogin.click();
    }

    public boolean isLoginBtnEnabled() {
        return btnLogin.isEnabled();
    }

    public void printMessageLogin(){
        System.out.println(">>>> message login >>>> " +
                messageLoginSuccess.getText());
    }

    public boolean validateTextMessageLoginSuccess(String text) {
        return isTextInElementPresent(messageLoginSuccess, text);
    }

    public boolean isMessageLoginDisplayed() {
        return isDisplayed(messageLoginSuccess);
    }

    public boolean isMessageLoginFailedDisplayed() {
        return isDisplayed(messageLoginFailed);
    }

    public boolean validateTextMessageEmailIsRequired(String text) {
        return isTextInElementPresent(emailInputContainer, text);
    }

    public boolean validateTextMessagePasswordIsRequired(String text) {
        return isTextInElementPresent(passwordInputContainer, text);
    }
}
