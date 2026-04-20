package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import baseT.BaseTest;
import pages.LoginPage;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest {
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setupReport() {
        extent = ExtentManager.getReport();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String user, String pass) {
    	  test = extent.createTest("Login Test: " + user);
        LoginPage lp = new LoginPage(driver);
        lp.login(user, pass);
    }
    @AfterMethod
    public void tearDownTest(ITestResult result) {

        if (test == null) {
            return; // 👉 crash avoid
        }

        if (result.getStatus() == ITestResult.FAILURE) {

            ScreenshotUtil.capture(driver, result.getName());

            String path = System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".png";

            test.fail("Test Failed",
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            test.pass("Test Passed");

        } else if (result.getStatus() == ITestResult.SKIP) {

            test.skip("Test Skipped");
        }
    }

    @AfterClass
    public void flushReport() {
        extent.flush();
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
            {"Admin", "admin123"} ,
            {"test2@gmail.com", "123456"}
        };
    }
}
