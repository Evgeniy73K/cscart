import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class LoginPageTestClass {
    private WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/cscart/login/");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 0)
    public void validLogin() {
        MainPage mainPage;
        mainPage = new MainPage(driver);

        loginPage
                .typeEmail("test@test.ru")
                .typePass("12345")
                .login();

        Assert.assertTrue(mainPage.checkLogin());

    }

    @Test(priority = 1)
    public void invalidLogin() {
        loginPage
                .typeEmail("net@test.ru")
                .typePass("12345")
                .login();

        Assert.assertTrue(loginPage.getErrorAlert());

    }

    @Test(priority = 1)
    public void emptyFields() {
        loginPage
                .typeEmail("")
                .typePass("")
                .login();

        Assert.assertTrue(loginPage.errorEmail() && loginPage.errorPass());

    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (! result.isSuccess()) {
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
            String name = format.format(date)+".png";

            try {
                FileUtils.copyFile(screen,new File("C:/Screen"+name));
                addAttachment("Screenshot", FileUtils.openInputStream(screen));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        driver.quit();
    }
}
