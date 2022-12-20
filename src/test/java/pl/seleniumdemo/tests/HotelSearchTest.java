package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void  searchHotelTest()  {

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setCity("London");
        hotelSearchPage.setDates("29/04/2023","31/04/2023");
        hotelSearchPage.setTravellersInput(1,2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage=new ResultsPage(driver);
        List<String> hotelNames=resultsPage.getHotelNames();

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));


    }
}
