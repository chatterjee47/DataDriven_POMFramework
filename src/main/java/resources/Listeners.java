package resources;

import java.io.IOException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClass.BrowserSetup;


public class Listeners implements ITestListener{
	static RemoteWebDriver driver;
	
	
	public void onTestStart(ITestResult result) {	
		System.out.println("The name of the testcase started is :" + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("The name of the testcase passed is :"+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("The name of the testcase failed is :"+result.getName());
		String s=result.getName();
		try {
			BrowserSetup.getScreenshot(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("The name of the testcase Skipped is :"+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed but within success percentage: " +result.getName());
	}

	public void onStart(ITestContext context) {
		System.out.println("This is onStart method: " +context.getOutputDirectory());
	}

	public void onFinish(ITestContext context) {
		System.out.println("This is onFinish method: " +context.getPassedTests());
		System.out.println("This is onFinish method: " +context.getFailedTests());
	}
}
