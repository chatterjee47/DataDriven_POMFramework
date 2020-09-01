package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='uid']")
	private WebElement UserName;

	public void setUserName(String strUserName) {
		try {
			UserName.sendKeys(strUserName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AssertionError("UserName not entered", e);
		}
	}

	@FindBy(xpath = "//input[@name='password']")
	private WebElement Password;

	public void setPassword(String strPassword) {
		try {
			Password.sendKeys(strPassword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AssertionError("Password not entered", e);
		}	
	}
	
	@FindBy(xpath = "//input[@name='btnLogin']")
	private WebElement LoginButton;

	public void ClickOnLoginButton() {
		try {
			LoginButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}
