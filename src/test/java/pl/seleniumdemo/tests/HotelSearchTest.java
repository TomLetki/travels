package pl.seleniumdemo.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;


public class HotelSearchTest extends BaseTest {

    @Test

    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("London");
        hotelSearchPage.setDates("22/12/2021", "24/12/2021");
        hotelSearchPage.setTravelers(1, 2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();


        System.out.println("Listed items : " + hotelNames.size());
        hotelNames.forEach(el -> System.out.println("* " + el));

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }

    @Test
    public void searchHotelWithoutNameTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("24/12/2021", "27/12/2021");
        hotelSearchPage.setTravelers(0, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals("No Results Found", resultsPage.getHeadingText());

    }

}
