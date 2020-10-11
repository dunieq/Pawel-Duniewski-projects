package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookAppRegistrationPage extends AbstractPage{

    @FindBy(css = "input[name=\"login\"]")
    private WebElement loginInput;

    @FindBy(css = "input[name=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css = "input[name=\"password-repeat\"]")
    private WebElement passwordRepeatInput;

    @FindBy(css = "button[id=\"register-btn\"]")
    private WebElement registerButton;

    @FindBy(css = "p[class=\"alert__content\"]")
    private WebElement registrationAlert;

    @FindBy(css = "h2[class=\"sub-title\"]")
    private WebElement signUpHeader;

    public BookAppRegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String registrationAlert() throws InterruptedException {
        Thread.sleep(1000);
        return registrationAlert.getText();
    }

    private void setLogin(String login) {
       loginInput.sendKeys(login);
    }

    private void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    private void setPasswordRepeat(String passwordRepeat) {
        passwordRepeatInput.sendKeys(passwordRepeat);
    }

    private void clickRegister() {
        registerButton.click();
    }

    public String signUpHeader() {
        return signUpHeader.getText();
    }

    public void registerNewUser(String login, String password, String passwordRepeat) throws InterruptedException {
        Thread.sleep(1000);
        setLogin(login);
        Thread.sleep(1000);
        setPassword(password);
        Thread.sleep(1000);
        setPasswordRepeat(passwordRepeat);
        Thread.sleep(1000);
        clickRegister();
    }

}
