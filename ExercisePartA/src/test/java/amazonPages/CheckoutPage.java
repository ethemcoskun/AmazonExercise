package amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Checkout Page Object Class
public class CheckoutPage {
	
	WebDriver driver;
	By cartTotal = By.xpath("//*[@id=\"hlb-subcart\"]/div[1]/span/span[2]");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getCheckoutPrice() {
		return driver.findElement(cartTotal).getAttribute("innerHTML");
	}
	
}
