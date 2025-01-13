package easyStockTestCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import easyStockPageObjects.LoginPage;
import easyStockUtilities.AndroidActions;
import easyStockUtilities.XLUtils;

public class TC_LoginDDT_001 extends BaseClass {
    
    public TC_LoginDDT_001() {
        setSkipLogin(true);
        setSkipProfileClick(true);
    }
    
    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd, String currurl) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        AndroidActions action = new AndroidActions(driver);
        
        logger.info("Testing with Username: " + user +" & password as:"+ pwd);
        
        try {
            // Perform login steps
            performLoginSteps(lp, action, user, pwd);
            
           
            
            // Check login result and handle accordingly
            handleLoginResult(lp,action,currurl);
            
        } catch (Exception e) {
            logger.error("Test failed with exception: " + e.getMessage());
           
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    private void performLoginSteps(LoginPage lp, AndroidActions action, String user, String pwd) throws InterruptedException {
        // Username entry
        //lp.setUserNameclick();
        //lp.setUserNameclear();
        lp.setUserName(user);
        logger.info("Username entered");
        
        // Password entry
        //lp.setPasswordClick();
        //lp.setPasswordClear();
        lp.setPassword(pwd);
        logger.info("Password entered");
        
        // Submit login
        action.scrollTwithpercentAction(1);
        lp.clicksubmit();
        logger.info("Login button clicked");
        
        
    }
    
    private void handleLoginResult(LoginPage lp,AndroidActions action,String currurl) throws InterruptedException 
    {
        
    	 if (lp.isLoginSuccessful()) {
    		 
    		 if(currurl.equalsIgnoreCase("Valid")) {
    	            softAssert.assertTrue(true,"passed");
    		 }else {
    			 
    			 softAssert.assertTrue(false,"failed");   
    		       }
    		 
            logger.info("Login successful - proceeding with logout");
           
            performLogout(lp);
           }
    
          
    	else if (lp.isUserNotFoundMessageDisplayed()) {
    		
    		if(currurl.equalsIgnoreCase("Invalid")) {
    			
    			softAssert.assertTrue(true, "Test passed - UserNotFound");
	            
		 }else {
			 
			 softAssert.assertTrue(false,"failed");   
		       }
    		
            logger.info("Expected negative test case - UserNotFound");
            
            
        }
        else if (lp.isInvalidMobileNumberDisplayed() || lp.isInvalidPasswordDisplayed()) {
        	
             if(currurl.equalsIgnoreCase("Invalid")) {
    			
	            softAssert.assertTrue(true, "Test passed - Invalid credentials detected as expected");
	            
		 }else {
			 
			 softAssert.assertTrue(false,"failed");   
		       }
            logger.info("Expected negative test case - invalid credentials");
           
        }
       
        else {
            logger.error("No expected message found on screen");
            softAssert.fail("Test failed - Could not determine login outcome");
        }
    }
    
    private void performLogout(LoginPage lp) throws InterruptedException {
        
    	
    	softAssert.assertTrue(lp.isprofilebtndisplayed(), "Login Successfull");
        lp.setprofilebtn();
        Thread.sleep(1000);
        lp.setlogoutbtn();
        lp.setyes();
        Thread.sleep(5000);
    }
    
    @DataProvider(name = "LoginData")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/easyStockTestData/LoginData.xlsx";
        int rownum = XLUtils.getRowCount(path, "Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
        
        String logindata[][] = new String[rownum][colcount];
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
            }
        }
        return logindata;
    }
}