package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.CreateAccountPage;

public class TC_CreateAccount_001 extends BaseClass {

	public TC_CreateAccount_001() {
        // Call this in the constructor to skip login for this test class
        setSkipLogin(true);
        setSkipProfileClick(true);
    }
	@Test
	public void createAccount() throws InterruptedException 
	
	{
		
	  CreateAccountPage ca=new	CreateAccountPage(driver);
		
	  ca.createAccBtn();
	  logger.info("Clicked on create account button");
	  Thread.sleep(2000);
	  
	  ca.mobileNmField(usernumber);
	  logger.info("provided number as "+ usernumber);
	  Thread.sleep(1000);
	  
	  ca.emailIdField("Guru@gmail.com");
	  logger.info("provided email as "+ "Guru@gmail.com");	
	  
	  ca.passwordField(password);
	  logger.info("provided password as "+ password);
	  
	  ca.acceptTC();
	  logger.info("Accepted Terms & Condition");
	  
	  ca.submitBtn();
	  logger.info("Clicked on Submit Button");
	  
	}
	
	
	
}
