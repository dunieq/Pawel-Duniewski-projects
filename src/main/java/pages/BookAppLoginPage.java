package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BookAppLoginPage extends AbstractPage {

    WebDriver driver;

    @FindBy(css = "button[id=\"register-btn\"]")
    private WebElement registerButton;

    @FindBy(css = "button[id=\"login-btn\"]")
    private WebElement loginButton;

    @FindBy(css = "input[name=\"login\"]")
    private WebElement loginInput;

    @FindBy(css = "input[name=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css = "div[class=\"alert alert--error\"]")
    private WebElement logInAlert;

    public BookAppLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLogInAlert() throws InterruptedException {
        Thread.sleep(1000);
        return logInAlert.getText();
    }

    public void redirectToRegistrationPage() {
        PageFactory.initElements(driver, BookAppRegistrationPage.class);
        bookAppRegistrationPage(driver);
        registerButton.click();
    }

    public BookAppRegistrationPage bookAppRegistrationPage(WebDriver driver) {
        return new BookAppRegistrationPage(driver);
    }

    private void setLogin(String login) {
        loginInput.sendKeys(login);
    }

    private void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void loginRegisteredUser(String login, String password) {
        setLogin(login);
        setPassword(password);
        loginButton.click();
    }

}
