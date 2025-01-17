package easyStockTestCases;

import java.io.IOException;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;

public class TC_Login_001 extends BaseClass {
	
	public TC_Login_001() {
        // Call this in the constructor to skip login for this test class
        setSkipLogin(true);
        setSkipProfileClick(true);
    }
	
	@Test()
	public void loginTest() throws InterruptedException, IOException, FindFailed {
		//AndroidActions scroll = new AndroidActions(driver);
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Portal is opened");
		
        Thread.sleep(3000);
		//lp.setUserNameclick();
		//lp.setpermisionallow();

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
		
		performLogout(lp);

		/*	// ..scroll for visible text
		// ----driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(new
		// UiSelector().textContains(\"Logout\").instance(0));"));

		/*..scroll.scrollTwithpercentAction(1.0);

		
		 * ......to scroll until end----( boolean canScrollMore; {
		 * canScrollMore=(Boolean)
		 * ((JavascriptExecutor)driver).executeScript("mobile: scrollGesture"
		 * ,ImmutableMap.of(
		 * 
		 * "left", 100, "top", 200, "width",200, "height", 200, "direction", "down",
		 * "percent", 1.0 )); }while(canScrollMore);.....)
		 
		// ...to scroll for text
		// visible----driver.findElement(AppiumBy.androidUIAutomator("new
		// UiScrollable(new UiSelector()).scrollIntoView(text(\"Logout\"));"));

		Thread.sleep(2000);
		WebElement logout = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Logout\"]"));
		logout.click();
		// lp.setlogoutbtn();
		logger.info("clicked on Logout");

		Thread.sleep(1000);
		driver.findElement(FlutterBy.xpath("//android.widget.Button[@content-desc=\"Yes\"]")).click();
		// lp.setyes();
		logger.info("clicked on yespopup");
		Thread.sleep(1000);

		if (driver.getClipboardText().equals("OrangeHRM")) {
			Thread.sleep(3000);
			Assert.assertTrue(true);
			logger.info("Title verified successfully");
		} else {
			Thread.sleep(3000);

			Assert.assertTrue(false);
			logger.error("Title not matching");
			logger.debug("Debug Logs...");

		}  */

	}
private void performLogout(LoginPage lp) throws InterruptedException {
        
    	
    	softAssert.assertTrue(lp.isprofilebtndisplayed(), "Login Successfull");
        lp.setprofilebtn();
        
        Thread.sleep(1000);
        lp.setlogoutbtn();
        lp.setyes();
        Thread.sleep(5000);
    }

}
