package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class CartPage extends Base{

	@FindBy(css = "div.product-list tr td:nth-of-type(2) a")
	List<WebElement> productList;
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateProductPresentInCart(String productname) {
		long count = productList.stream().filter(s -> s.getText().equalsIgnoreCase(productname)).count();
		log.info("Count of product: "+count);
		return (count > 0) ? true : false;
	}
	
}
