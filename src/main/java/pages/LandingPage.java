package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//input[@name='q-destination']")
    private WebElement destination;
    @FindBy(xpath = "//tr[@id='citysqm-asi0-s0']")
    private WebElement selectcityList;
    @FindBy(xpath = "//input[@name='q-localised-check-in']")
    private WebElement checkInDate;
    @FindBy(xpath = "//input[@name='q-localised-check-out']")
    private WebElement checkOutDate;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;
    @FindBy(xpath = "//button[@class='widget-overlay-close']")
    private WebElement closeCalander;
    @FindBy(xpath = "//button[contains(text(),'Accept')]")
    private  WebElement acceptCookies;
    @FindBy(xpath = "//*[@class='cta cta-strong cta-processing']")
    private WebElement submitProcessing;
    @FindBy(xpath = "//input[@type='radio'][1]")
    private  WebElement multipleCityAlaert;
    @FindBy(xpath = "//button[contains(text(),'Search')]")
    private  WebElement submitAlert;

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        acceptCookies();
    }

    public void setDestination(String location){
        clearData(destination);
        type(destination,location);
        waitForElementVisibilityAndClick(selectcityList,60);
        clickOnCityAlert();
    }
    public void setCheckInDate(String checkInDate){
        clearData(this.checkInDate);
        type(this.checkInDate,checkInDate);
    }
    public void setCheckOutDate(String checkOutDate){
        clearData(this.checkOutDate);
        type(this.checkOutDate,checkOutDate);
        if(isElementPresent(closeCalander))
            click(closeCalander);
    }
    public  void submit(){
       waitForElementVisibilityAndClick(submit,60);
    }

    public Object searchHotelAtLocation(String location, String checkIndate,String checkOutDate){
        setDestination(location);
        setCheckInDate(checkIndate);
        setCheckOutDate(checkOutDate);
        submit();
        waitForSubmitProcessing();
        if(getPageTitle().contains("hotels in"))
            return new SearchResultPage(driver);
        return null;
    }

    public void acceptCookies(){
        if(isElementPresent(acceptCookies))
           click(acceptCookies);
    }

    public void waitForSubmitProcessing(){
        waitProcessing(submitProcessing);
    }

    public void clickOnCityAlert(){
        if(isAlertPresent())
            multiplechoiceAllert(multipleCityAlaert,submitAlert);

    }
}
