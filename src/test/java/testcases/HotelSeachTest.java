package testcases;

import Enums.Filters;
import Enums.SliderFilter;
import base.BaseTest;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SearchResultPage;
import utils.DateUtils;
import utils.Filesutils;
import utils.ReadJson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HotelSeachTest extends BaseTest {
    private LandingPage landingPage;
    private SearchResultPage searchResultPage;
    private  String csvFilePath;
    @BeforeTest
    public void setUp(){
        csvFilePath = Filesutils.getOutputFolderPath() + File.separator + "output.csv";
        Filesutils.deleteFile(csvFilePath);
        baseEnvSetUp();
        landingPage = new LandingPage(driver);

        //writer = new CSVWriter(csvFilePath);
    }

    @Test(dataProvider = "getLocation")
    public void searchHotelTest(JsonObject data){
        List<String []> output = new ArrayList<>();
        String checkInDate = DateUtils.setDateMonthBase(data.get("checkInDate").getAsInt());
        String checkOutDate = DateUtils.setDateMonthDayBased(data.get("checkInDate").getAsInt(),data.get("checkOutDate").getAsInt());
       Object object= landingPage.searchHotelAtLocation(data.get("location").getAsString(),
                checkInDate,checkOutDate);
       if(object ==null)
           Assert.fail("Page not Loaded");
       searchResultPage = (SearchResultPage) object;
       searchResultPage.applyFilter(Filters.PRICE);
       searchResultPage.waitForPriceProcessing();
       searchResultPage.slide(SliderFilter.GUESTRATING,data.get("guestRatingSlideFrom").getAsInt(),data.get("guestRatingSlideTo").getAsInt());
       searchResultPage.waitForGuestRatingProcessing();
       //searchResultPage.scrollPage();
        List<WebElement> elements = searchResultPage.getAllHotels();
        searchResultPage.scrollPageElementInView(elements.get(elements.size()-1));
        elements = searchResultPage.getAllHotels();
        int count=1;
       for(int counter =0;counter<elements.size();counter++){
           if(count>10)
               break;
           String name = searchResultPage.hotelName(elements.get(counter));
           System.out.println(name);
           String price = searchResultPage.hotelPrice(elements.get(counter));
           if(price.contains("\n"))
               price =  price.split("\\r?\\n")[1];
           price=price.substring(1);
           System.out.println(price);
           output.add(new String[]{name,price});
           count++;
       }
       Filesutils.addCSV(csvFilePath,output);



    }

    @DataProvider(name = "getLocation")
    public Object[][] getData(){
      return ReadJson.getData("seachHotel", Filesutils.getJsonFilePath()+ File.separator+"searchHotel.json");
    }

    @AfterTest
    public void cleanUp(){
    //    closeDriver();
    }

}
