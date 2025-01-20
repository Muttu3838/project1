package easyStockUtilities;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {

	public AppiumDriverLocalService service;
	
	
	private static final Random random = new Random();
	
	
	// Generates a random integer between min and max (inclusive)
	public static int generateRandomNumberAsInteger(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }
        return random.nextInt((max - min) + 1) + min;
    }
	
	// Generates a random integer between min and max (inclusive) and returns as String
	public static String generateRandomNumberAsString(int min, int max) {
        return String.valueOf(generateRandomNumberAsInteger(min, max));
    }
	
	 //Generates an array of random numbers between min and max (inclusive)
    public static int[] generateRandomNumbers(int min, int max, int count) {
       if (count < 0) {
           throw new IllegalArgumentException("count cannot be negative");
       }
       
       int[] numbers = new int[count];
       for (int i = 0; i < count; i++) {
           numbers[i] = generateRandomNumberAsInteger(min, max);
       }
       return numbers;
   }
    
    
    // Generates an array of random numbers as Strings between min and max (inclusive)
   public static String[] generateRandomNumbersAsString(int min, int max, int count) {
       if (count < 0) {
           throw new IllegalArgumentException("count cannot be negative");
       }
       
       String[] numbers = new String[count];
       for (int i = 0; i < count; i++) {
           numbers[i] = generateRandomNumberAsString(min, max);
       }
       return numbers;
   }
	
    // Method To Check is Element present or displayed
	public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    } 
	
	
	// Method To Check is Element enabled or disabled
	public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    } 
	
	
	// Method To Check is Element selected or not selected mainly for checkboxes
	public boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    } 

	
	
	// Method To Start Appium Server
	public AppiumDriverLocalService startAppiumServer(String ipAddress,int port)
	{
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Admin//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
					.withIPAddress(ipAddress).usingPort(port).build();
				service.start();
				return service;
	}
	
	// Method To wait for Element to Appear in page
	public void waitForElementToAppear(WebElement ele, AppiumDriver driver)
	{
		try {
			WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfAllElements((ele)));
			wait.withTimeout(Duration.ofSeconds(20));
			wait.pollingEvery(Duration.ofMillis(200));
			wait.ignoring(NoSuchElementException.class);
		}catch(Exception e)
		{
			return;
		}
		
	}
	
	// Method To wait for Element until it's clickable in page
	public void waitForElementTobeClickable(WebElement ele, AppiumDriver driver)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable((ele)));
		wait.pollingEvery(Duration.ofMillis(500));
	}
	
	// Method To wait for Element until it's Disappeared in page
	public void waitForElementTobeDisappear(WebElement ele, AppiumDriver driver)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		wait.pollingEvery(Duration.ofMillis(500));
	}
	
	
		
		
		
	
	
	
}
