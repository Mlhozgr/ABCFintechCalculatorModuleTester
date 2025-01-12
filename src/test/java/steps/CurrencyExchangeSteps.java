package steps;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyExchangeSteps {

    public Integer TLTutari=0;

    @Tag("Döviz Dönüştürme")
    @Step("Kullanıcı dönüştürmek istediği miktarı girer:<TLTutari>")
    public void exchangeAmount(Integer TLTutari) throws InterruptedException {
        this.TLTutari=TLTutari;
        char[] digits = TLTutari.toString().toCharArray();
        for (char digit : digits) {
            WebElement digitButton = Driver.webDriver.findElement(By.xpath("//div[text()='" + digit + "']"));
            digitButton.click();
            Thread.sleep(500);
        }

    }

    @Tag("Döviz Dönüştürme")
    @Step("Kullanıcı dönüştürmek istediği para birimini girer.:<ParaBirimi> ve döviz dönüşümünü görüntüler")
    public void calculateCurrency(String ParaBirimi) throws InterruptedException {
        Double EUR=39.00;
        Double USD=36.00;
        double expected=0.0;

        Driver.webDriver.findElement(By.xpath("//div[text()='/']")).click();
        Thread.sleep(1000);
        if(ParaBirimi.equals("EUR")){
            char[] digits = EUR.toString().replace(".",",").toCharArray();
            for (char digit : digits) {
                WebElement digitButton = Driver.webDriver.findElement(By.xpath("//div[text()='" + digit + "']"));
                digitButton.click();
                Thread.sleep(500);
            }
            expected=TLTutari/EUR;

        }
        else if(ParaBirimi.equals("USD")){
            char[] digits = EUR.toString().replace(".",",").toCharArray();
            for (char digit : digits) {
                WebElement digitButton = Driver.webDriver.findElement(By.xpath("//div[text()='" + digit + "']"));
                digitButton.click();
                Thread.sleep(500);
            }
            expected=(TLTutari/USD);


        }
        Driver.webDriver.findElement(By.xpath("//div[text()='=']")).click();
        Thread.sleep(1000);

        BigDecimal roundedResult = new BigDecimal(expected).setScale(2, RoundingMode.UP);

        System.out.println(roundedResult);
        Thread.sleep(1000);

        WebElement text = Driver.webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div/div/div/div[1]/div/span"));
        String actual = text.getText().replace("=","").strip();
        System.out.println("Element text: " + text);
        assertEquals(String.valueOf(roundedResult).replace(".",",").strip()
                , actual);


    }
}