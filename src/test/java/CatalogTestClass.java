import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.addAttachment;

public class CatalogTestClass {
    private WebDriver driver;
    private MainPage mainPage;
    private SearchPage searchPage;
    private CatalogPage catalogPage;
    private ProductPage productPage;
    private WishPage wishPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/cscart/");
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Test
    public void perToPageTest() {
        mainPage.search("");
        for(int i = 96; i >= 12; i = i/2) {
            Assert.assertTrue(searchPage.paginationStepCheck(i));
        }

    }

    @Test
    public void sortPriceByDescTest(){
        catalogPage = new CatalogPage(driver);
        driver.navigate().to("http://localhost/cscart/elektronika/kompyutery/");
        catalogPage.sortPriceByDesc();
        Assert.assertTrue(catalogPage.getPriceListDesc());
    }

    @Test
    public void sortPriceByAscTest(){
        catalogPage = new CatalogPage(driver);
        driver.navigate().to("http://localhost/cscart/elektronika/kompyutery/");
        catalogPage.sortPriceByAsc();
        Assert.assertTrue(catalogPage.getPriceListAsc());
    }

    @Test
    public void filterMinPriceTest() throws InterruptedException {
        catalogPage = new CatalogPage(driver);
        driver.navigate().to("http://localhost/cscart/elektronika/kompyutery/");
        Assert.assertTrue(catalogPage.setMinPrice());


    }

    @Test
    public void AddToWishListTest() {
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        wishPage = new WishPage(driver);
        mainPage.search("");
        searchPage.clickProduct();
        String value1 = productPage.addToWish();
        driver.navigate().to("http://localhost/cscart/wishlist/");
        Assert.assertTrue(value1.contains(wishPage.getName()));




    }

    @Test
    public void deleteFromWishListTest() {
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        wishPage = new WishPage(driver);
        mainPage.search("");
        searchPage.clickProduct();
        String value1 = productPage.addToWish();
        driver.navigate().to("http://localhost/cscart/wishlist/");
        Assert.assertTrue(value1.contains(wishPage.getName()));
        Assert.assertTrue(wishPage.delete());




    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (! result.isSuccess()) {
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
            String name = format.format(date)+".png";

            try {
                FileUtils.copyFile(screen,new File("./Screenshots/"+name));
                addAttachment("Screenshot", FileUtils.openInputStream(screen));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

       //driver.quit();
    }


}
