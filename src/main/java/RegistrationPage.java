import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    
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

    public RegistrationPage open() {
        Selenide.open("/");
        return this;
    }

    public RegistrationPage typeName(String name) {
        $(firstNameField).setValue(name);
        return this;
    }

    public  RegistrationPage typeSurname(String surname) {
        $(surNameField).setValue(surname);
        return this;
    }

    public RegistrationPage typePhone(String number) {
        $(phoneField).setValue(number);
        $(phoneField).click();
        return this;
    }

    public  RegistrationPage typeEmail(String email) {
        $(emailField).setValue(email);
        return this;
    }

    public  RegistrationPage typePass1(String pass) {
        $(password1Field).setValue(pass);
        return this;
    }

    public RegistrationPage typePass2(String pass) {
        $(password2Field).setValue(pass);
        return this;
    }

    public  RegistrationPage register() {
        $(registerButton).click();
        return this;
    }

    public SelenideElement getErrorEmail() {
        SelenideElement error = $(emailError);
        return error;
    }

    public SelenideElement getPhoneError() {
        SelenideElement error = $(errorPhone);
        return error;
    }

    public SelenideElement getPass1Error() {
        SelenideElement error = $(password1Error);
        return error;
    }

    public SelenideElement getPass2Error() {
        SelenideElement error = $(password2Error);
        return error;
    }

    public SelenideElement getErrorAlert() {

        SelenideElement error = $(alertError);
        return error;
    }


}
