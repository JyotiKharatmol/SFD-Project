package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Selenium.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver; // instance variable
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	
	@FindBy(css ="tr td:nth-child(3)")
	List<WebElement> productNames ;

	
	public List<WebElement> productNames()
	{
		return productNames;
	}
	
	public Boolean VerifyOrderProductName(String productName)
	{
		Boolean match  = productNames().stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	
	
	
	
	
}
