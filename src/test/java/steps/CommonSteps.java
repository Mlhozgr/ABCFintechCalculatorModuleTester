package steps;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonSteps {
    @Tag("Giriş Yap")
    @Step("Hesaplama modulü ana sayfasına yönlenilir")
    public void openCatchyLabs() {
        Driver.webDriver.get("https://catchylabs-webclient.testinium.com/");
    }

    @Tag("Giriş Yap")
    @Step("Kullanıcı Adı:<username> , Şifre:<password> girerek login olur")
    public void loginPage(String username, String password) {
        WebElement usernameInput = Driver.webDriver.findElement(By.cssSelector("[placeholder='Username']"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = Driver.webDriver.findElement(By.cssSelector("[placeholder='Password']"));
        passwordInput.sendKeys(password);
        Driver.webDriver.findElement(By.xpath("*//div[contains(text(),'Login')]")).click();
    }

    @Tag("Çıkış Yap")
    @Step("Kullanıcı sistemden çıkış yapar")
    public void logOut() {
        Driver.webDriver.findElement(By.xpath("*//div[contains(text(),'Back')]")).click();
    }


    @Tag("Hesaplama Makinesi Aç")
    @Step("Hesaplama modülü açılır")
    public void openCalculator() {
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.
                        visibilityOfElementLocated(By.xpath("*//div[contains(text(),'Open Calculator')]")))
                .click();

    }
}
