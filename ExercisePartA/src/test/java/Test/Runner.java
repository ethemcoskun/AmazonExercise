package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import amazonPages.CheckoutPage;
import amazonPages.HomePage;
import amazonPages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;

//I created this class to test my automation testing software to be able to quickly identify any errors or mistakes in my program.
//This software normally should be run by the testng.xml file by running as TestNG Suit
public class Runner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver;
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		HomePage homeObj = new HomePage(driver);
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		homeObj.setDropDown("Books");
		homeObj.searchForItem("qa testing for beginners");
		
		System.out.println(homeObj.getPrice(0));
		homeObj.clickOnItem(0);
		
		ProductsPage prodObj = new ProductsPage(driver);
		System.out.println(prodObj.getPrice());
		prodObj.addToCart();
		
		CheckoutPage checkoutObj = new CheckoutPage(driver);
		System.out.println(checkoutObj.getCheckoutPrice());
		
		driver.quit();
	}

}
