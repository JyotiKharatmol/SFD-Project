package Selenium.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.pageObjects.MyCartPage;
import Selenium.pageObjects.OrderConfirmationPage;
import Selenium.pageObjects.OrdersPage;
import Selenium.pageObjects.PaymentPage;
import Selenium.pageObjects.ProductCatalogue;
import Selenium.testsComponents.BaseTest;

public class SubmitOrderTest extends BaseTest
{
		String productName = "ZARA COAT 3";
	
		@Test(dataProvider= "getData", groups = {"Purchase"})
		public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
		{
			String countryName = "Ind";
			ProductCatalogue productCatalogue  = landingPage.loginApplication(input.get("email"), input.get("password"));
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(input.get("product"));
			MyCartPage myCart = productCatalogue.goToCartPage();
			List<WebElement> cartProducts = myCart.CartProducts();
			Boolean match = myCart.VerifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			PaymentPage paymentPage = myCart.CheckOut();
			paymentPage.SelectCountry(countryName);
			OrderConfirmationPage orderConfirmationPage = paymentPage.placeOrder();
			String confirmationMessage = orderConfirmationPage.OrderConfirmMessage();
			Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			orderConfirmationPage.signOut();
		}
		
		@Test(dependsOnMethods= {"SubmitOrder"})
		public void OrderHistoryTest()
		{
			OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
			OrdersPage ordersPage = orderConfirmationPage.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderProductName(productName));	
		}
	
	
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String,String>> data = getDataMap(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\data\\PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{ data.get(1)}};
			
		}
		

		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "jyoti@gmail.com");
		map.put("password", "7718928697@1pP");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "testing@gmail.co");
		map1.put("password", "Register@Password1");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		
		
		/*	@DataProvider
			public Object[][] getData()
		{
		return new Object[][] {{"jyoti@gmail.com","7718928697@1pP","ZARA COAT 3" },{"testing@gmail.co","Register@Password1", "ADIDAS ORIGINAL"} };
					
		}*/	
		
		
		/*
		 * check depends on attribute - above 2nd test 
		 */
		
		
		
		
		
		
		
		
		
		

}
