package pages;

import Enums.Filters;
import Enums.SliderFilter;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//a[@class='menu-trigger'][contains(text(),'Price')]")
    private WebElement priceFilter;
    @FindBy(xpath = "//fieldset[@id='filter-guest-rating']//div[@class='cta cta-control widget-slider-handle widget-slider-handle-min']")
    private WebElement guestRatingSlider;
    @FindBy(xpath = "//section[@class='hotel-wrap']")
    private List<WebElement> hotels;
    @FindBy(xpath = ".//ins[contains(test(),'£')]")
    private WebElement amount;
    @FindBy(xpath = ".//a")
    private  WebElement hotelName;
    @FindBy(xpath = "//*[@class='widget-slider-current-values processing']")
    private WebElement ratingProcessing;
    @FindBy(xpath = "//*[@class='sort-option processing']")
    private WebElement priceFilterProcessing;
    @FindBy(xpath = "//*[@class='submenu-wrap']//*[contains(text(),'Price (high to low)')]")
    private  WebElement subMenuHigToLowFilter;
    @FindBy(xpath = "//h3[contains(text(),'Guest rating')]")
    private WebElement guestRatingMenu;

    public SearchResultPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public void applyFilter(Filters filters){
        switch (filters) {
            case PRICE:
             mouseHoverAndSelectFromList(priceFilter, subMenuHigToLowFilter);
        }
    }

    public void slide(SliderFilter sliderFilter, int from, int to){
        if(!isElementPresent(guestRatingMenu))
            clickOnGuestRatingMenu();
        switch (sliderFilter){
            case GUESTRATING:
                horizontalDrag(guestRatingSlider,from,to);
        }

    }
    public void clickOnGuestRatingMenu(){
        click(guestRatingMenu);
    }

    public void waitForGuestRatingProcessing(){
        waitProcessing(ratingProcessing);
        }


    public void waitForPriceProcessing(){
        waitProcessing(priceFilterProcessing);
    }

    public String hotelName(WebElement hotel){
      return  getElementText(hotel.findElement(By.className("property-name-link")));
    }

    public String hotelPrice(WebElement hotel){
        return getElementText(hotel.findElement(By.className("price-link")));
    }

    public List<WebElement> getAllHotels(){
        return hotels;
    }




}
