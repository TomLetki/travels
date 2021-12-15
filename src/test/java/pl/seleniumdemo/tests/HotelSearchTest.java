package pl.seleniumdemo.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;


public class HotelSearchTest extends BaseTest {

 @Test

    public void searchHotelTest()  {

      driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
      driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
      driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
//data
       driver.findElement(By.name("checkin")).sendKeys("25/11/2021");
      // driver.findElement(By.name("checkout")).sendKeys("19/12/2021");

       driver.findElement(By.name("checkout")).click();
       driver.findElements(By.xpath("//td[@class='day 'and text()='30']"))
                                                               .stream()
                                                               .filter(el->el.isDisplayed())
                                                               .findFirst()
                                                               .ifPresent(el->el.click());
        // driver.findElement(By.name("checkout")).sendKeys("19/12/2021");
       // ilość gości
       driver.findElement(By.id("travellersInput")).click();
       driver.findElement(By.id("adultPlusBtn")).click();
       driver.findElement(By.id("childPlusBtn")).click();

       driver.findElement(By.xpath("//button[text()=' Search']")).click();

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
