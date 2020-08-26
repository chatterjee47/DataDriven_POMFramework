package TestCasesExecution;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import BaseClass.BrowserSetup;
import ExcelReader.getExcelData;
import PageObjects.LoginPage;

public class LoginPageTest  extends getExcelData{
	static WebDriver driver;
	LoginPage loginpage;
	static Logger log = Logger.getLogger(LoginPageTest.class.getName());
	
	
	@BeforeMethod(alwaysRun = true)
	public void setupSuite() throws InterruptedException {
	//	PropertyConfigurator.configure("C:\\Users\\Rush 14\\workspace\\ReadExcelData_DataProvider\\config\\log4j.properties");
		DOMConfigurator.configure("log4j.xml");
		driver = BrowserSetup.StartBrowser("Chrome" ,"http://www.demo.guru99.com/v4/");
		log.info("Application launched");
	}

	@Test(dataProvider="getLoginData")
    public void LoginTestCase( String username, String password) throws InterruptedException{
		DOMConfigurator.configure("log4j.xml");
		loginpage = new LoginPage(driver);
		loginpage.setUserName(username);
		log.info("username entered");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.setPassword(password);
		log.info("password entered");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		loginpage.ClickOnLoginButton();
		log.info("login button clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		handleAlert();
		log.info("Alert clicked");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.close();
    }
	

	public static void handleAlert(){
	    if(isAlertPresent()){
	        Alert alert = driver.switchTo().alert();
	        System.out.println(alert.getText());
	        alert.accept();
	    }
	}
	public static boolean isAlertPresent(){
	      try{
	          driver.switchTo().alert();
	          return true;
	      }catch(NoAlertPresentException ex){
	          return false;
	      }
	}
	
}
	
