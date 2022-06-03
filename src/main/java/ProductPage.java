import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addToCartButton = By.xpath("//button[@class=\"ty-btn__primary ty-btn__big ty-btn__add-to-cart cm-form-dialog-closer ty-btn\"]");
    private By goToCheckout = By.xpath("//a[@class=\"ty-btn ty-btn__primary cm-notification-close \"]");

    public ProductPage addToCart() {
        driver.findElement(addToCartButton).click();
        return this;
    }

    public ProductPage goToCheckout() {
        driver.findElement(By.xpath("//a[@class=\"ty-btn ty-btn__primary cm-notification-close \"]")).click();
        return this;
    }
}
