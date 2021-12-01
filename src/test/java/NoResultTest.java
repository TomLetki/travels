import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class NoResultTest {



   @Test
    public void searchHotelWithoutName()  {
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       driver.get("http://www.kurs-selenium.pl/demo/");

//data
       driver.findElement(By.name("checkin")).sendKeys("27/11/2021");
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
       driver.findElement(By.id("childPlusBtn")).click();
       driver.findElement(By.xpath("//button[text()=' Search']")).click();

     WebElement result = driver.findElement(By.xpath("//h2[@class='text-center']"));
     Assert.assertTrue(result.isDisplayed());
     Assert.assertEquals("No Results Found", result.getText());
   }
}
