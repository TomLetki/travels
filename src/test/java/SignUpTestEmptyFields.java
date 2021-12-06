import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTestEmptyFields {



   @Test
    public void signUpEmpty()  {
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       driver.get("http://www.kurs-selenium.pl/demo/");

       driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                                                               .findFirst().ifPresent(WebElement::click);
       driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();


      driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

      List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p")).stream()
              .map(WebElement::getText).collect(Collectors.toList());

       SoftAssert softAssert = new SoftAssert();
       softAssert.assertTrue(errors.contains("The Email field is required."));
       softAssert.assertTrue(errors.contains("The Password field is required."));
       softAssert.assertTrue(errors.contains("The Password field is required."));
       softAssert.assertTrue(errors.contains("The First name field is required."));
       softAssert.assertTrue(errors.contains("The Last Name field is required."));
       softAssert.assertAll();





      //WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
      //Assert.assertTrue(heading.getText().contains(lastname));
      //Assert.assertEquals(heading.getText(), "Hi, Tomasz Comasz");


       
   }


}
