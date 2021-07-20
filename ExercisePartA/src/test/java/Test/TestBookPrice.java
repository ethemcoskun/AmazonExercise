package Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import amazonPages.CheckoutPage;
import amazonPages.HomePage;
import amazonPages.ProductsPage;
import configuration.DriverSingleton;

public class TestBookPrice {

	private WebDriver driver;
	private HomePage homePageObj;
	private ProductsPage productsPageObj;
	private CheckoutPage checkoutPageObj;
	private DriverSingleton driverSingleton;
	
	//Initializing the driver which I created by implementing a singleton pattern
	//This way, all driver instances will be the same for all our Page Object Model Classes
	@BeforeTest
	public void setupTest() {
		driverSingleton = DriverSingleton.getInstance();
		driver = driverSingleton.setupDriver();		
		driver.get("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	//Terminate the browser when we are done testing the website
	@AfterTest
	public void finish() {
		if(driver != null)
			driver.quit();
	}
	
	@Test
	public void test() throws Exception {
		
		homePageObj = new HomePage(driver);
		homePageObj.setDropDown("Books");
		homePageObj.searchForItem("qa testing for beginners");
				
		//Since there are random number of sponsored listings can be displayed on top of the page,
		//the actual book that I was asked to click on is displayed can show up in different orders.
		//I am giving index 0 for the first item. However, this is not the best approach,
		//random items should be considered and there should be created a better approach to handle this.
		String listPrice = homePageObj.getPrice(0);	
		homePageObj.clickOnItem(0);

		//The program now opens the page for the product which we clicked on
		productsPageObj = new ProductsPage(driver);
		String productPrice = productsPageObj.getPrice();

		//Lets assert to make sure that the price is the same
		Assert.assertEquals(productPrice, listPrice);
		productsPageObj.addToCart();
		
		//Now the website navigates to the Checkout Page (Assuming that you are logged in, otherwise it will ask you to login)
		checkoutPageObj = new CheckoutPage(driver);
		//Here is the final verification for the price
		String checkoutPrice = checkoutPageObj.getCheckoutPrice();
		Assert.assertEquals(checkoutPrice, listPrice);
	}
}
