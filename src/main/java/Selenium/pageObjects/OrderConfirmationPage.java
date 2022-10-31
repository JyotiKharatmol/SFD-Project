package Selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{

	WebDriver driver; // instance variable
	
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(css="h1")
	WebElement confirmMessage;


	public String OrderConfirmMessage()
	{
		String confirmationMessage = confirmMessage.getText();
		
		return confirmationMessage;
	}
	
	
	
	
	
	
}
