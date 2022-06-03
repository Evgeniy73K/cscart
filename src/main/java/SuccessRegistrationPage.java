import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessRegistrationPage {
    WebDriver driver;

    public SuccessRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    By head = By.xpath("//h1[@class=\"ty-mainbox-title\"]");

    public String getH1() {
        String h1 = driver.findElement(head).getText();
        return h1;
    }
}
