package TestCasesExecution;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClass.BrowserSetup;
import ExcelReader.getExcelData;
import PageObjects.LoginPage;
import Utilities.utility;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class LoginPageTest extends getExcelData {
	static WebDriver driver;
	LoginPage loginpage;
	ATUTestRecorder recorder;
	utility util;
	static Logger log = Logger.getLogger(LoginPageTest.class.getName());

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

		// To start video recording.
		recorder.start();

		driver = BrowserSetup.StartBrowser("Chrome", "http://www.demo.guru99.com/v4/");
		log.info("Application launched");

	}

	@Test(dataProvider = "getLoginData", priority = 0, groups = {
			"TCID 01: Verifying Login TestCase" }, enabled = true)
	public void LoginTestCase(String username, String password) throws InterruptedException {
		DOMConfigurator.configure("log4j.xml");
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

	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws ATUTestRecorderException {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				String path = System.getProperty("user.dir");
				System.out.println(path);
				FileUtils.copyFile(source,
						new File(path + "//FailTestCaseScreenshots//" + result.getName() + "-" + timestamp() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		driver.quit();
		recorder.stop();
	}
}
