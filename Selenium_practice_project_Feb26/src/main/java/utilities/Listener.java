package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.Base;

public class Listener extends Base implements ITestListener {

	ExtentSparkReporter reporter;
	ExtentReports report = new ExtentReports();
	ExtentTest test;
	String path = System.getProperty("user.dir") + "\\reports\\testreport.html";
	String sspath = System.getProperty("user.dir") + "\\screenshot\\";

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.print("Test started");
		reporter = new ExtentSparkReporter(new File(path));
		reporter.config().setReportName("Automation Report of Ecommerce Testing");
		reporter.config().setDocumentTitle("Detailed End to End Report");
		report.attachReporter(reporter);
		report.setSystemInfo("Env", "CD");
		report.setSystemInfo("Tester", "Subhra");
		report.setSystemInfo("Drop", "Drop 2");
		test = report.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.print("Test pass");
		test.pass(result.getMethod().getMethodName() + " - Test is a pass");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.print("Test fail");
		test.fail(result.getMethod().getMethodName() + " - Test is a fail");
		SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyyhhMMss");
		String curtime = sdf.format(new Date());
		String sspath2 = sspath + curtime + "_fail.png";
		try {
			PageFunction.takeScreenshot(driver, sspath2);
			log.info("screenshot added successfully");
			test.addScreenCaptureFromPath(sspath2);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
