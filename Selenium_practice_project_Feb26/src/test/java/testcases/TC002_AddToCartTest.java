package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DataProviders;
import utilities.Listener;

@Listeners(Listener.class)
public class TC002_AddToCartTest extends Base{

	String testdatapath = System.getProperty("user.dir") + "\\resources\\Testdata.xlsx";
	String sheetname = "Data";
	HomePage obj_homepage = null;
	LoginPage obj_loginpage = null;
	CartPage obj_cartpage = null;
	SoftAssert s = null;
	boolean testpass = false;
	boolean testfail = true;

	@BeforeTest
	void setup() {
		loadWebBrowser();
		
		obj_homepage = new HomePage();
		obj_loginpage = obj_homepage.clickOnlogin();
		obj_homepage = obj_loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(obj_homepage.validateLogin(),"MY ACCOUNT");
		log.info("Login successful");
	}
	
	@Test(dataProvider = "allData", dataProviderClass = DataProviders.class)
	void addToCart(String TC_ID, String category, String subcategory, String productName, String result)
			throws IOException {
		log.info("Test details: "+ TC_ID+category+subcategory+productName);
		s = new SoftAssert();
		obj_homepage.selectProduct(category, subcategory, productName);
		obj_cartpage = obj_homepage.clickOnCart();
		Assert.assertTrue(obj_cartpage.validateProductPresentInCart(productName));
		s.assertAll();
		testfail = false;	

	}
	
	@AfterMethod
	void updateResultOnTestdataSheet(){
		 
	}
	
	
	
	
}
