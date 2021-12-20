package pl.seleniumdemo.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;


public class SignUpTest extends BaseTest {

  @Test
    public void userSignUpTest() {


      HotelSearchPage hotelSearchPage = new  HotelSearchPage(driver);
      hotelSearchPage.openSignUpForm();

       //uzupełnianie danych użytkownika
      String lastname = "Comasz";
      int randomNumber = (int) (Math.random()*1000);
      String email = "Tester"+randomNumber+"@testy.pl";
      driver.findElement(By.name("firstname")).sendKeys("Tomasz");
      driver.findElement(By.name("lastname")).sendKeys("Comasz");
      driver.findElement(By.name("phone")).sendKeys("+48555666777");
      driver.findElement(By.name("email")).sendKeys(email);
      driver.findElement(By.name("password")).sendKeys("Test123");
      driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
      driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

      WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
      Assert.assertTrue(heading.getText().contains(lastname));
      Assert.assertEquals(heading.getText(), "Hi, Tomasz Comasz");


       
   }
    @Test
    public void userSignUpEmptyTest()  {
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

    }
    @Test
    public void userSignUpWrongEmailTest()  {
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
