package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {

	private static DriverSingleton dsInstance = null;
	private WebDriver driver;
	
	private DriverSingleton() {
		
	}
	
	public WebDriver setupDriver(){
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static DriverSingleton getInstance(){
        if(dsInstance == null){
        	dsInstance = new DriverSingleton();
        }
        return dsInstance;
    }
}
