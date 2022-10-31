package Selenium.testsComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	public WebDriver driver;
	
	public LandingPage landingPage;
	
		public WebDriver initializeBrowser() throws IOException
		{
			Properties pro = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Selenium\\Resources\\GlobalProp.properties");
			pro.load(fis);
			String browserName = pro.getProperty("browser");
	
			if(browserName.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup(); 
				driver = new ChromeDriver(); 
			}
			else if(browserName.equalsIgnoreCase("firefox"))
			{
					System.setProperty("webdriver.gecko.driver", "C:\\Users\\jyoti_xfiqe3z\\OneDrive\\Documents\\GeckoDriver\\geckodriver-v0.31.0-win64\\geckodriver.exe");
					driver = new FirefoxDriver();		
			}
			else if(browserName.equalsIgnoreCase("edge"))
			{
				System.setProperty("webdriver.edge.driver", "C:\\Users\\jyoti_xfiqe3z\\OneDrive\\Documents\\EdgeDriver\\edgedriver_win64\\msedgedriver.exe");
				driver = new EdgeDriver(); 	
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
		}
		
		public List<HashMap<String, String>> getDataMap(String filename) throws IOException
		{
		
			String jsonContent = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
		
			ObjectMapper mapper = new ObjectMapper();
			
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
				{
				});
			return data;
		}
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
		{
			TakesScreenshot	ts= (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			File destination = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
			
			FileUtils.copyFile(source, destination);
			
			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		}
		
	
		@BeforeTest(alwaysRun=true)
		public LandingPage launchApplication() throws IOException
		{
			driver= initializeBrowser();
			landingPage = new LandingPage(driver); 
			landingPage.goTo();
			return landingPage;
		}
		
		@AfterTest(alwaysRun=true)
		public void tearDown()
		{
			driver.close();
		}
		
	
	
	
	
	
	
	
}
