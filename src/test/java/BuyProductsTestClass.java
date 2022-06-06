import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.addAttachment;

public class BuyProductsTestClass {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/cscart/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void buyProductAsUserTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("http://localhost/cscart/login/");
        loginPage
                .typeEmail("test@test.ru")
                .typePass("12345")
                .login();
        mainPage.search("");
        searchPage.clickProduct();
        productPage.addToCart();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        productPage.goToCheckout();
        checkoutPage.manageDelivery();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        Thread.sleep(5000);
        checkoutPage.managePayment();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        Thread.sleep(5000);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        checkoutPage.checkbox();
        checkoutPage.getOrder();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout.complete"));



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

        driver.quit();
    }


}
