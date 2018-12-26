package utils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Exceldataprovider {

	WebDriver driver = null;
	@BeforeTest
	public void setUp() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/src/main/resources/browsers/chromedriver");
		driver = new ChromeDriver();
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.close();
	}
	@Test(dataProvider="test1data")
	public void test1(String username, String password) throws InterruptedException {
		
		//System.out.println(username +" | "+password);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Assert.assertEquals("Invalid credentials", driver.findElement(By.id("spanMessage")).getText());
		//verifiyEquals("Invalid credentials1", driver.findElement(By.id("spanMessage")).getText());
		Thread.sleep(2000);
	}
	
	
	
	
	
	


	@DataProvider(name="test1data")
	public  Object[][] getData() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath+"/src/test/resources/excel/data.xlsx";
		
		
		Object data [][] = testdata(excelPath, "Sheet1");
		return data;
	}
	
	
	public  Object[][] testdata(String excelPath, String sheetName) throws IOException {
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		     //String cellData;
		Object data [][] = new Object[rowCount-1][colCount];
		
		for (int i = 1; i < rowCount; i++) {
			
			for (int j = 0; j < colCount; j++) {
//				cellData = excel.getCellDataString(i, j);	
//				data[i-1][j] = cellData;
				
				data[i-1][j]  = excel.getCellDataString(i, j);	
				
			}
			
		}
		return data;
		
	}
}
