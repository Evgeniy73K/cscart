import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath("//input[@id=\"login_main_login\"]");
    private By passwordField = By.xpath("//input[@id=\"psw_main_login\"]");
    private By errorEmail = By.xpath("//span[@id=\"login_main_login_error_message\"]");
    private By errorPass = By.xpath("//span[@id=\"psw_main_login_error_message\"]");
    private By button = By.xpath("//div[@class=\"span8  main-content-grid\"]//form//button");
    private By alertError = By.xpath("//div[@class=\"cm-notification-content notification-content alert-error\"]");

    public LoginPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage typePass(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
        return this;
    }

    public Boolean errorEmail() {
        Boolean error = driver.findElement(errorEmail).isEnabled();
        return error;
    }

    public Boolean errorPass() {
        Boolean error = driver.findElement(errorPass).isEnabled();
        return error;
    }

    public Boolean getErrorAlert() {
        Boolean error = driver.findElement(alertError).isEnabled();
        return error;
    }

    public void login() {
        driver.findElement(button).click();
    }


}
