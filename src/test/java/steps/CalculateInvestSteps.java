package steps;

import com.thoughtworks.gauge.Step;
import driver.Driver;

import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateInvestSteps {
    public Integer yatirimTutari = 0;
    public Integer faizOrani = 0;
    public Integer yil = 0;


    @Tag("Yatırım Hesaplama")
    @Step("Kullanıcı yapmak istediği yatırım tutarını girer:<yatirimTutari>")
    public void calculateInvestAmount(Integer yatirimTutari) throws InterruptedException {
        this.yatirimTutari = yatirimTutari;
        Driver.webDriver.findElement(By.xpath("//div[text()='*']")).click();
        char[] digits = yatirimTutari.toString().toCharArray();
        for (char digit : digits) {
            WebElement digitButton = Driver.webDriver.findElement(By.xpath("//div[text()='" + digit + "']"));
            digitButton.click();
            Thread.sleep(500);
        }

    }

    @Tag("Yatırım Hesaplama")
    @Step("Kullanıcı faiz oranını ve yılı  girer faiz oranı:<faizOrani>,yıl:<yil>")
    public void typeInterestRate(Integer faizOrani, Integer yil) throws InterruptedException {
        this.faizOrani = faizOrani;
        this.yil = yil;
        Integer hundred = 100;

        Driver.webDriver.findElement(By.xpath("//div[text()='" + faizOrani + "']")).click();
        Thread.sleep(1000);

        Driver.webDriver.findElement(By.xpath("//div[text()='/']")).click();
        Thread.sleep(1000);

        char[] digits = hundred.toString().toCharArray();
        for (char digit : digits) {
            WebElement digitButton = Driver.webDriver.findElement(By.xpath("//div[text()='" + digit + "']"));
            digitButton.click();
            Thread.sleep(500);
        }

        Driver.webDriver.findElement(By.xpath("//div[text()='=']")).click();
        Thread.sleep(1000);

        Driver.webDriver.findElement(By.xpath("//div[text()='*']")).click();
        Thread.sleep(1000);

        WebElement year = Driver.webDriver.findElement(By.xpath("//div[text()='" + yil + "']"));
        year.click();
        Thread.sleep(500);

        Driver.webDriver.findElement(By.xpath("//div[text()='=']")).click();
        Thread.sleep(1000);

        Driver.webDriver.findElement(By.xpath("//div[text()='+']")).click();
        year.click();
        Thread.sleep(1000);

        Driver.webDriver.findElement(By.xpath("//div[text()='=']")).click();
        Thread.sleep(1000);

    }

    @Tag("Yatırım Hesaplama")
    @Step("Kullanıcı toplam yatırımını doğru hesaplanmış olarak görmelidir")
    public void calculateInvest() throws InterruptedException {

        Driver.webDriver.findElement(By.xpath("//div[text()='=']")).click();
        Integer expectedInvest = (yatirimTutari * (yil + ((faizOrani / 100) * yil)));

        System.out.println(expectedInvest);
        Thread.sleep(1000);

        WebElement text = Driver.webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div/div/div/div[1]/div/span"));
        String actualInvest = text.getText();
        System.out.println("Element text: " + text);
        assertEquals(expectedInvest.toString(), actualInvest);

    }



}
