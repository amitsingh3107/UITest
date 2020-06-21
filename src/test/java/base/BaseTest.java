package base;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public String baseUrl;
    public WebDriver driver;

    public void baseEnvSetUp(){
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        baseUrl="https://uk.hotels.com";
        driver.get(baseUrl);

    }

    public void closeDriver(){
        driver.close();
    }

    public void explicitWait(WebElement locatorKey, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(locatorKey));
    }
}
