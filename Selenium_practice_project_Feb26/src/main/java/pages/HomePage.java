package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import utilities.PageFunction;

public class HomePage extends Base {

	@FindBy(css = "#main_menu_top [data-id='menu_account']")
	WebElement accountBtn;

	@FindBy(css = "#main_menu_top [data-id='menu_account'] [href*='login']")
	WebElement loginBtn;

	@FindBy(css = ".maintext")
	WebElement loginPageTxt;

	@FindBy(css = ".maintext")
	WebElement succLoginMsgTxt;

	@FindBy(css = ".thumbnails.grid>div")
	List<WebElement> productList;

	@FindBy(css = ".cart")
	WebElement btn_addtocart;
	
	@FindBy(css = "#main_menu_top li[data-id='menu_cart'] span")
	WebElement btn_menucart;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public LoginPage clickOnlogin() {
		PageFunction.moveToElement(driver, accountBtn);
		PageFunction.clickOnElement(driver, loginBtn);
		log.info("Hover on account and then clicked on login button");
		Assert.assertTrue(loginPageTxt.isDisplayed());
		return new LoginPage();
	}

	public String validateLogin() {

		return succLoginMsgTxt.getText();
	}

	public void selectProduct(String category, String subcategory, String productname) {
		String categoryLoc = "//ul[contains(@class, 'categorymenu')]//a[contains(text(),'" + category + "')]";
		String subcategoryLoc = "//ul[contains(@class, 'categorymenu')]//a[contains(text(),'" + category
				+ "')]//following-sibling::div//a[contains(text(), '" + subcategory + "')]";
		WebElement btn_category = driver.findElement(By.xpath(categoryLoc));
		WebElement btn_subcategory = driver.findElement(By.xpath(subcategoryLoc));

		PageFunction.moveToElement(driver, btn_category);
		PageFunction.clickOnElement(driver, btn_subcategory);
		productList.stream()
				.filter(s -> s.findElement(By.cssSelector(".prdocutname")).getText().equalsIgnoreCase(productname))
				.findFirst().get().findElement(By.cssSelector(".prdocutname")).click();

		PageFunction.clickOnElement(driver, btn_addtocart);
		log.info(productname + " added to the cart");

	}
	
	public CartPage clickOnCart() {
		PageFunction.clickOnElement(driver, btn_menucart);
		return new CartPage();
	}
	
	

}
