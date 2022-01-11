package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

public class LoggedUserPage {


    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;
    private WebDriver driver;

    public String getHeadingText() {

        SeleniumHelper.waitForElementToBeVisible(driver, heading);
        return heading.getText();
    }

    public LoggedUserPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);


    }
}
