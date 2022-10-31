package Selenium.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportFile {

	public static ExtentReports ExtentReporter()
	{
		
		String path = System.getProperty("user.dir") +"//reports//index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); // modification to existing html file
		
		reporter.config().setDocumentTitle("Test Results");
		
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports report = new ExtentReports(); // used to create test, monitor the test
		
		report.attachReporter(reporter);
		
		report.setSystemInfo("Tester", "Jyoti Kharatmol");
		return report;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
