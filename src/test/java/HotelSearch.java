import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HotelSearch {



   @Test
    public void searchHotel()  {
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       driver.get("http://www.kurs-selenium.pl/demo/");

      //driver.findElement(By.xpath("//*[@id='s2id_autogen8']/a/span[1]")).click();
      driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
      driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
      driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
   }


}
