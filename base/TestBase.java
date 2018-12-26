package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import extentReport.ExtentManager;

public class TestBase {

	
	public static WebDriver driver = null;
	public static Properties prop ;
	public static FileInputStream fis ;
	public static String projectPath = System.getProperty("user.dir");
	public static final Logger logger = LogManager.getLogger(TestBase.class.getName()) ;
	public static WebDriverWait wait;
	public  ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	@BeforeSuite
	public void setUp()  {
		
		if (driver == null) {
			
			
			prop = new Properties();
			
			try {
				fis = new FileInputStream(projectPath+"/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/executables/chromedriver");
				driver = new ChromeDriver();
				
			} else if(prop.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",projectPath+"/src/test/resources/executables/geckodriver");
				driver = new FirefoxDriver();
			}
			
			
			driver.get(prop.getProperty("baseUrl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("explicitWait")));
			logger.info("********** Test started *********");
			//test = ExtentManager.getInstance().createTest(getClass().getName());
			
		}
		
		
		
		
	}
	
	public boolean isElementPresent(By by) {
	
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
		
		
	} 
	
	

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			logger.info("********** Test finished *********");
			driver.quit();
		}
	}

}
