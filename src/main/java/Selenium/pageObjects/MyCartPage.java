package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Selenium.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent{

	WebDriver driver; // instance variable
	
	public MyCartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	
	@FindBy(css =".cartSection h3")
	List<WebElement> cartProducts ;
	
	@FindBy(css=".totalRow button")
	WebElement CheckOut;
	
	
	
	public List<WebElement> CartProducts()
	{
		return cartProducts;
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match  = CartProducts().stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
		
	}
	public PaymentPage CheckOut()
	{
		CheckOut.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}
	
	
	
	
	
	
	
}
