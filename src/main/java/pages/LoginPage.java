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

    public void typeLoginForm(UserLombok user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnLogin(){
        btnLogin.click();
    }

}
