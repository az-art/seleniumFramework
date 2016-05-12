package base;

import helpers.ScreenshotListener;
import org.testng.annotations.*;
import pages.Header;
import pages.LoginPage;
import pages.MailPage;

import static base.DriverContainer.open;
import static base.DriverContainer.quitDriver;

@Listeners(ScreenshotListener.class)

public class TestBase {
    private static final String BASE_URL = "https://mail.ru";

    @BeforeTest
    public void setUp() {
        open(BASE_URL);
    }

    @BeforeClass
    public void login() {
        LoginPage.shouldAppear();
        LoginPage.login();
        MailPage.shouldAppear();
    }


    @AfterClass
    public void logout() {
        Header.logout();
    }

    @AfterTest
    public void tearDown() {
        quitDriver();
    }
}
