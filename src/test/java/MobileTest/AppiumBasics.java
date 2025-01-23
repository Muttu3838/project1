/*package MobileTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy.FlutterBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics {
	
	
	@Test
	public void AppiumTest() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		
		
		/*DesiredCapabilities capabilities=new DesiredCapabilities();
		
		capabilities.setCapability("appium:deviceName", "Pixel 9 API 35");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appium:automationNam", "uiautomator2");
		capabilities.setCapability("appium:app", "E:\\Appium\\ES_312_1110_MS.apk");
		capabilities.setCapability("platformversion", "13");
		
		
		
		AppiumDriverLocalService service=new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Admin//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
	 service.start();
		
	/*... DesiredCapabilities capabilities=new DesiredCapabilities();
	 capabilities.setCapability("deviceName", "Pixel 9 API 35");
	 capabilities.setCapability("app", "\"E://Product Management//ProductionManagement_V1//src//test//java//resources//ES_1402_1912_MS.apk\"");
	 
	 //FlutterDriverOptions options=new FlutterDriverOptions();
		UiAutomator2Options options=new UiAutomator2Options();
	
		options.setCapability("deviceName", "Pixel 9 API 35"); //("Pixel 9 API 35");
		options.setCapability("app", "E://Product Management//ProductionManagement_V1//src//test//java//resources//ES_1402_1912_MS.apk");
		options.setAutoGrantPermissions(true);
		
		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		 
		
		
		Thread.sleep(3000);
		
	
		WebElement username= driver.findElements(FlutterBy.className("android.widget.EditText")).get(0);
		username.click();
		username.sendKeys("8660310566");
		
		username.sendKeys(Keys.ENTER);
		
		
	
		Thread.sleep(2000);
		
		WebElement password=driver.findElements(FlutterBy.className("android.widget.EditText")).get(1);
		password.click();
		password.sendKeys("Test@123");
		driver.hideKeyboard();
		Thread.sleep(2000);
		driver.findElement(FlutterBy.className("android.widget.Button")).click();
	Thread.sleep(4000);
		driver.findElement(FlutterBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView")).click();
		Thread.sleep(2000);
		
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrolaable(new Uiselector()).scrollIntoView(text(\"Logout\");"));
		//Thread.sleep(2000);
		driver.findElement(FlutterBy.xpath("//android.view.View[@content-desc=\"Logout\"]")).click();
		Thread.sleep(3000);
		driver.findElement(FlutterBy.xpath("//android.widget.Button[@content-desc=\"Yes\"]")).click();
		Thread.sleep(3000);
		
	 
	   service.stop();
	}
	
	// Example usage in your test framework
	 * 
	 */

/*  @Test
public void verifyInventoryData() {
    try {
        // Parse the inventory file
        List<InventoryItem> inventory = InventoryFileParser.parseInventoryFile("path/to/Dm Opening Stk.txt");
        
        // Example 1: Print all items
        for (InventoryItem item : inventory) {
            System.out.println(item);
        }
        
         // Get all unique items
        List<String> allItems = InventoryFileParser.getAllItems(inventory);
        
        // Print all items
        System.out.println("All Items in Inventory:");
        for (String item : allItems) {
            System.out.println(item);
        }
        
        // Example 2: Get items in Masala Godown
        List<InventoryItem> masalaItems = InventoryFileParser.getItemsByLocation(inventory, "Masala Godown");
        
        // Example 3: Get total quantity for a specific item
        double totalUridDal = InventoryFileParser.getTotalQuantityForItem(inventory, "Uriddal President 30kg");
        
        // Example 4: Get all unique locations
        List<String> allLocations = InventoryFileParser.getAllLocations(inventory);
        
        // Add your assertions here
        Assert.assertEquals(60.0, totalUridDal, 0.01);
        Assert.assertTrue(allLocations.contains("Masala Godown"));
    } catch (IOException e) {
        e.printStackTrace();
        Assert.fail("Failed to parse inventory file: " + e.getMessage());
    }
}
/* Key features of this implementation:

Handles quoted strings correctly
Parses quantity and unit separately
Maintains relationship between items and their locations
Provides utility methods for common queries
Uses a clean object-oriented design
Handles multiple locations for the same item
Easy to integrate with testing frameworks
Would you like me to:

Add more utility methods for specific verifications?
Modify the parsing logic to handle different file formats?
Add validation checks for the data?
Add methods to export the data in different formats?
 Copy
Retry



Claude can make mistakes. Please double-check responses. */

/*  @Test
public void testUniqueItems() {
    try {
        // Get all unique items with their total quantities
        List<ItemParser.Item> items = ItemParser.parseUniqueItems("path/to/Dm Opening Stk.txt");
        
        // Print all items
        ItemParser.printAllItems(items);
        
        // Example: Check specific item
        ItemParser.Item uridDal = ItemParser.getItemByName(items, "Uriddal President 30kg");
        Assert.assertNotNull(uridDal);
        Assert.assertEquals(60.0, uridDal.getTotalQuantity(), 0.01);
        
        // Use in your framework
        for (ItemParser.Item item : items) {
            System.out.println(String.format("Item: %s, Total Quantity: %.2f %s",
                item.getItemName(),
                item.getTotalQuantity(),
                item.getUnit()));
        }
        
    } catch (IOException e) {
        e.printStackTrace();
        Assert.fail("Failed to parse items: " + e.getMessage());
    }
}  */

