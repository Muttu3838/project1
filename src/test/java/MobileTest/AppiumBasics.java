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

}*/
