package pl.seleniumdemo.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;


public class SignUpTest extends BaseTest {

    @Test
    public void userSignUpTest() {


        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        String lastname = "Comasz";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "Tester" + randomNumber + "@testy.pl";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Tomasz");
        signUpPage.setLastName(lastname);
        signUpPage.setPhone("123123123");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        signUpPage.signup();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Tomasz Comasz");


    }

    @Test
    public void userSignUpEmptyTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signup();
        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

    }

    @Test
    public void userSignUpWrongEmailTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Tomasz");
        signUpPage.setLastName("Comasz");
        signUpPage.setPhone("123123123");
        signUpPage.setEmail("email");
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        signUpPage.signup();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }


}
