package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

    public static WebDriver getDriver() {

        String browser = System.getenv("BROWSER");
        browser = (browser == null) ? "CHROME" : browser.toUpperCase(); // BROWSER çevresel değişkenini al ve büyük harfe çevir

        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();


                options.addArguments("--disable-gpu"); // GPU'yu devre dışı bırak (Linux için faydalı olabilir)
                options.addArguments("--no-sandbox"); // Sandbox'ı devre dışı bırak (Linux için faydalı olabilir)
                options.addArguments("--start-maximized"); // Tarayıcıyı tam ekran başlat
                return new ChromeDriver(options); // Seçenekler ile ChromeDriver başlat
        }
    }
}
