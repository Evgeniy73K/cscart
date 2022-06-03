import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class RegistratinPageTestClass {
    private RegistrationPage registrationPage;
    private SuccessRegistrationPage successRegistrationPage;


    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        Configuration.browserSize = "1920x1080";
        baseUrl = "http://localhost/cscart/profiles-add/";
        browser = "chrome";


    }

    @Test
    public void validRegisterTest() {
        registrationPage = new RegistrationPage();
        successRegistrationPage = new SuccessRegistrationPage();
        registrationPage
                .open()
                .typeName("Test")
                .typeSurname("Test")
                .typeEmail("Test11@test11.ru")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(successRegistrationPage.getH1().contains("Вы успешно зарегистрированы"));
        Selenide.closeWebDriver();
    }

    @Test
    public void existUserRegisterTest() {
        registrationPage = new RegistrationPage();
        successRegistrationPage = new SuccessRegistrationPage();
        registrationPage
                .open()
                .typeName("Test")
                .typeSurname("Test")
                .typeEmail("Test@test.ru")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(registrationPage.getErrorAlert().text().contains("Ошибка Такое имя пользователя или email уже существуют. Пожалуйста, попробуйте другой вариант."));


    }

    @Test
    public void emptyFieldsTest() {
        registrationPage = new RegistrationPage();
        registrationPage
                .open()
                .typeName("")
                .typeSurname("")
                .typeEmail("")
                .typePhone("")
                .typePass1("")
                .typePass2("")
                .register();
        Assert.assertTrue(registrationPage.getErrorEmail().text().contains("Поле E-mail обязательное."));
        Assert.assertTrue(registrationPage.getPass1Error().text().contains("Поле Пароль обязательное."));
        Assert.assertTrue(registrationPage.getPass2Error().text().contains("Поле Подтверждение пароля обязательное."));

    }

    @Test
    public void incorrectPasswordTest() {
        registrationPage = new RegistrationPage();
        successRegistrationPage = new SuccessRegistrationPage();
        registrationPage
                .open()
                .typeName("")
                .typeSurname("")
                .typeEmail("pass@test.ru")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12341")
                .register();
        Assert.assertTrue(registrationPage.getPass1Error().text().contains("Пароли в полях Подтверждение пароля и Пароль не совпадают."));
        Assert.assertTrue(registrationPage.getPass2Error().text().contains("Пароли в полях Пароль и Подтверждение пароля не совпадают."));
    }

    @Test
    public void minPasswordTest() {
        registrationPage = new RegistrationPage();
        successRegistrationPage = new SuccessRegistrationPage();
        registrationPage
                .open()
                .typeName("")
                .typeSurname("")
                .typeEmail("pass@test.ru")
                .typePhone("")
                .typePass1("1234")
                .typePass2("1234")
                .register();
        Assert.assertTrue(registrationPage.getErrorAlert().text().contains("Ошибка Пароль должен содержать как минимум 5 символов."));

    }

    @Test
    public void incorrectEmailTest() {
        registrationPage = new RegistrationPage();
        successRegistrationPage = new SuccessRegistrationPage();
        registrationPage
                .open()
                .typeName("")
                .typeSurname("")
                .typeEmail("pass")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(registrationPage.getErrorEmail().text().contains("Email в поле E-mail неверен."));
        registrationPage
                .open()
                .typeName("")
                .typeSurname("")
                .typeEmail("pa22ss@td12de21st1")
                .typePhone("")
                .typePass1("12345")
                .typePass2("12345")
                .register();
        Assert.assertTrue(registrationPage.getErrorEmail().text().contains("Email в поле E-mail неверен."));
        Selenide.closeWebDriver();
    }
    @After
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }

}


