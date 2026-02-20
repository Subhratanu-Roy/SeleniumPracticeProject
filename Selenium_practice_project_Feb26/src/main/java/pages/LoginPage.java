package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utilities.PageFunction;

public class LoginPage extends Base{

	@FindBy(id = "loginFrm_loginname")
	WebElement in_loginName;
	
	@FindBy(id = "loginFrm_password")
	WebElement in_password;
	
	@FindBy(css = "[type='submit'][title='Login']")
	WebElement btn_login;
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String username, String password) {
		PageFunction.sendKeys(in_loginName, username);
		PageFunction.sendKeys(in_password, password);
		PageFunction.clickOnElement(driver, btn_login);
		return new HomePage();
	}
	
}
