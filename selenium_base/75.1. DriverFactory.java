package utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory  {
	public static WebDriver driver;
	
	public WebDriver getDriver() throws Exception {
		try {
			ReadConfigFile file = new ReadConfigFile();
			String browserName = file.getBrowser();
			
			switch (browserName) {
			
			//firefox setup
			case "firefox":
				if (null == driver) {
					System.setProperty("webdriver.gecko.driver", Constant.GECKO_DRIVER_DIRECTORY);
					DesiredCapabilities capabilities=DesiredCapabilities.firefox();
				    capabilities.setCapability("marionette", true);
				    driver = new FirefoxDriver();
				}
				break;
		
		   //chrome setup
			case "chrome":
				if (null == driver) {
					System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_DIRECTORY);
					//CHROME OPTIONS
					ChromeOptions options = new ChromeOptions();
					driver = new ChromeDriver(options);
					driver.manage().window().maximize();
				}
				break;
				
		   //IE setup	
			case "ie":
				if (null == driver) {
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					System.setProperty("webdriver.ie.driver", Constant.IE_DRIVER_DIRECTORY);
					caps.setCapability("ignoreZoomSetting", true);
					driver = new InternetExplorerDriver(caps);
					driver.manage().window().maximize();
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("Unable to load browser! - Exception: " + e.getMessage());
		} finally {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		}
		return driver;
	}
}
