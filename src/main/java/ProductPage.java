import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addToCartButton = By.xpath("//button[@class=\"ty-btn__primary ty-btn__big ty-btn__add-to-cart cm-form-dialog-closer ty-btn\"]");
    private By goToCheckout = By.xpath("//a[@class=\"ty-btn ty-btn__primary cm-notification-close \"]");
    private By addToWishList = By.xpath("//span[@class=\"ty-icon ty-icon-heart\"]");
    private By ajax = By.xpath("//div[@id=\"ajax_loading_box\"]");
    private By productName = By.xpath("//h1");


    public ProductPage addToCart() {
        driver.findElement(addToCartButton).click();
        return this;
    }

    public ProductPage goToCheckout() {
        driver.findElement(By.xpath("//a[@class=\"ty-btn ty-btn__primary cm-notification-close \"]")).click();
        return this;
    }

    public String addToWish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(addToWishList).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(ajax)));
        String name = driver.findElement(productName).getText();

        return name;
    }


}
