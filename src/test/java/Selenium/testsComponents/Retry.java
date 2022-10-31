package Selenium.testsComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	/*  If a failed test cases (which might have got failed due to flaky reasons (easily breaking elements sometimes) can be
	 * re-run and verified the output again
	 * You can run the test case n number of times
	 */
		int count = 0;
		int max =1;
	
	
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<max)
		{
			count++;
			return true;
		}
		
		
		return false;
	}

	
	
	
	
}
