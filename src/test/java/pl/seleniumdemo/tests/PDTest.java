package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;

public class PDTest extends BaseTest {

    @Test
    public void noResultsFoundTest(){

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setDates("25/12/2022","30/12/2022");
        hotelSearchPage.setTravellersInput(0,1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage=new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(),"No Results Found");


    }
}
