package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverFactory {
    private static WebDriver driver;


    public static WebDriver getDriver(DriverType driverType) throws NowSuchDriverException {
        if (driver == null) {
            getSpecificDriver(driverType);
            driver.manage().window().maximize();
        }
        return driver;

    }

    private static void getSpecificDriver(DriverType driverType) throws NowSuchDriverException {
        switch (driverType) {
            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            default:
                System.out.println("Brak drivera danego typu");
                throw new NowSuchDriverException();

        }
    }

    public static void resetDriver() {
        driver = null;
    }
}
