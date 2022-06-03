import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By manageDelivery = By.xpath("//span[@class=\"ty-one-store__name-text\"]");
    private By adresField = By.xpath("//input[@id=\"litecheckout_s_address\"]");
    private By payment = By.xpath("//label[@id=\"payments_2\"]");
    private By checkbox = By.xpath("//input[@name=\"accept_terms\"]");
    private By orderButton = By.xpath("//button[@id=\"litecheckout_place_order\"]");

    public CheckoutPage manageDelivery() {
        driver.findElement(manageDelivery).click();
        return this;
    }

    public CheckoutPage typeAdres(String value) {
        driver.findElement(adresField).sendKeys(value);
        return this;
    }

    public CheckoutPage managePayment() {
        driver.findElement(payment).click();
        return this;
    }

    public CheckoutPage checkbox() {
        driver.findElement(checkbox).click();
        return this;
    }

    public CheckoutPage getOrder() {
        driver.findElement(orderButton).click();
        return this;
    }

}
