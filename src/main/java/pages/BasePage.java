package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Enums.HeaderMenu;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;

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
            System.out.println(e.getMessage());
        }
        return false;
    }

    public <T extends BasePage> T clickHeaderButtons(HeaderMenu item) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath(item.getLocator()))).click();
        switch (item) {
            case LOGO -> {
                return (T) new HomePage(driver);
            }
            case SEARCH -> {
                return (T) new HomePage(driver);
            }
            case LOG_OUT -> {
                return (T) new HomePage(driver);
            }
            case LET_THE_CAR_WORK -> {
                return (T) new LetTheCarWorkPage(driver);
            }
            case TERMS_OF_USE -> {
                return (T) new TermsOfUsePage(driver);
            }
            case SIGN_UP -> {
                return (T) new RegistrationPage(driver);
            }
            case LOG_IN -> {
                return (T) new LoginPage(driver);
            }
            case DELETE_ACCOUNT -> {
                return (T) new PopUpPage(driver);
            }
            default -> throw new IllegalArgumentException("Wrong menu item selected");
        }
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .textToBePresentInElement
                                    (element, text));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
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
