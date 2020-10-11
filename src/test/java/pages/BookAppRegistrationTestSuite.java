package pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BookAppRegistrationTestSuite {

    WebDriver driver;

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, BookAppLoginPage.class);
        PageFactory.initElements(driver, BookAppRegistrationPage.class);
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.redirectToRegistrationPage();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    //Test if redirection from login page to registration page works correctly
    @Test
    public void test1() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        Thread.sleep(1000);
        String message = bookAppRegistrationPage.signUpHeader();
        Assert.assertEquals("SIGN UP", message);
    }

    //Test if can register existing user
    @Test
    public void test2() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        bookAppRegistrationPage.registerNewUser("Man", "newPassword", "newPassword");
        String message = bookAppRegistrationPage.registrationAlert();
        Assert.assertEquals("This user already exist!", message);
    }

    //Test if can register with empty "login" field
    @Test
    public void test3() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        bookAppRegistrationPage.registerNewUser("", "newPassword", "newPassword");
        String message = bookAppRegistrationPage.registrationAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can register with empty "password" field
    @Test
    public void test4() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        bookAppRegistrationPage.registerNewUser("John", "", "newPassword");
        String message = bookAppRegistrationPage.registrationAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can register with empty "repeat password" field
    @Test
    public void test5() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        bookAppRegistrationPage.registerNewUser("John", "newPassword", "");
        String message = bookAppRegistrationPage.registrationAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can register with all empty fields
    @Test
    public void test6() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        bookAppRegistrationPage.registerNewUser("", "", "");
        String message = bookAppRegistrationPage.registrationAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can register new user
    @Test
    public void test7() throws InterruptedException {
        BookAppRegistrationPage bookAppRegistrationPage = new BookAppRegistrationPage(driver);
        bookAppRegistrationPage.registerNewUser("John", "newPassword", "newPassword");
        String message = bookAppRegistrationPage.registrationAlert();
        Assert.assertEquals("You have been successfully registered!", message);
    }

}
