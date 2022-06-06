import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogPage {
    private WebDriver driver;
    private boolean result;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    private By sortSelector = By.xpath("//a[@id=\"sw_elm_sort_fields\"]");
    private By priceByDesc = By.xpath("//li[@class=\"sort-by-price-desc ty-sort-dropdown__content-item\"]");
    private By priceByAsc = By.xpath("//li[@class=\"sort-by-price-asc ty-sort-dropdown__content-item\"]");
    private By price = By.xpath("//span[@class=\"ty-price-num\"]");
    private By filterPrice1 = By.xpath("//input[@id=\"slider_32_1_left\"]");
    private By filterPrice2 = By.xpath("//input[@id=\"slider_32_1_right\"]");
    private By ajax = By.xpath("//div[@id=\"ajax_loading_box\"]");

    public CatalogPage sortPriceByDesc() {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(sortSelector).click();
        driver.findElement(priceByDesc).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(ajax)));
        return this;
    }

    public CatalogPage sortPriceByAsc() {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(sortSelector).click();
        driver.findElement(priceByAsc).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(ajax)));
        return this;
    }

    public boolean getPriceListDesc() {

        List<WebElement> prices = driver.findElements(price);
        List<String> test = new ArrayList<>();

        for (int i = 0, k = 0; i < prices.size(); i = i +2, k++) {
            test.add(k,prices.get(i).getText().replace(" ",""));

        }

        List<Float> floats = test.stream().map(Float::valueOf).collect(Collectors.toList());
        List<Float> sortedList = floats.stream().sorted().collect(Collectors.toList());

        Collections.reverse(sortedList);

        if (sortedList.equals(floats)) {
            return true;
        } else {
            return false;
        }
}

    public boolean getPriceListAsc() {

        List<WebElement> prices = driver.findElements(price);
        List<String> test = new ArrayList<>();

        for (int i = 0, k = 0; i < prices.size(); i = i +2, k++) {
            test.add(k,prices.get(i).getText().replace(" ",""));

        }

        List<Float> floats = test.stream().map(Float::valueOf).collect(Collectors.toList());
        List<Float> sortedList = floats.stream().sorted().collect(Collectors.toList());

        if (sortedList.equals(floats)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean setMinPrice() throws InterruptedException {
        Boolean result = null;
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(filterPrice2).clear();
        Thread.sleep(5000);
        String minValue = driver.findElement(filterPrice1).getAttribute("value");
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(ajax)));
        List<WebElement> prices = driver.findElements(price);
        List<String> test = new ArrayList<>();
        Thread.sleep(5000);
        for (int i = 0, k = 0; i < prices.size(); i = i +2, k++) {
            test.add(k,prices.get(i).getText().replace(" ",""));
            if (test.get(i).contains(minValue)) {
                result = true;
            } else {
                result = false;
            }


        }





        return result;




    }
}