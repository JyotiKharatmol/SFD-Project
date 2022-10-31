package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver; // instance variable
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	
	@FindBy(css =".mb-3")
	List<WebElement> products ;
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	@FindBy(css=".ng-animating")
	WebElement loadingIcon;
	
	@FindBy(css="#toast-container")
	WebElement toastMessageShow;
	
	By toastMessage = By.cssSelector("#toast-container");
	
	By productElements = By.cssSelector(".mb-3");
	
	By cartButton = By.cssSelector("[routerlink*='cart']");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productElements);
		return products;
	}

	public WebElement getProductByName(String productName)
	{
		WebElement prod =getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		getProductByName(productName).findElement(addToCart).click();
		waitForElementToDisappear(loadingIcon);
		waitForElementToAppearBy(toastMessage);
		waitforElementToBeClickable(cartButton);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
