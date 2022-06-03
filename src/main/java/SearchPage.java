import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    private By product = By.xpath("//a[@class=\"product-title\"]");

    public SearchPage clickProduct() {
        driver.findElement(product).click();
        return this;
    }
}
