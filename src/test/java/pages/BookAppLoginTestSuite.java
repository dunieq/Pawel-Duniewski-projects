package pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BookAppLoginTestSuite {

    WebDriver driver;

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        PageFactory.initElements(driver, BookAppTitlesPage.class);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    //Test if can log in with unfilled login and password field
    @Test
    public void test1() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("", "");
        String message = bookAppLoginPage.getLogInAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can log in with filled password field and unfilled login field
    @Test
    public void test2() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("", "newPassword");
        String message = bookAppLoginPage.getLogInAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can log in with filled login field and unfilled password field
    @Test
    public void test3() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("John", "");
        String message = bookAppLoginPage.getLogInAlert();
        Assert.assertEquals("You can't leave fields empty", message);
    }

    //Test if can log in user with incorrect login and correct password
    @Test
    public void test4() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("Mary", "newPassword");
        String message = bookAppLoginPage.getLogInAlert();
        Assert.assertEquals("Login failed", message);
    }

    //Test if can log in user with correct login and incorrect password
    @Test
    public void test5() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("John", "oldPassword");
        String message = bookAppLoginPage.getLogInAlert();
        Assert.assertEquals("Login failed", message);
    }

    //Test if can log in user with incorrect login and password
    @Test
    public void test6() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("Mary", "oldPassword");
        String message = bookAppLoginPage.getLogInAlert();
        Assert.assertEquals("Login failed", message);
    }

    //Test if registered user can log in with correct login and password
    @Test
    public void test7() throws InterruptedException {
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppLoginPage.loginRegisteredUser("John", "newPassword");
        String message = bookAppTitlesPage.titleEmptyListAlert();
        Assert.assertEquals("No titles", message);
    }

}
