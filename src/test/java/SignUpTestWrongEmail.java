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

public class SignUpTestWrongEmail {



   @Test
    public void signUpWrongEmail()  {
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       driver.get("http://www.kurs-selenium.pl/demo/");

       driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                                                               .findFirst().ifPresent(WebElement::click);
       driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

       //uzupełnianie danych użytkownika
      String lastname = "Comasz";
      int randomNumber = (int) (Math.random()*1000);
      String email = "Tester"+randomNumber+"$testy.pl";
      driver.findElement(By.name("firstname")).sendKeys("Tomasz");
      driver.findElement(By.name("lastname")).sendKeys("Comasz");
      driver.findElement(By.name("phone")).sendKeys("+48555666777");
      driver.findElement(By.name("email")).sendKeys(email);
      driver.findElement(By.name("password")).sendKeys("Test123");
      driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
      driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

       List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p")).stream()
               .map(WebElement::getText).collect(Collectors.toList());

       Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));




   }


}
