package Selenium.testsComponents;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Selenium.Resources.ExtentReportFile;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports report =	ExtentReportFile.ExtentReporter();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // thread safe : Error Validation test -> will not be overriden by 
	//Submit Order test
	//Each test -> unique thread id
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = report.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // -> unique thread id 
		//Replace test with extentTest
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test is matching the expected results");
	}

	
	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());
		//Screenshot
		
		String filePath = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try
		{
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} 
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());	
	}
	
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {

		report.flush();
		
	}
	
	
	
	
	
	
	

}
