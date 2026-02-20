package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelLibrary;
import utilities.Log;

public class Base {

	public static WebDriver driver = null;
	public Properties prop = null;
	public FileInputStream fis = null;
	public Log log = new Log();
	public static ExcelLibrary excelLibrary = null;

	@BeforeSuite
	public void init() throws IOException {
		//System.out.println("Enter into init");
		String path = System.getProperty("user.dir") + "\\resources\\Globaldata.properties";
		fis = new FileInputStream(new File(path));
		prop = new Properties();
		prop.load(fis);
		String excelpath = System.getProperty("user.dir") + "\\resources\\Testdata.xlsx";
		excelLibrary = new ExcelLibrary(excelpath, "Data");
	}

	public void loadWebBrowser() {
		String browser = prop.getProperty("browser");
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			log.info("chrome browser is opening");
			break;
		case "firefox":
			driver = new FirefoxDriver();
			log.info("firefox browser is opening");
			break;
		case "edge":
			driver = new EdgeDriver();
			log.info("edge browser is opening");
			break;

		default:
			log.warn("Not a valid browser");
			break;

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		log.info("Url "+prop.getProperty("url")+" loaded successfully");
	}
	
	@AfterSuite
	public void closeWebBrowser() {
		driver.close();
	}
}
