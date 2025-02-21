package easyStockUtilities;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class AppiumUtils {

	public AppiumDriverLocalService service;
	
	
	
	
	private static final Random random = new Random();
	protected static SoftAssert softAssert;
	
	// Helper method to get SoftAssert instance
    protected SoftAssert getSoftAssert() {
    	softAssert = new SoftAssert();
        return softAssert;
    }
    
    // Method to verify soft assertions
    protected void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }
    
    // Reset soft assert (useful between test methods)
    protected void resetSoftAssert() {
        softAssert = new SoftAssert();
    }
	
	
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
	
	//Utility Methods for XML Files
	 public static List<Map<String, String>> readXmlData(String filePath) {
	        List<Map<String, String>> dataList = new ArrayList<>();

	        try {
	            File file = new File(filePath);
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(file);

	            document.getDocumentElement().normalize();
	            NodeList rows = document.getElementsByTagName("Row");

	            for (int i = 0; i < rows.getLength(); i++) {
	                Node node = rows.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    Map<String, String> dataMap = new HashMap<>();

	                    NodeList childNodes = element.getChildNodes();
	                    for (int j = 0; j < childNodes.getLength(); j++) {
	                        Node childNode = childNodes.item(j);
	                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
	                            Element childElement = (Element) childNode;
	                            dataMap.put(childElement.getNodeName(), childElement.getTextContent());
	                        }
	                    }
	                    dataList.add(dataMap);
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return dataList;
	    }	
		
	 
	 public static List<String> readAsciiData(String filePath) {
	        List<String> dataList = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                dataList.add(line.trim());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return dataList;
	 }
	 
	 
	 public void tapAtPosition(int x, int y, FlutterAndroidDriver driver) {
		 PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence tap = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
	            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(new Pause(finger, Duration.ofMillis(200)))
	            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        driver.perform(Collections.singletonList(tap));
	    }

	 
	 
	 //Methods to get Opening or Closing Stock
	 
		
	
	
	
}
