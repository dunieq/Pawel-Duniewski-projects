package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookAppItemsPage extends AbstractPage {

    public BookAppItemsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[name=\"add-item-button\"]")
    private WebElement addNewItemButton;

    @FindBy(css = "button[name=\"submit-button\"]")
    private WebElement addCopyButton;

    @FindBy(css = "button[class=\"edit-btn btn--small btn btn--warning\"]")
    private WebElement editButton;

    @FindBy(css = "button[class=\"remove-btn btn--small btn btn--error\"]")
    private WebElement deleteButton;

    @FindBy(css = "button[class=\"show-rents-btn btn--small btn btn--primary\"]")
    private WebElement showRentsButton;

    @FindBy(css = "input[name=\"purchase-date\"]")
    private WebElement datePicker;

    @FindBy(css = "span[class=\"prev\"]") //*[@id="title-copies"]/div[2]/div/form/div/div/div/div[2]/header/span[1]
    private WebElement previousButton;

    @FindBy(css = "span[class=\"next\"]")
    private WebElement nextButton;

    @FindBy(css = "ul[class=\"items-list list\"]")
    private List<WebElement> listOfItems;

    @FindBy(css = "div[class=\"vdp-datepicker__calendar\"]")
    private WebElement datePickerCalendar;

    @FindBy(css = "div[class=\"items-list__item__status list__item__col items-list__item__status--available\"]")
    private WebElement itemAvailability;

    @FindBy(css = "div[class=\"items-list__item__purchase-date list__item__col list__item__col--primary\"]")
    private WebElement purchaseDate;

    @FindBy(css = "div[class=\"alert alert--info\"]")
    private WebElement alertMessage;

    @FindBy(css = "button[id=\"return-button\"]")
    private WebElement returnButton;


    public void setDate(String year, String month, String day) throws InterruptedException {
        Thread.sleep(1000);
        dateSetter(year, month, day);
    }

    public void addItem(String year, String month, String day) throws InterruptedException {
        Thread.sleep(1000);
        addNewItemButton.click();
        Thread.sleep(1000);
        datePicker.click();
        setDate(year, month, day);
    }

    public void editDate(String year, String month, String day) throws InterruptedException {
        Thread.sleep(1000);
        editButton.click();
        Thread.sleep(1000);
        datePicker.click();
        dateSetter(year, month, day);
    }

    private void dateSetter(String year, String month, String day) {
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'day__month_btn up')]")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'month__year_btn up')]")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'cell year') and text()='" + year + "']")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'cell month') and text()='" + month + "']")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'cell day') and text()='" + day + "']")).click();
        addCopyButton.click();
    }

    public void deleteItem() throws InterruptedException {
        Thread.sleep(1000);
        deleteButton.click();
    }

    public void returnButton() {
        returnButton.click();
    }

    public String getItemAvailability() {
        return itemAvailability.getText();
    }

    public int countElementsInList() throws InterruptedException {
        Thread.sleep(1000);
        String content = listOfItems.get(0).getText();
        return content.split("EDIT").length - 1;
    }

    public String getPurchaseDate() throws InterruptedException {
        Thread.sleep(1000);
        return purchaseDate.getText();
    }

    public String getAlertMessage() {
        return alertMessage.getText();
    }

    public void showRentsButton() {
        showRentsButton.click();
    }

    public WebElement addButton() {
        return addNewItemButton;
    }

    public WebElement datePicker() {
        return datePicker;
    }

    public WebElement getPreviousMonth() {
        return previousButton;
    }

    public WebElement getNextMonth() {
        return nextButton;
    }

    public WebElement editButton() {
        return editButton;
    }
}
