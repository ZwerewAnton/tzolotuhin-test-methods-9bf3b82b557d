package ru.nsu.fit.tests.ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.nsu.fit.services.browser.Browser;
import ru.nsu.fit.services.browser.BrowserService;
import ru.nsu.fit.services.log.Logger;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Timur Zolotuhin (tzolotuhin@gmail.com)
 */
public class AcceptanceTest {
    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test
    @Title("Create customer with null firstname")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerFirstNameNull() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("FirstName is empty.");
        Logger.info(f ? "Test customer with null firstname is pass" : "Test customer with null firstname is fall");
        Reporter.log(f ? "Test customer with null firstname is pass" : "Test customer with null firstname is fall");
        Assert.assertTrue(f);
        screenShooter();
    }


    @Test
    @Title("Create customer with space in firstname")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerFirstNameSpace() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cli ff");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("FirstName have space.");
        Logger.info(f ? "Test customer with space in firstname is pass" : "Test customer with space in firstname is fall");
        Reporter.log(f ? "Test customer with space in firstname is pass" : "Test customer with space in firstname is fall");
        Assert.assertTrue(f);
        screenShooter();
    }

    @Test
    @Title("Create customer with small length of firstname")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerFirstNameSmallLength() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cli");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        Logger.info(f ? "Test customer with small length of firstname is pass" : "Test customer with small length of firstname is fall");
        Reporter.log(f ? "Test customer with small length of firstname is pass" : "Test customer with small length of firstname is fall");
        Assert.assertTrue(f);
        screenShooter();
    }

    @Test
    @Title("Create customer with big length of firstname")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerFirstNameLength() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("IAmCliffBoothHa");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        Logger.info(f ? "Test customer with big length of firstname is pass" : "Test customer with big length of firstname is fall");
        Reporter.log(f ? "Test customer with big length of firstname is pass" : "Test customer with big length of firstname is fall");
        Assert.assertTrue(f);
        screenShooter();
    }

    @Test
    @Title("Create customer with null lastname")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerLastNameNull() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cliff");
        browser.getElement(By.id("last_name_id")).sendKeys("");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("LastName is empty.");
        Logger.info(f ? "Test customer with null lastname is pass" : "Test customer with null lastname is fall");
        Reporter.log(f ? "Test customer with null lastname is pass" : "Test customer with null lastname is fall");
        Assert.assertTrue(f);
        screenShooter();
    }

    @Test
    @Title("Create customer with space in lastname")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerLastNameSpace() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cliff");
        browser.getElement(By.id("last_name_id")).sendKeys("Bo oth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("LastName have space.");
        Logger.info(f ? "Test customer with space in lastname is pass" : "Test customer with space in lastname is fall");
        Reporter.log(f ? "Test customer with space in lastname is pass" : "Test customer with space in lastname is fall");
        Assert.assertTrue(f);
        screenShooter();
    }

    @Test
    @Title("Create customer email null")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerEmailNull() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cliff");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("");
        browser.getElement(By.id("password_id")).sendKeys("123asdff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("Email or password is empty.");
        Logger.info(f ? "Test customer email null is pass" : "Test customer email null is fall");
        Reporter.log(f ? "Test customer email null is pass" : "Test customer email null is fall");
        Assert.assertTrue(f);
        screenShooter();
    }


    @Test
    @Title("Create customer password null")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerPasswordNull() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cliff");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("Email or password is empty.");
        Logger.info(f ? "Test customer password null is pass" : "Test customer password null is fall");
        Reporter.log(f ? "Test customer password null is pass" : "Test customer password null is fall");
        Assert.assertTrue(f);
        screenShooter();
    }

    @Test
    @Title("Create customer email password")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void checkCustomerEmailPasswordWithPartOfNames() throws InterruptedException {
        //Browser browser = BrowserService.openNewBrowser();
        browser.openPage("http://localhost:8081/endpoint/add_customer.html?login=admin&pass=setup&role=ADMIN");

        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys("Cliff");
        browser.getElement(By.id("last_name_id")).sendKeys("Booth");
        browser.getElement(By.id("email_id")).sendKeys("cliboo@gmail.com");
        browser.getElement(By.id("password_id")).sendKeys("Cliff");

        browser.getElement(By.id("create_customer_id")).click();

        String alert = browser.getAlert();
        boolean f = alert.equals("Password contains part of the FirstName or LastName.");
        Logger.info(f ? "Test Password contains part of the FirstName or LastName is pass" : "Test Password contains part of the FirstName or LastName is fall");
        Reporter.log(f ? "Test Password contains part of the FirstName or LastName is pass" : "Test Password contains part of the FirstName or LastName is fall");
        Assert.assertTrue(f);
        screenShooter();
    }





    @AfterClass
    public void afterClass() {
        if (browser != null)
            browser.close();
    }

    public void screenShooter(){
        FileOutputStream file;
        try {
            String path = System.getProperty("user.dir") + "\\test-output\\screen\\" + System.currentTimeMillis() + ".jpg";
            file = new FileOutputStream(path);
            file.write(browser.makeScreenshot());
            Reporter.log("<br/><a href='" + path + "'> <img src='"+ path + "' height='300' width='600'/> </a>");
            file.close();
        } catch (Exception e) {
            Logger.info(e.toString());
            Reporter.log(e.toString());
        }
    }
}
