package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


public class AppManager {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup(Method method) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(enabled = false)
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}
