package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookAppTitlesPage extends AbstractPage {

    public BookAppTitlesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[name=\"add-title-button\"]")
    private WebElement addButton;

    @FindBy(css = "input[name=\"title\"]")
    private WebElement titleInput;

    @FindBy(css = "input[name=\"author\"]")
    private WebElement authorInput;

    @FindBy(css = "input[name=\"year\"]")
    private WebElement yearInput;

    @FindBy(css = "button[name=\"submit-button\"]")
    private WebElement submitButton;

    @FindBy(css = "ul[class=\"titles-list list\"]")
    private List<WebElement> listOfTitles;

    @FindBy(css = "button[class=\"remove-btn btn--small btn btn--error\"]")
    private WebElement deleteButton;

    @FindBy(css = "button[class=\"edit-btn btn--small btn btn--warning\"]")
    private WebElement editButton;

    @FindBy(css = "div[class=\"titles-list__item__year list__item__col\"]")
    private WebElement yearColumn;

    @FindBy(css = "div[class=\"titles-list__item__title list__item__col list__item__col--primary\"]")
    private WebElement titleColumn;

    @FindBy(css = "div[class=\"titles-list__item__author list__item__col\"]")
    private WebElement authorColumn;

    @FindBy(css = "div[class=\"alert alert--info\"]")
    private WebElement titleEmptyListAlert;

    @FindBy(css = "div[class=\"alert alert--error\"]")
    private WebElement titleAddingAlert;

    @FindBy(css = "button[class=\"show-copies-btn btn--small btn btn--primary\"]")
    private WebElement showCopiesButton;

    public void setTitleInput(String title) {
        titleInput.sendKeys(title);
    }

    public void setAuthorInput(String author) {
        authorInput.sendKeys(author);
    }

    public void setYearInput(String year) {
        yearInput.sendKeys(year);
    }

    public int countElementsInList() {
        String content = listOfTitles.get(0).getText();
        return content.split("EDIT").length - 1;
    }

    public String titleEmptyListAlert() throws InterruptedException {
        Thread.sleep(1000);
        return titleEmptyListAlert.getText();
    }

    public String titleAddingAlert() throws InterruptedException {
        Thread.sleep(1000);
        return titleAddingAlert.getText();
    }

    public String getYearColumn() throws InterruptedException {
        Thread.sleep(1000);
        return yearColumn.getText();
    }

    public String getTitleColumn() throws InterruptedException {
        Thread.sleep(1000);
        return titleColumn.getText();
    }

    public String getAuthorColumn() throws InterruptedException {
        Thread.sleep(1000);
        return authorColumn.getText();
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getShowCopiesButton() {
        return showCopiesButton;
    }

    public void addTitle(String title, String author, String year) throws InterruptedException {
        Thread.sleep(1000);
        addButton.click();
        setTitleInput(title);
        setAuthorInput(author);
        setYearInput(year);
        submitButton.click();
    }

    public void deleteTitle() {
        deleteButton.click();
    }

    public List<WebElement> getListOfTitles() {
        return listOfTitles;
    }

    private void clearTitleFields() {
        titleInput.clear();
        authorInput.clear();
        yearInput.clear();
    }

    public void editTitle(String title, String author, String year) {
        editButton.click();
        clearTitleFields();
        setTitleInput(title);
        setAuthorInput(author);
        setYearInput(year);
        submitButton.submit();
    }

}
