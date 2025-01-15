package easyStockUtilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;

public class AndroidActions extends AppiumUtils{
	
	FlutterAndroidDriver driver;
	
	public AndroidActions(FlutterAndroidDriver driver)
	{
	
		this.driver = driver;
	}
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	
	
	
	public void scrollToEndAction()
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 1.0
			    
			));
		}while(canScrollMore);
	}
	
	
	public void scrollToText(String text)
	{
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	public void scrollTwithpercentAction(int d)
	{
		 driver.executeScript("mobile: scrollGesture",ImmutableMap.of(
					"left", 100, "top", 200, "width",200,  "height", 200,
					"direction", "down",
					"percent", d
					));
	}
	
	public void scrollToElement(WebElement element) {
	    boolean canScrollMore = true;
	    while (canScrollMore) {
	        try {
	            if (element.isDisplayed()) {
	                return;
	            }
	        } catch (Exception e) {
	            // Element not visible yet, continue scrolling
	            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", 
	                ImmutableMap.of(
	                    "left", 100, "top", 100, "width", 200, "height", 200,
	                    "direction", "down",
	                    "percent", 10
	                ));
	        }
	    }
	}
	
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			 
			    "direction", direction,
			    "percent", 0.75
			));
		
		
	}
	
	public void swipeToEndAction(WebElement ele,String direction)
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					"elementId", ((RemoteWebElement)ele).getId(),
					 
				    "direction", direction,
				    "percent", 0.75
				));
		 
		}while(canScrollMore);
	}
	
	public void scrollDown(String direction) {
	        try {
	            driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
	                "direction", direction,
	                "percent", 0.8
	            ));
	            Thread.sleep(1000);
	        } catch (Exception e) {
	            System.out.println("Alternative scroll failed: " + e.getMessage());
	        }
	    }

}
