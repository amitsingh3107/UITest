package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;

    public BasePage(){}
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    /* basic method */

    public String getPageTitle(){
        return driver.getTitle();
    }

    public boolean isElementPresent(WebElement locator){
            return locator.isDisplayed();

    }

    public boolean isElementExists(WebElement locator){
        try{
            return locator.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public void clearData(WebElement locator){
        locator.clear();

    }

    public void type(WebElement locator, String text){
        locator.sendKeys(text);
    }

    public  void click(WebElement locator){
        locator.click();
    }

    public void explicitWait(WebElement locatorKey, int time) {

        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(locatorKey));
    }

    public void waitForElementVisibilityAndClick(WebElement locator, int time){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(time, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(locator));
        locator.click();
    }

    public  void waitForElementClickableAndClick(WebElement locator, int time){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(time, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        locator.click();
    }

    public void horizontalDrag(WebElement locator, int from , int to){
        Actions move = new Actions(driver);
        // move.moveToElement(locator).click().build().perform();
         for(int i=1;i<=to;i++){
             move.moveToElement(locator).click(locator).sendKeys(Keys.ARROW_RIGHT).perform();
         }
    }

    public void mouseHoverAndSelectFromList(WebElement hoverElement, WebElement clickElement){
        Actions hover = new Actions(driver);
        Action action =(Action)hover.moveToElement(hoverElement).moveToElement(clickElement).click().build();
        action.perform();
    }

    public String getElementText(WebElement locator){
        return locator.getText();
    }

    public  void waitProcessing(WebElement locator){
        boolean isPresent = isElementExists(locator);
        while(isPresent){
            isPresent =  isElementExists(locator);
        }
    }

    public void multiplechoiceAllert(WebElement locator,WebElement submit){
        driver.switchTo().alert();
        locator.click();
        submit.click();
    }

    public  boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }catch(NoAlertPresentException ex){
            return false;

        }

    }

    public void scrollPageElementInView( WebElement locator) {
      // Actions action = new Actions(driver);
      // action.keyDown(Keys.COMMAND).sendKeys(Keys.DOWN).build().perform();
        // ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"");
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",locator);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
      //  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",locator);
     public void getCarrier(){

       WebElement element =  driver.findElement(By.xpath("//*[contains(text(),'Careers')]"));
       Actions actions = new Actions(driver);
       actions.moveToElement(element).build().perform();
     }

    }

