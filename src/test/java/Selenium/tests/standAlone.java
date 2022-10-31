package Selenium.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Selenium.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standAlone {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup(); //Updating chrome driver based on the auto-updated version of chrome browser
		
		WebDriver driver = new ChromeDriver(); //driver is an used to access the methods of chrome driver class which are within the webdriver interface
		String productName = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		LandingPage landingPage = new LandingPage(driver); // when you execute this line, the objects of landing page will be permitted to be accessed 
		//in this page and parameterized constructor of landing page will get executed.
		
		
		driver.findElement(By.id("userEmail")).sendKeys("jyoti@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("7718928697@1pP");
		driver.findElement(By.id("login")).click();
		
         WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		System.out.println(products.size());
	
		WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
				
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		
	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
	
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
/*	w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".form-group")));
	
	driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
	
	w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".list-group button")));
	List<WebElement> countryList = driver.findElements(By.cssSelector(".list-group button"));
	
	WebElement filteredCountry = countryList.stream().filter(country->country.getText().equals("India")).findFirst().orElse(null);
	
	filteredCountry.click();

	driver.findElement(By.cssSelector("div.actions a")).click();
	
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "THANKYOU FOR THE ORDER.");
	
	driver.findElement(By.cssSelector("label[routerlink='/dashboard/myorders']")).click(); */
	
	Actions a = new Actions(driver);
	
	a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "India").build().perform();
	
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
	
	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	
	driver.findElement(By.cssSelector("a.btnn")).click();
	
	String confirmMessage = driver.findElement(By.tagName("h1")).getText();
	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	driver.close();
	
	
	}

}
