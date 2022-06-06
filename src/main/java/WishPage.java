import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishPage {
    private WebDriver driver;

    public WishPage(WebDriver driver) {
        this.driver = driver;
    }

    private By element = By.xpath("//a[@class=\"product-title\"]");
    private By deleteElement = By.xpath("//a[@class=\"ty-twishlist-item__remove ty-remove\"]");

    public String getName() {
        String name = driver.findElement(element).getText();
        return name;
    }

    public Boolean delete() {
        driver.findElement(deleteElement).click();
        List<WebElement> elements = driver.findElements(element);
        if(elements.size()<1) {
            return true;
        } else {
            return false;
        }
    }



}
