package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static base.DriverContainer.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by azart on 5/7/16.
 */
public class Waiters {
    public static void waitToBeVisible(By element) {
        WebDriverWait waiter = new WebDriverWait(getDriver(), 15);
        waiter.until(visibilityOfElementLocated(element));
    }

    public static void waitToBeClickable(By element) {
        WebDriverWait waiter = new WebDriverWait(getDriver(), 15);
        waiter.until(elementToBeClickable(element));
    }

    public static void waitToBePresent(By element) {
        WebDriverWait waiter = new WebDriverWait(getDriver(), 15);
        waiter.until(presenceOfElementLocated(element));
    }

    public static void waitToBeNotPresent(By element) {
        WebDriverWait waiter = new WebDriverWait(getDriver(), 15, 500);
        waiter.until(not(presenceOfElementLocated(element)));
    }

    public static void waitToBeNotVisible(By element) {
        WebDriverWait waiter = new WebDriverWait(getDriver(), 15, 500);
        waiter.until(invisibilityOfElementLocated(element));
    }

}
