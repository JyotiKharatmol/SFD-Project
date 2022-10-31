package Selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver; // instance variable
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	
	
	//WebElements
	
	@FindBy(id ="userEmail")
	WebElement userEmail ;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String errorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	//Action methods
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE),email);
		userPassword.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE), password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
