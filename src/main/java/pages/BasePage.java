package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;
    public Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void setDriver(WebDriver wd) {
        driver = wd;
    }

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

    public boolean isTextInErrorPresent(String text) {
        if (listErrors == null || listErrors.isEmpty()) return false;
        for (WebElement element : listErrors) {
            if (element.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void clickWait(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until
                        (ExpectedConditions.elementToBeClickable(element))
                        .click();
    }

    public boolean isUrlContainsText(String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5)).until
                    (ExpectedConditions.urlContains(text));
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .textToBePresentInElement
                                    (element, text));
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean isTextInElementPresentSimple(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public String closeAlert() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }


    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
