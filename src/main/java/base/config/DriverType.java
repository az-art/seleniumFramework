package base.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by azart on 4/14/16.
 */
public enum  DriverType implements DriverSetup {

    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new FirefoxDriver(capabilities);
        }
    },

    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new ChromeDriver(capabilities);
        }
    },

    IE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    },

    SAFARI {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },

    OPERA {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new OperaDriver(capabilities);
        }
    },

    HTMLUNIT {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
            capabilities.setJavascriptEnabled(true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            //((HtmlUnitDriver).driver).setJavascriptEnabled(true);
            return new HtmlUnitDriver(capabilities);
        }
    },

    REMOTEWEBDRIVER {
        public Platform getPlatform() {
            String platformName = System.getProperty("platform", "LINUX").toLowerCase();
            String gridURL = System.getProperty("gridURL", "localhost:4444/wd/hub");
            switch (platformName) {
                case "win8-1" : return Platform.WIN8_1;
                case "win10" : return Platform.WIN10;
                case "linux" : return Platform.LINUX;
                case "win7" : return Platform.VISTA;
                case "mac" : return Platform.EL_CAPITAN;
                default: return Platform.VISTA;
            }
        }
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(getPlatform());
            capabilities.setBrowserName(System.getProperty("browser", "firefox"));
            capabilities.setVersion(System.getProperty("version", ""));
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            try {
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.err.println("Unable to initialize hub, starting Firefox...");
                return new FirefoxDriver();
            }
        }
    }
}
