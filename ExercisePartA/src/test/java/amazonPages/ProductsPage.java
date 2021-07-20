package amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Product display Page Object Class
public class ProductsPage {
	
	WebDriver driver;
	By product = By.id("newBuyBoxPrice");
	By addToCartButton = By.id("add-to-cart-button");
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPrice() {
		return driver.findElement(product).getAttribute("innerHTML");
	}
	
	public void addToCart() {
		driver.findElement(addToCartButton).click();
	}
	
}
