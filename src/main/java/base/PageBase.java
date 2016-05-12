package base;

import helpers.Waiters;

import static base.DriverContainer.getDriver;
import static org.testng.Assert.assertTrue;

public class PageBase extends Waiters{

    protected static void shouldAppear(String title) {
        assertTrue(getDriver().getTitle().contains(title), "Title is incorrect. Actual: " + getDriver().getTitle() + " Expected: " + title);
    }



    //GAnalytics, DocType = html, Tags

}
