import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
    public void buyProductAsUserTest() {
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
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        checkoutPage.manageDelivery();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        checkoutPage.managePayment();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        checkoutPage.typeAdres("Test");
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        checkoutPage.checkbox();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id=\"ajax_loading_box\"]"))));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        checkoutPage.getOrder();



    }

}
