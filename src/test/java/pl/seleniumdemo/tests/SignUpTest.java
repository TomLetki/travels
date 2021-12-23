package pl.seleniumdemo.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;


public class SignUpTest extends BaseTest {

    @Test
    public void userSignUpTest() {


        String lastname = "Comasz";
        int randomNumber = (int) (Math.random() * 1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Tomasz")
                .setLastName(lastname)
                .setPhone("123123123")
                .setEmail("Tester" + randomNumber + "@testy.pl")
                .setPassword("Test123")
                .setConfirmPassword("Test123")
                .signup();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Tomasz Comasz");


    }

    @Test
    public void userSignUpEmptyTest() {


        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
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

        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Tomasz")
                .setLastName("Comasz")
                .setPhone("123123123")
                .setEmail("email")
                .setPassword("Test123")
                .setConfirmPassword("Test123");
        signUpPage.signup();


        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }


}
