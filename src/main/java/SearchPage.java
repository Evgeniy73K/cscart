import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    private By product = By.xpath("//a[@class=\"product-title\"]");
    private By steps = By.xpath("//a[@id=\"sw_elm_pagination_steps\"]");



    public SearchPage clickProduct() {
        driver.findElement(product).click();
        return this;
    }

    public Boolean paginationStepCheck(int value){
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(steps).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+value+"')]")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ul[@id=\"elm_pagination_steps\"]"))));
        if(driver.findElements(product).size() == value) {
            return true;
        }
        else {
            return false;
        }

    }


}
