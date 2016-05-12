package base;

import base.config.DriverType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static base.config.DriverType.FIREFOX;

/**
 * Created by azart on 4/13/16.
 */
public class DriverContainer {
    private static WebDriver webdriver = null;
    private static DriverType selectedDriverType;
    private final static DriverType defaultDriverType = FIREFOX;
    //    private static String browser = "FIREFOX";
    private final static String browser = System.getProperty("browser", "firefox").toUpperCase();
    private final static String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final static String systemArch = System.getProperty("os.arch");

    protected DriverContainer() {
        //block constractor for outer calls
    }

//    public static void setDriver(String browser) {
//        DriverContainer.browser = browser.toUpperCase();
//    }

    public static WebDriver getDriver() {
        if (webdriver == null) {
            selectedDriverType = selectDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }
        return webdriver;
    }

    private static DriverType selectDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, fefaulting to '" + driverType + "'...");
        }
        return driverType;
    }

    private static void instantiateWebDriver(DesiredCapabilities desiredCapabilities) {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArch);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");

        webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);

        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void quitDriver() {
        if (webdriver != null) {
            webdriver.quit();
            webdriver = null;
        }
    }


    public static WebElement findElement(By locator) {
        return getDriver().findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }

    public static void open(String url) {
        getDriver().get(url);
    }

}
