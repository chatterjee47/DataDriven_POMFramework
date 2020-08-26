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
		UserName.sendKeys(strUserName);
	}

	@FindBy(xpath = "//input[@name='password']")
	private WebElement Password;

	public void setPassword(String strPassword) {
		Password.sendKeys(strPassword);
		
	}
	
	@FindBy(xpath = "//input[@name='btnLogin']")
	private WebElement LoginButton;

	public void ClickOnLoginButton() {
		LoginButton.click();
		
	}

	

}
