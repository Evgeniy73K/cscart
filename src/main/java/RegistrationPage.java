import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameField = By.xpath("//input[@id=\"elm_6\"]");
    private By surNameField = By.xpath("//input[@id=\"elm_7\"]");
    private By phoneField = By.xpath("//input[@id=\"elm_9\"]");
    private By emailField = By.xpath("//input[@id=\"email\"]");
    private By password1Field = By.xpath("//input[@id=\"password1\"]");
    private By password2Field = By.xpath("//input[@id=\"password2\"]");
    private By registerButton = By.xpath("//button[@class=\"ty-btn__secondary ty-btn\"]");
    private By emailError = By.xpath("//span[@id=\"email_error_message\"]");
    private By password1Error = By.xpath("//span[@id=\"password1_error_message\"]");
    private By password2Error = By.xpath("//span[@id=\"password2_error_message\"]");
    private By errorPhone = By.xpath("//span[@id=\"elm_9_error_message\"]");
    private By alertError = By.xpath("//div[@class=\"cm-notification-content notification-content alert-error\"]");

    public RegistrationPage typeName(String name) {
        driver.findElement(firstNameField).sendKeys(name);
        return this;
    }

    public  RegistrationPage typeSurname(String surname) {
        driver.findElement(surNameField).sendKeys(surname);
        return this;
    }

    public RegistrationPage typePhone(String number) {
        driver.findElement(phoneField).sendKeys(number);
        driver.findElement(phoneField).click();
        return this;
    }

    public  RegistrationPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public  RegistrationPage typePass1(String pass) {
        driver.findElement(password1Field).sendKeys(pass);
        return this;
    }

    public RegistrationPage typePass2(String pass) {
        driver.findElement(password2Field).sendKeys(pass);
        return this;
    }

    public  RegistrationPage register() {
        driver.findElement(registerButton).click();
        return this;
    }

    public String getErrorEmail() {
        String error = driver.findElement(emailError).getText();
        return error;
    }

    public String getPhoneError() {
        String error = driver.findElement(errorPhone).getText();
        return error;
    }

    public String getPass1Error() {
        String error = driver.findElement(password1Error).getText();
        return error;
    }

    public String getPass2Error() {
        String error = driver.findElement(password2Error).getText();
        return error;
    }

    public String getErrorAlert() {
        String error = driver.findElement(alertError).getText();
        return error;
    }


}
