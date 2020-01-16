package Selenium.Figosoft.testing.executionClass;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Selenium.Figosoft.testing.uiStore.AmazonHomePageXpaths;
import Selenium.Figosoft.testing.utility.ScreenShot;

public class AmazonHomePageMethods extends AmazonHomePageXpaths{

	public static WebDriver driver;
	static WebDriverWait wait;
	static ExtentTest test;
	static String testId;
	static String input;
	
	private static void enterIntoSearchField() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
		driver.findElement(searchField).sendKeys(input);
		ScreenShot.attachScreenshot(test, testId, driver, "Product entered for search");
	}
	
	private static void clickSearchButton() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchButton)));
		driver.findElement(By.xpath(searchButton)).click();
		ScreenShot.attachScreenshot(test, testId, driver, "Search Button Clicked");

	}
	
	private static void selectProduct(String subString1,String subString2,String subString3) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(searchProduct)));
		List<WebElement> e=driver.findElements(By.xpath(searchProduct));
		
		for(WebElement element:e) {
			
			if(element.getText().contains(subString1)&&element.getText().contains(subString2)&&element.getText().contains(subString3))
			{
				driver.findElement(By.xpath(searchProduct)).click();
				break;
			}
		
		}
		
		ScreenShot.attachScreenshot(test, testId, driver,"Product found and selected");

	}
	
	private static String getPrice() throws IOException, InterruptedException {
		
		String currentWindowHandle = driver.getWindowHandle();
		 Set<String> windowHandles = driver.getWindowHandles();

		    for (String window:windowHandles){

		        //if it contains the current window we want to eliminate that from switchTo();
		        if (!currentWindowHandle.equalsIgnoreCase(window)){
		            //Now switchTo new Tab.
		            driver.switchTo().window(window);
		    
		            wait.until(ExpectedConditions.visibilityOfElementLocated(price));
		    	    ScreenShot.attachScreenshot(test, testId, driver, "Price found");
		    	    return driver.findElement(price).getText();	
		        }
		    }
		    
		    return "Not found";    
	}
	
	public  static String amazonHomePageAllMethods(WebDriver driver1, ExtentTest test1, String testId1,String input1,String subString1,String subString2,String subString3) throws IOException, InterruptedException {
		
		driver = driver1;
		test = test1;
		testId = testId1;
		wait = new WebDriverWait(driver, 10);
		input=input1;

		enterIntoSearchField();
		clickSearchButton();
		selectProduct(subString1,subString2,subString3);
		return getPrice();
	}
}
