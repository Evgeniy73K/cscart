import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userProfile = By.xpath("//a[text()=\"Учетная запись\"]");
    private By search = By.xpath("//input[@id=\"search_input\"]");

    public Boolean checkLogin() {
        Boolean check = driver.findElement(userProfile).isEnabled();
        return check;

    }

    public MainPage search(String value) {
        driver.findElement(search).sendKeys(value);
        driver.findElement(search).submit();
        return this;
    }
}
