package pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BookAppTitlesTestSuite {

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
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    //Test if can show empty list of titles
    @Test
    public void test1() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        String message = bookAppTitlesPage.titleEmptyListAlert();
        Assert.assertEquals("No titles", message);
    }

    //Test if can add one title without filling "Title" field
    @Test
    public void test2() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.addTitle("", "George Orwell", "1949");
        String message = bookAppTitlesPage.titleAddingAlert();
        Assert.assertEquals("\"title\" field shouldn't be empty...", message);
    }

    //Test if can add one title without filling "Author" field
    @Test
    public void test3() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.addTitle("Nineteen Eighty-Four", "", "1949");
        String message = bookAppTitlesPage.titleAddingAlert();
        Assert.assertEquals("\"author\" field shouldn't be empty...", message);
    }

    //Test if can add one title without filling "Year" field
    @Test
    public void test4() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.addTitle("Nineteen Eighty-Four", "George Orwell", "");
        String message = bookAppTitlesPage.titleAddingAlert();
        Assert.assertEquals("\"year\" field shouldn't be empty...", message);
    }

    //Test if can add one title, and delete it after
    @Test
    public void test5() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.addTitle("Nineteen Eighty-Four", "George Orwell", "1949");
        int listOfTitlesSize = bookAppTitlesPage.getListOfTitles().size();
        Assert.assertEquals(1, listOfTitlesSize);
        bookAppTitlesPage.deleteTitle();
    }

    //Test if can add two titles and delete first of them
    @Test
    public void test6() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.addTitle("Nineteen Eighty-Four", "George Orwell", "1949");
        bookAppTitlesPage.addTitle("The Lord of the Rings", "J. R. R. Tolkien", "1949");
        wait.until(ExpectedConditions.visibilityOf(bookAppTitlesPage.getListOfTitles().get(0))).isDisplayed();
        int numberOfTitles = bookAppTitlesPage.countElementsInList();
        Assert.assertEquals(2, numberOfTitles);
        bookAppTitlesPage.deleteTitle();
    }

    //Test if can edit title and change "year" field
    @Test
    public void test7() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.editTitle("The Lord of the Rings", "J. R. R. Tolkien", "1965");
        String result = bookAppTitlesPage.getYearColumn();
        Assert.assertEquals("(1965)", result);
    }

    //Test if can edit title and change "title" field
    @Test
    public void test8() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.editTitle("Two towers", "J. R. R. Tolkien", "1965");
        String result = bookAppTitlesPage.getTitleColumn();
        Assert.assertEquals("TWO TOWERS", result);
    }

    //Test if can edit title and change "author" field
    @Test
    public void test9() throws InterruptedException {
        BookAppTitlesPage bookAppTitlesPage = new BookAppTitlesPage(driver);
        bookAppTitlesPage.editTitle("Two towers", "unknown", "1965");
        String result = bookAppTitlesPage.getAuthorColumn();
        Assert.assertEquals("by unknown", result);
    }

}
