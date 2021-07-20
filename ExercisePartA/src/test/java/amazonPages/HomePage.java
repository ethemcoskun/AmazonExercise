package amazonPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//Home Page Object Class
public class HomePage {
	
	WebDriver driver;
	
	By searchBoxDropDown = By.id("searchDropdownBox");
	By searchBox = By.id("twotabsearchtextbox");
	By price = By.xpath("//*[@id=\"search\"]/div[1]/div/div[1]/div/span[3]/div[2]/div[2]/div/span/div/div/div/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div[1]/div[2]/a/span[1]/span[1]");
	By image = By.className("s-image");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setDropDown(String itemName) {		
		Select items = new Select(driver.findElement(searchBoxDropDown));
		items.selectByVisibleText(itemName);
	}
	
	public void searchForItem(String text) {		
		driver.findElement(searchBox).sendKeys(text + Keys.RETURN);
	}
	
	//To be able to get the price of the first item in the list, I got all the items first, and then selected the first book from the list
	public String  getPrice(int index) {	
		List<WebElement> items = driver.findElements(price);
		WebElement bookItem = items.get(index);
		return bookItem.getAttribute("innerHTML");
	}
	
	public void clickOnItem(int index) {
		List<WebElement> images = driver.findElements(image);
		WebElement bookImage = images.get(index);
		bookImage.click();
	}
}
