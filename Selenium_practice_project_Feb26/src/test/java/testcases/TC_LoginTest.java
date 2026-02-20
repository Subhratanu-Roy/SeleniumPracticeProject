package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.LoginPage;

public class TC_LoginTest extends Base{

	HomePage obj_homepage = null;
	LoginPage obj_loginpage = null;
	
	@BeforeTest
	void setup() {
		loadWebBrowser();
	}
	
	@Test
	void tc_login() {
		obj_homepage = new HomePage();
		obj_loginpage = obj_homepage.clickOnlogin();
		obj_homepage = obj_loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(obj_homepage.validateLogin(),"MY ACCOUNT");
		log.info("Login successful");
	}
	
	@AfterTest
	void teardown() {
		closeWebBrowser();
	}
	
}
