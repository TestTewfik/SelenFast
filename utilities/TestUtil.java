package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.w2a.base.TestBase;

public class TestUtil extends TestBase{

	public static void takeScreenShot()  {
		String directory = projectPath+"/src/test/resources/screenShots/";
		//String fileName = getRandomString(10)+".png";
		String fileName = "ScreenShot"+getDate()+".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);											 
		
			try {
				FileUtils.copyFile(scrFile, new File(directory + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	public static String getDate() {
		final DateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		return(sdf.format(date));
	}
	
}
