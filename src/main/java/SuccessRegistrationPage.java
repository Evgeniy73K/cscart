import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class SuccessRegistrationPage {


    By head = By.xpath("//h1[@class=\"ty-mainbox-title\"]");

    public String getH1() {
        String h1 = $(head).text();
        return h1;
    }
}
