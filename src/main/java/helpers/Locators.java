package helpers;

import org.openqa.selenium.By;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Locators {
    private static Properties locators;

    private enum LocatorType {
        id,
        name,
        className,
        tagName,
        linkText,
        css,
        xpath
    }

    static {
        locators = new Properties();
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(Locators.class.getResourceAsStream("/locators.properties"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            locators.load(is);
        } catch (Exception e) {
            System.out.println("Error reading from property file");
        }
    }

    public static By get(String locatorName) {
        String value = locators.getProperty(locatorName);
        String[] result = value.split("=", 2);
        LocatorType locatorType = LocatorType.valueOf(result[0]);
        String selector = result[1];

        switch (locatorType) {
            case id:
                return By.id(selector);
            case name:
                return By.name(selector);
            case className:
                return By.className(selector);
            case tagName:
                return By.tagName(selector);
            case linkText:
                return By.linkText(selector);
            case css:
                return By.cssSelector(selector);
            case xpath:
                return By.xpath(selector);
            default:
                throw new IllegalArgumentException("No such locator type" + result[0]);
        }
    }

}
