package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void  SignUpTest2() {

        int randomNumbers = (int) (Math.random() * 1000);
        String email = "tester" + randomNumbers + "@gmail.com";

        User user=new User();
        user.setFirstName("Wojciech");
        user.setLastName("Rachoń");
        user.setPhone("111111111");
        user.setEmail(email);
        user.setPassword("Test123");



        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        //signUpPage.fillSignUpForm("Wojciech","Rachoń","111111111",email,"Test123");
        signUpPage.fillSignUpForm(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Wojciech Rachoń");
    }
    @Test
    public void signUpEmptyFormTest() {
        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage =new SignUpPage(driver);
        signUpPage.clickSignUp();
        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }
    @Test
    public  void signInvalidEmail(){

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Wojciech");
        signUpPage.setLastName("Rachoń");
        signUpPage.setPhone("111111111");
        signUpPage.setEmail("email");
        signUpPage.setPassword("Test123");
        signUpPage.confirmPassword("Test123");
        signUpPage.clickSignUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
}

