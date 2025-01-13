package easyStockTestCases;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import easyStockPageObjects.LoginPage;
import easyStockUtilities.AppiumUtils;
import easyStockUtilities.ReadConfig;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class BaseClass extends AppiumUtils {
	
	ReadConfig readconfig=new ReadConfig();
	
	public String IPAdress=readconfig.getIPAdress();
	public String port=readconfig.getport();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public String branchnm=readconfig.getBranchName();
	public String user=readconfig.getUser();
	public String usernumber=readconfig.getUsernumber();
	
	public Logger logger;
	public static FlutterAndroidDriver driver;
	protected UiAutomator2Options options;
	public AppiumDriverLocalService service;
	 protected static SoftAssert softAssert; 
	
	 private boolean skipLogin = false;
	 private boolean skipProfileClick = false;    
	 
	    // Method to set skip login flag
	    protected void setSkipLogin(boolean skip) {
	        this.skipLogin = skip;
	    }
	    
	    protected void setSkipProfileClick(boolean skip) {
	        this.skipProfileClick = skip;
	    }

	    
	//@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup() throws URISyntaxException, InterruptedException, FindFailed, IOException 
	{
		//   ---Command to check package activity----- adb shell dumpsys window | find "mCurrentFocus" 
        //---Homepage---mCurrentFocus=Window{5a9dec7 u0 com.mwb.easystocks/com.mwb.easystocks.MainActivity}
		
		/*   Activity activity = new Activity("com.mwb.easystocks", "com.mwb.easystocks.MainActivity");
		driver.startActivity(activity);..*/
		

		  softAssert = new SoftAssert();
		
	logger= Logger.getLogger("EasyStock_V1");   //....Logger implementation
	PropertyConfigurator.configure("./target/Log4j.properties");
		
	//service=new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Admin//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
	//service.start();
		
	
	service=startAppiumServer(IPAdress, Integer.parseInt(port));
	
		
	 
	 
	    options = new UiAutomator2Options();
		                                   //Samsung SM-M36B API 34 , Nothing A015 API 34  ,OPPO CPH2239 API 20, Xiaomi 23028RN4DI
		options.setCapability("deviceName", "Xiaomi 23028RN4DI"); //("Pixel 9 API 35");Pixel 9 pro API 33
		options.setCapability("app", System.getProperty("user.dir") + "/src/test/java/resources/ES_1100_0301_MWBS.apk");
		//options.setCapability("app", "E://Product Management//EasyStock_V1//src//test//java//resources//ES_1100_0301_MWBS.apk");
		options.setAutoGrantPermissions(true);
		options.setCapability("unicodeKeyboard", true);
        options.setCapability("resetKeyboard", true);
		
		
		 //driver = new FlutterAndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		 driver = new FlutterAndroidDriver(service.getUrl(), options);
		
        FluentWait<FlutterAndroidDriver> wait =new FluentWait<FlutterAndroidDriver>(driver);
		wait.withTimeout(Duration.ofMinutes(1));
		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);
		
		if (!skipLogin) {
            performLogin();
        }
		
		/* Web application Implementation starts here.....//rdriver=new ChromeDriver();
		
		if(br.equals("chrome")) 
		{
		rdriver=new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			
			rdriver=new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			rdriver=new InternetExplorerDriver();
			
		}
	
		
		
		logger= Logger.getLogger("EasyStock_V1");   //....Logger implementation
		PropertyConfigurator.configure("./target/Log4j.properties");
		
		Thread.sleep(3000);
		rdriver.get(baseURL);....*/
		
		
	}
	
	//@BeforeClass(dependsOnMethods = "setup",alwaysRun = true)
	public void performLogin() throws InterruptedException, IOException, FindFailed {
	
		LoginPage lp = new LoginPage(driver);
		logger.info("Portal is opened");
		Thread.sleep(1000);

		//lp.setUserNameclick();

		lp.setUserName(username);
		// Thread.sleep(3000);
		logger.info("Entered username successfully");
		// driver.hideKeyboard();
		// Thread.sleep(2000);

		//lp.setPasswordClick();
		// Thread.sleep(2000);

		lp.setPassword(password);
		logger.info("Entered Password successfully");
		// driver.hideKeyboard();

		//Thread.sleep(1000);
		lp.clicksubmit();
		logger.info("Button submitted successfully");
		Thread.sleep(6000);

		//lp.setprofilebtn();
		//logger.info("clicked on profile");
		
	}
	
	// Helper method to get SoftAssert instance
    protected SoftAssert getSoftAssert() {
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
    
    @AfterMethod(alwaysRun = true)
    protected void afterMethod() {
        // Verify all soft assertions after each test method
        assertAll();
        // Reset for next test
        resetSoftAssert();
    }
    
    @BeforeMethod(alwaysRun = true)
    protected void beforeEachMethod() throws InterruptedException, IOException, FindFailed {
        if (!skipProfileClick) {
            // Profile click before each test method
            LoginPage lp = new LoginPage(driver);
            lp.setprofilebtn();
            logger.info("Clicked on profile before test method execution");
        }
    }
	
    @AfterMethod(alwaysRun = true)
    protected void afterEachMethod() throws InterruptedException {
        // Your existing afterMethod code for soft assertions
        assertAll();
        resetSoftAssert();
        
        if (!skipProfileClick) {
        	try {
                LoginPage lp = new LoginPage(driver);
                
                // Keep clicking back while the back button is visible
                while (lp.isBackButtonVisible()) {
                    lp.ClickBack();;
                    logger.info("Clicked back button");
                    Thread.sleep(500); // Small wait to allow UI to update
                }
                
                // Check and click final back if visible
                if (lp.isFinalBackVisible()) {
                    lp.FinalBack();
                    logger.info("Clicked final back button");
                }
                
            } catch (Exception e) {
                logger.error("Error during navigation: " + e.getMessage());
            }
        }
    }
	
	
	@AfterClass(alwaysRun = true)
	public void teardown() throws InterruptedException
	{    Thread.sleep(5000);
	    
            driver.quit();
            service.stop();
     }
		
		
	  
	
	

	public String captureScreen(String tname) throws IOException, WebDriverException {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd. HH.mm.ss").format(new Date());
				
		
        TakesScreenshot ts =(TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath =System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" +timeStamp+".png";
		File targetFile =new File(targetFilePath);

		
		sourceFile.renameTo(targetFile);
		
		System.out.println("Screenshot taken");
		return targetFilePath;
		
		
	}
	

}
