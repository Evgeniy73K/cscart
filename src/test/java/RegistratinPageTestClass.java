import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.addAttachment;

public class RegistratinPageTestClass {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    SuccessRegistrationPage successRegistrationPage;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/cscart/profiles-add/");
    }

    @Test(priority = 0)
    public void validRegisterTest() {
        registrationPage = new RegistrationPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        registrationPage
                .typeName("Test")
                .typeSurname("Test")
                .typeEmail("Tes32t1@test.ru")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(successRegistrationPage.getH1().contains("Вы успешно зарегистрированы"));
    }

    @Test(priority = 1)
    public void existUserRegisterTest() {
        registrationPage = new RegistrationPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        registrationPage
                .typeName("Test")
                .typeSurname("Test")
                .typeEmail("Test@test.ru")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(registrationPage.getErrorAlert().contains("Ошибка Такое имя пользователя или email уже существуют. Пожалуйста, попробуйте другой вариант."));

    }

    @Test(priority = 1)
    public void emptyFieldsTest() {
        registrationPage = new RegistrationPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        registrationPage
                .typeName("")
                .typeSurname("")
                .typeEmail("")
                .typePhone("")
                .typePass1("")
                .typePass2("")
                .register();
        Assert.assertTrue(registrationPage.getErrorEmail().contains("Поле E-mail обязательное."));
        Assert.assertTrue(registrationPage.getPass1Error().contains("Поле Пароль обязательное."));
        Assert.assertTrue(registrationPage.getPass2Error().contains("Поле Подтверждение пароля обязательное."));

    }

    @Test(priority = 1)
    public void incorrectPasswordTest() {
        registrationPage = new RegistrationPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        registrationPage
                .typeName("")
                .typeSurname("")
                .typeEmail("pass@test.ru")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12341")
                .register();
        Assert.assertTrue(registrationPage.getPass1Error().contains("Пароли в полях Подтверждение пароля и Пароль не совпадают."));
        Assert.assertTrue(registrationPage.getPass2Error().contains("Пароли в полях Пароль и Подтверждение пароля не совпадают."));

    }

    @Test(priority = 1)
    public void minPasswordTest() {
        registrationPage = new RegistrationPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        registrationPage
                .typeName("")
                .typeSurname("")
                .typeEmail("pass@test.ru")
                .typePhone("")
                .typePass1("1234")
                .typePass2("1234")
                .register();
        Assert.assertTrue(registrationPage.getErrorAlert().contains("Ошибка Пароль должен содержать как минимум 5 символов."));

    }

    @Test(priority = 1)
    public void incorrectEmailTest() {
        registrationPage = new RegistrationPage(driver);
        successRegistrationPage = new SuccessRegistrationPage(driver);
        registrationPage
                .typeName("")
                .typeSurname("")
                .typeEmail("pass")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(registrationPage.getErrorEmail().contains("Email в поле E-mail неверен."));
        registrationPage
                .typeName("")
                .typeSurname("")
                .typeEmail("pass@t223est")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(registrationPage.getErrorEmail().contains("Email в поле E-mail неверен."));
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


