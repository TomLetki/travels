package pl.seleniumdemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriveFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
        public void setup() throws IOException {
                driver = DriveFactory.getdriver();
               // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
              // przejęte przez SeleniumHelper
                driver.manage().window().maximize();
                driver.get("http://www.kurs-selenium.pl/demo/");
    }
    @AfterMethod
        public void tearDown() throws InterruptedException {
        Thread.sleep(500);
        driver.quit();
    }
}
