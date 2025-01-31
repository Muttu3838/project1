package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.CreateAccountPage;

public class TC_CreateAccount_001 extends BaseClass {

	@Test
	public void createAccount() 
	
	{
		
	  CreateAccountPage ca=new	CreateAccountPage(driver);
		
	  ca.createAccBtn();
	  logger.info("Clicked on create account button");
		
	  ca.mobileNmField(usernumber);
	  logger.info("provided number as "+ usernumber);
	  
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
