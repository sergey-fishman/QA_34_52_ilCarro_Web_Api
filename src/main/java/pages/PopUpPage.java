package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PopUpPage extends BasePage {

    public PopUpPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory
                        (driver, 10), this);
    }

    @FindBy(id = "mat-dialog-0")
    WebElement popUpContainer;

    public boolean isTextInPoPupContainerPresent(String text){
        return isTextInElementPresent(popUpContainer,text);
    }

}
