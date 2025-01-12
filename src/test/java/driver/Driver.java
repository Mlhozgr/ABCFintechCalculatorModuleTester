package driver;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;


public class Driver {

    public static WebDriver webDriver;

    @BeforeSuite
    public void initializeDriver(){

        webDriver = DriverFactory.getDriver();


    }

    // Close the webDriver instance
    @AfterSuite
    public void closeDriver(){
        webDriver.quit();

    }

}
