package Selenium.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Selenium.pageObjects.MyCartPage;
import Selenium.pageObjects.ProductCatalogue;
import Selenium.testsComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest
{
		@Test(groups= {"ErrorHandling"}) // retryAnalyzer=Retry.class)
		
		public void LoginPageErrorValidation() throws IOException, InterruptedException
		{
			landingPage.loginApplication("jyoti@gmail.com", "7718928797@1pP" );
			Assert.assertEquals(landingPage.errorMessage(), "Incorrect email  password.");
		}

		@Test
		public void ProductCataloguePageErrorValidation() throws IOException, InterruptedException
		{
			String productName = "ZARA COAT 3";
			ProductCatalogue productCatalogue  = landingPage.loginApplication("jyoti@gmail.com", "7718928697@1pP" );
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			MyCartPage myCart = productCatalogue.goToCartPage();
			List<WebElement> cartProducts = myCart.CartProducts();
			Boolean match = myCart.VerifyProductDisplay("ZARA COAT 3");
			Assert.assertTrue(match);	
		}
		
		
		
		
}

/* Login Page Error Validations - Test Cases in One Java
 * Product Catalog Page Test Cases - in Another Java class
 * Cart Page Test Cases in another Java Class
 */
