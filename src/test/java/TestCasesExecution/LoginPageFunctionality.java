package TestCasesExecution;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import BaseClass.BrowserSetup;
import ExcelReader.getExcelData;
import PageObjects.LoginPage;
import Utilities.utility;
import atu.testrecorder.ATUTestRecorder;

public class LoginPageFunctionality extends getExcelData {
	static WebDriver driver;
	LoginPage loginpage;
	ATUTestRecorder recorder;
	utility util;
	static Logger log = Logger.getLogger(LoginPageFunctionality.class.getName());
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/SFDC_ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "LUBS-EC-SFDC-AutomationTest").addSystemInfo("Environment", "Regression Test")
				.addSystemInfo("User Name", "Amit Chatterjee");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\config\\extent-config.xml"));
	}

	@BeforeMethod
	public void HomePageNavigation(Method method) throws Throwable {
		DOMConfigurator.configure("log4j.xml");
		Thread.sleep(1000);
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		String path = System.getProperty("user.dir");

		utility.deleteVideos(path + "\\ScriptVideos\\");
		recorder = new ATUTestRecorder(path + "\\ScriptVideos\\",
				method.getName() + "ScriptVideo-" + dateFormat.format(date), false);
		recorder.start();
		driver = BrowserSetup.StartBrowser("Chrome", "http://www.demo.guru99.com/v4/");
		log.info("Application launched");

	}

	@Test(dataProvider = "getLoginData", priority = 0, groups = { "TCID 01: Verifying Login TestCase" }, enabled = true)
	public void LoginPageOne(String username, String password) throws InterruptedException {
		DOMConfigurator.configure("log4j.xml");
		logger = extent.startTest("Login Page", "Login Page functionality testing");
		Assert.assertTrue(true);
		loginpage = new LoginPage(driver);
		loginpage.ClickOnLoginButton();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		log.info("login button clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		util = new utility(driver);
		util.handleAlert();
		log.info("Alert clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.setUserName(username);
		log.info("username entered");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.setPassword(password);
		log.info("password entered");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.ClickOnLoginButton();
		log.info("login button clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		util = new utility(driver);
		util.handleAlert();
		log.info("Alert clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "getLoginData", priority = 1, groups = { "TCID 02: Verifying failed Login TestCase" }, enabled = true)
	public void LoginPageTwo(String username, String password) throws InterruptedException {
		DOMConfigurator.configure("log4j.xml");
		logger = extent.startTest("Login Page", "Login Page functionality testing");
		//logger.log(LogStatus.PASS, "Test Case Passed is Login Page");
		loginpage = new LoginPage(driver);
		loginpage.ClickOnLoginButton();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		log.info("login button clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		util = new utility(driver);
		util.handleAlert();
		log.info("Alert clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.setUserName(username);
		log.info("username entered");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.setPassword(password);
		log.info("password entered");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.ClickOnLoginButton();
		log.info("login button clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Assert.assertTrue(false);
		util = new utility(driver);
		util.handleAlert();
		log.info("Alert clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case Successfully executed is " + result.getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case failed due to " + result.getThrowable());
			util = new utility(driver);
			String screenshotPath = util.getScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.flush();
		driver.close();
		recorder.stop();
	}

}