package pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BookAppItemsTestSuite {

    WebDriver driver;

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, BookAppLoginPage.class);
        PageFactory.initElements(driver, BookAppTitlesPage.class);
        BookAppLoginPage bookAppLoginPage = new BookAppLoginPage(driver);
        bookAppLoginPage.loginRegisteredUser("John", "newPassword");
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.getShowCopiesButton().click();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    //Test if can add copy
    @Test
    public void test1() throws InterruptedException {
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        bookAppItemsPage.addItem("2020", "July", "11");
        String message = bookAppItemsPage.getItemAvailability();
        Assert.assertEquals("Available", message);
    }

    //Test if can edit copy
    @Test
    public void test2() throws InterruptedException {
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        bookAppItemsPage.editDate("2024", "March", "13");
        String purchaseDate = bookAppItemsPage.getPurchaseDate();
        Assert.assertEquals("2024-03-13", purchaseDate);
    }

    //Test if can delete copy
    @Test
    public void test3() throws InterruptedException {
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        bookAppItemsPage.deleteItem();
        String message = bookAppItemsPage.getAlertMessage();
        Assert.assertEquals("No copies...", message);
    }

    //Test if can add two copies
    @Test
    public void test4() throws InterruptedException {
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        bookAppItemsPage.addItem("2023", "July", "11");
        bookAppItemsPage.addItem("2021", "December", "9");
        int numberOfItems = bookAppItemsPage.countElementsInList();
        Assert.assertEquals(2, numberOfItems);
    }

    //Test if can delete two items from list
    @Test
    public void test5() throws InterruptedException {
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        bookAppItemsPage.deleteItem();
        bookAppItemsPage.deleteItem();
        String message = bookAppItemsPage.getAlertMessage();
        Assert.assertEquals("No copies...", message);
    }

    //Test if can add one item and get back to titles list
    @Test
    public void test6() throws InterruptedException {
        BookAppItemsPage bookAppItemsPage = new BookAppItemsPage(driver);
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppItemsPage.addItem("2021", "December", "11");
        bookAppItemsPage.returnButton();
        Boolean result = bookAppTitlesPage.getListOfTitles().isEmpty();
        Assert.assertEquals(false, result);
    }

}
