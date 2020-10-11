package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookAppRentsPage extends AbstractPage {

    public BookAppRentsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "p[class=\"alert__content\"]")
    private WebElement alertContent;

    @FindBy(css = "button[name=\"add-rent-button\"]")
    private WebElement addRentButton;

    @FindBy(css = "input[name=\"customer-name\"]")
    private WebElement customerNameField;

    @FindBy(css = "button[name=\"submit-button\"]")
    private WebElement submitButton;

    @FindBy(css = "div[class=\"alert alert--error\"]")
    private WebElement rentAddingAlert;

    @FindBy(css = "ul[class=\"rents-list list\"]")
    private List<WebElement> rentsList;

    @FindBy(css = "button[class=\"remove-btn btn--small btn btn--error\"]")
    private WebElement removeRentButton;

    @FindBy(css = "button[class=\"edit-btn btn--small btn btn--warning\"]")
    private WebElement editRentButton;

    @FindBy(css = "input[name=\"rent-date\"]")
    private WebElement rentDatePicker;

    @FindBy(css = "input[name=\"expiration-date\"]")
    private WebElement expirationDatePicker;

    @FindBy(css = "div[class=\"vdp-datepicker__calendar\"]")
    private WebElement datePickerCalendar;

    @FindBy(css = "div[class=\"rents-list__rent__customer-name list__item__col list__item__col--primary\"]")
    private WebElement customerName;

    @FindBy(xpath = "/html/body/div/div/div/ul/li/div[1]/div[2]")
    private WebElement rentDateColumn; //*[@id="rent-1011"]/div[1]/div[2]

    @FindBy(xpath = "/html/body/div/div/div/ul/li/div[1]/div[3]")
    private WebElement expirationDateColumn; //*[@id="rent-1011"]/div[1]/div[3]

    public String alertContent() throws InterruptedException {
        Thread.sleep(1000);
        return alertContent.getText();
    }

    public void addRent(String name) throws InterruptedException {
        Thread.sleep(1000);
        addRentButton.click();
        customerNameField.sendKeys(name);
        submitButton.click();
    }

    public String getRentAddingAlert() {
        return rentAddingAlert.getText();
    }

    public int countElementsInRentsList() throws InterruptedException {
        Thread.sleep(1000);
        String content = rentsList.get(0).getText();
        return content.split("EDIT").length - 1;
    }

    public void deleteRent() throws InterruptedException {
        Thread.sleep(1000);
        removeRentButton.click();
    }

    private void clearCustomerNameField() {
        customerNameField.clear();
    }

    public String getCustomerName() throws InterruptedException {
        Thread.sleep(1000);
        return customerName.getText();
    }

    public String getRentDateColumn() throws InterruptedException {
        Thread.sleep(1000);
        return rentDateColumn.getText();
    }

    public String getExpirationDateColumn() throws InterruptedException {
        Thread.sleep(1000);
        return expirationDateColumn.getText();
    }

    private void rentDateSetter(String rentYear, String rentMonth, String rentDay) {
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[2]/div/div/div[2]/header/span[2]")).click();
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[2]/div/div/div[3]/header/span[2]")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'cell year') and text()='" + rentYear + "']")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'cell month') and text()='" + rentMonth + "']")).click();
        datePickerCalendar.findElement(By.xpath("//span[contains(@class, 'cell day') and text()='" + rentDay + "']")).click();
    }

    private void expirationDateSetter(String expirationYear, String expirationMonth, String expirationDay) {
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[3]/div/div/div[2]/header/span[2]")).click();
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[3]/div/div/div[3]/header/span[2]")).click();
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[3]/div/div/div[4]/span[contains(@class, 'cell year') and text()='" + expirationYear + "']")).click();
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[3]/div/div/div[3]/span[contains(@class, 'cell month') and text()='" + expirationMonth + "']")).click();
        datePickerCalendar.findElement(By.xpath("//*[@id=\"rents\"]/div[2]/div/form/div[3]/div/div/div[2]/div/span[contains(@class, 'cell day') and text()='" + expirationDay + "']")).click();
    }

    public void editRent(String name, String rentYear, String rentMonth, String rentDay, String expirationYear, String expirationMonth, String expirationDay) throws InterruptedException {
        Thread.sleep(1000);
        editRentButton.click();
        clearCustomerNameField();
        customerNameField.sendKeys(name);
        rentDatePicker.click();
        Thread.sleep(1000);
        rentDateSetter(rentYear, rentMonth, rentDay);
        Thread.sleep(1000);
        expirationDatePicker.click();
        Thread.sleep(1000);
        expirationDateSetter(expirationYear, expirationMonth, expirationDay);
        submitButton.click();
    }

}

