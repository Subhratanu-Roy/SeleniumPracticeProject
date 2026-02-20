package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFunction {

	public static void moveToElement(WebDriver driver, WebElement element) {
		waitForElement(driver, element);
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}

	public static void waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(element));

	}

	public static void clickOnElement(WebDriver driver, WebElement ele) {
		try {
			for (int i = 0; i < 5; i++) {
				ele.click();
				break;
			}
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", ele);
			
		}
	}
	
	public static void sendKeys(WebElement ele, String txt) {
		ele.sendKeys(txt);
	}
	
	public static void takeScreenshot(WebDriver driver, String sspath) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(sspath));
	}

}
