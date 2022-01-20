package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;

import java.io.IOException;
import java.util.List;


public class HotelSearchTest extends BaseTest {

    @Test

    public void searchHotelTest() {
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done");
        hotelSearchPage.setDates("22/12/2021", "24/12/2021");
        test.log(Status.PASS, "Setting date done");
        hotelSearchPage.setTravelers(1, 2);
        test.log(Status.PASS, "Setting travelers done");
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done");

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();


        System.out.println("Listed items : " + hotelNames.size());
        hotelNames.forEach(el -> System.out.println("* " + el));

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
        test.log(Status.PASS, "Assertions passed");


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


    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        hotelSearchPage.setDates("22/02/2022", "24/02/2022");
        hotelSearchPage.setTravelers(1, 2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();


        Assert.assertEquals(hotelNames.get(0), hotel);

    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xls");
    }
}
