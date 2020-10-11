package pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BookAppRentsPageTestSuite {

    WebDriver driver;

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, BookAppLoginPage.class);
        PageFactory.initElements(driver, BookAppTitlesPage.class);
        PageFactory.initElements(driver, BookAppItemsPage.class);
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("John", "newPassword");
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.getShowCopiesButton().click();
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        bookAppItemsPage.showRentsButton();
    }

    @After
    public void tearDow() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    //Test if can show empty rents list
    @Test
    public void test1() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        String message = bookAppRentsPage.alertContent();
        Assert.assertEquals("No rents...", message);
    }

    //Test if can add rent without filling "Customer name" field
    @Test
    public void test2() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        bookAppRentsPage.addRent("");
        String message = bookAppRentsPage.getRentAddingAlert();
        Assert.assertEquals("\"customerName\" field shouldn't be empty...", message);
    }

    //Test if can add one new rent
    @Test
    public void test3() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        bookAppRentsPage.addRent("Steve");
        int listOfRents = bookAppRentsPage.countElementsInRentsList();
        Assert.assertEquals(1, listOfRents);
    }

    //Test if can delete rent
    @Test
    public void test4() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        bookAppRentsPage.deleteRent();
        String message = bookAppRentsPage.alertContent();
        Assert.assertEquals("No rents...", message);
    }

    //Test if can add two rents
    @Test
    public void test5() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        bookAppRentsPage.addRent("Steve");
        bookAppRentsPage.addRent("Mike");
        int listOfRents = bookAppRentsPage.countElementsInRentsList();
        Assert.assertEquals(2, listOfRents);
    }

    //Test if can delete one of two existing rents
    @Test
    public void test6() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        bookAppRentsPage.deleteRent();
        int listOfRents = bookAppRentsPage.countElementsInRentsList();
        Assert.assertEquals(1, listOfRents);
    }

    //Test if can edit rent: change rent date, expiration date, and customer name
    @Test
    public void test7() throws InterruptedException {
        BookAppRentsPage bookAppRentsPage = new BookAppRentsPage(driver);
        bookAppRentsPage.editRent("Barbara", "2022", "July", "12", "2021", "August", "15");
        String customerName = bookAppRentsPage.getCustomerName();
        String dateRent = bookAppRentsPage.getRentDateColumn();
        String expirationDate = bookAppRentsPage.getExpirationDateColumn();
        Assert.assertEquals("BARBARA", customerName);
        Assert.assertEquals("2022-07-12", dateRent);
        Assert.assertEquals("(expiration: 2021-08-15)", expirationDate);
    }

}