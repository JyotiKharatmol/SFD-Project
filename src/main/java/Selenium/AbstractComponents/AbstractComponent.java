package Selenium.AbstractComponents;

import java.time.Duration;
//Note: This used to store the reusable utilities
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.pageObjects.LandingPage;
import Selenium.pageObjects.MyCartPage;
import Selenium.pageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
	}

	public void waitForElementToAppear(By findby)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
			
	   w.until(ExpectedConditions.visibilityOf(driver.findElement(findby)));	
	}
	public void waitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
			
	   w.until(ExpectedConditions.visibilityOf(findby));  
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		//WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		//w.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(1000);
	}
	
	public void waitForElementToAppearBy(By e)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(50));
			
		w.until(ExpectedConditions.visibilityOfElementLocated(e));
	}

	public void waitforElementToBeClickable(By findby)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		w.until(ExpectedConditions.elementToBeClickable(findby));
	}
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement CartButton ;
	
	@FindBy(css ="[routerlink='/dashboard/myorders']")
	WebElement orderHeader ;
	
	@FindBy(css="ul li:last-of-type")
	WebElement signOut;
	
	
	public MyCartPage goToCartPage()
	{
		CartButton.click();
		MyCartPage myCart = new MyCartPage(driver);
		return myCart;
	}
	public OrdersPage goToOrdersPage()
	{
		orderHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
	public LandingPage signOut()
	{
		signOut.click();
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
		
	}
	
	
	
	
}
