package Selenium.Figosoft.testing.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class ScreenShot {
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
	static Date date;

	static Properties allObjects;
	
	/**
	 * This method takes the screenshot
	 * @param driver
	 * @param testId
	 * @throws IOException
	 */
	public static String takeScreenShot(WebDriver driver, String testId) throws IOException {
		
		date = new Date();
		String datetoString=dateFormat.format(date); 
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"\\ScreenShots\\screenshot(" +testId+")-" +datetoString +".png";
		File dest = new File(destination);
		Files.copy(source, dest);
		return destination;
	}

	/**
	 * This method calls takeScreenshot and attaches the screenshot at each testStep
	 * @param test
	 * @param testId
	 * @param driver
	 * @param testStep
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void attachScreenshot(ExtentTest test, String testId, WebDriver driver,String testStep) throws IOException, InterruptedException {

		String path = ScreenShot.takeScreenShot(driver, testId);
		test.log(Status.PASS, "Test Id: "+ testId+" => "+testStep);
		test.pass("", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
	}
	
}
