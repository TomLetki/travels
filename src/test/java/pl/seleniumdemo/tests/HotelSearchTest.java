package pl.seleniumdemo.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;


public class HotelSearchTest extends BaseTest {

 @Test

    public void searchHotelTest()  {

     HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
     hotelSearchPage.setCity("Dubai");
     hotelSearchPage.setDates("22/12/2021", "24/12/2021");
     hotelSearchPage.setTravellers();
     hotelSearchPage.performSearch();



       List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b")).stream()
                                                                                .map(el->el.getAttribute("textContent"))
                                                                                .collect(Collectors.toList());
       System.out.println("Listed items : "+hotelNames.size());
       hotelNames.forEach(el-> System.out.println("* "+el));

       Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
       Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
       Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
       Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

          }
    @Test
    public void searchHotelWithoutNameTest()  {

//data
        driver.findElement(By.name("checkin")).sendKeys("27/11/2021");
        // driver.findElement(By.name("checkout")).sendKeys("19/12/2021");

        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day 'and text()='30']"))
                .stream()
                .filter(el->el.isDisplayed())
                .findFirst()
                .ifPresent(el->el.click());

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement result = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2")); //opcja Bartka

        Assert.assertTrue(result.isDisplayed());
        Assert.assertEquals("No Results Found", result.getText());

    }

}
