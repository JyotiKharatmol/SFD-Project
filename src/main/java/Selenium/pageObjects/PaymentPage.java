package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Selenium.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{

	WebDriver driver; // instance variable
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	
	@FindBy(css=".form-group input")
	WebElement selectCountryField;
	
	@FindBy(css=".list-group")
	WebElement countryList;
	
	By CountryList = By.cssSelector(".list-group");
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement Country;
	
	@FindBy(css="a.btnn")
	WebElement placeOrder;
	
	

	public void SelectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(selectCountryField, countryName).build().perform();
		waitForElementToAppearBy(CountryList);
		Country.click();	
	}
	public OrderConfirmationPage placeOrder()
	{
		placeOrder.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}
	
	
	
	
	
	
}
