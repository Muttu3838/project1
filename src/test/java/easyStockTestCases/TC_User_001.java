package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserPage;

public class TC_User_001 extends BaseClass {
	UserPage usp ;
	LoginPage lp ;
	
	@Test(priority = 1)
	public void createUser() throws InterruptedException {
		usp = new UserPage(driver);
		lp = new LoginPage(driver);
		Thread.sleep(2000);

		usp.clicksidebarUser();
		logger.info("Clicked on sidebar user");
		
		
		if(usp.isUselistfounddisplayed()) 
		{
			Thread.sleep(5000);
		}
		usp.clickPlusBtn();
		logger.info("Clicked on plus button to add user");
		

		usp.userName(user);
		logger.info("Entered Username");
		

		usp.userMobileno(usernumber);
		logger.info("Entered Mobile Number");
		

		usp.userPassword("Test@123");
		logger.info("Entered Password");
		

		usp.selectRole();
		logger.info("Clicked on Select Role Button");
	

		usp.selectBranchManager();
		logger.info("Selected As Branch Manager");
		

		usp.selectBranch();
		logger.info("Clicked Select Branch Button");
		

		usp.searchBranchClick();
		logger.info("Clicked Search symbol");
		
		usp.searchBranch(branchnm);
		logger.info("Clicked on searchbar and Entered Branch as "+ branchnm);
		
		usp.selectBranchCheck();
		logger.info("Selected branch searched ");
		
		
		usp.clickOk();
		logger.info("Clicked on ok");

		usp.selectWarehouse();
		logger.info("Clicked on select Warehouse button");
		
		
		usp.searchWarehouseClick();
		logger.info("Clicked on search symbol");
		
		usp.searchWarehouse("Main Location");
		logger.info("Clicked and searching warehouse");
		
		usp.clickcheckbox1();
		logger.info("Selected Warehouse searched");
		// usp.clickwarehousecheckbox("Main Location");
		// usp.clickwarehousecheckbox2("Centeral Warehouse");
		// usp.clickMultiWarehouseCheckbox("Main Location,Centeral Warehouse");
		usp.clickOk();
		logger.info("Clicked on Ok button");

		usp.selectCategory();
		logger.info("Clicked on select Category button");
		
		usp.searchCategoryClick();
		logger.info("Clicked on search symbol");
		
		usp.searchCategory("Basmati");
		logger.info("Clicked and searched for Category");
		
		usp.clickcheckbox1();
		logger.info("Selected Category Searched searched");
		
		usp.clickOk();
		logger.info("Clicked on Ok button");
		

		usp.selectGroup();
		logger.info("Clicked on Select Group button");
		
		usp.searchGroupClick();
		logger.info("Clicked on search symbol");
		
		usp.searchGroup("Rice & Sugar");
		logger.info("Clicked and searching for Group");
		
		usp.clickcheckbox1();
		logger.info("Selected Group searched");
		
		usp.clickOk();
		logger.info("Clicked on Ok button");
		
		usp.clickSubmitButton();
		logger.info("click on submit button");
		
		if (usp.isOutofplanmsgdisplayed()) {
			logger.info("Out of plan, adding 1 more Branch from subscription");
			usp.ClickBack();
			usp.ClickBack();
			logger.info("Gone Back to Homepage");
			
			lp.setprofilebtn();
			logger.info("Adding 1 user");
			
			TC_subscriptionAddon_001 Subscribepage=new TC_subscriptionAddon_001();
			
			Subscribepage.Addon("User",1);
			logger.info("Added 1 User from subscription");
			
			lp.setprofilebtn();
			logger.info("Clicked on profile");
			
			logger.info("Trying to create User again");
			createUser();
			
			
			
			
		}else if(usp.issuccessmsgdisplayed())
		{
	    softAssert.assertTrue(true,"Passed");
	    logger.info("User Added succefully");
		
		
		usp.ClickDone();
		logger.info("click on Done button");
		
	
	}else if(usp.ismobilenoalreadyexistmsgdisplayed())
	{  
		softAssert.assertTrue(true,"Failed");
		logger.error("Mobile number alredy exist, Please use different Number");
		
	   
	}
	else
	{
		softAssert.assertTrue(false,"Failed");
		logger.error("User creation failed");
		
		
	}
		
	}

	@Test(priority = 2)
	public void editUser() throws InterruptedException {
		usp = new UserPage(driver);
		lp = new LoginPage(driver);
		usp.clicksidebarUser();
		logger.info("Clicked on sidebar user");
		
		usp.searchUser(user);
		logger.info("Searching Username");
		
		usp.userResultClick();
		logger.info("Clicked on searched Username to edit");

		usp.selectWarehouse();
		logger.info("Clicked on select Warehouse button");
		usp.searchWarehouseClick();
		logger.info("Clicked and searching warehouse");
		usp.searchWarehouse("Main Location");
		logger.info("Clicked and searching warehouse");
		usp.clickcheckbox1();
		logger.info("Unckecked Warehouse searched");
		usp.clickSearchCancel();
		logger.info("Clicked on cross cancel button");
		usp.clickAllCheckboxes();
		logger.info("Selected all checkbox visible");
		usp.clickOk();
		logger.info("Clicked on Ok Button");

		
		usp.selectCategory();
		logger.info("Clicked on select Category button");
		
		usp.searchCategoryClick();
		logger.info("Clicked and searching Category");
		usp.searchCategory("Basmati");
		logger.info("Clicked and searching Category");
		usp.clickcheckbox1();
		logger.info("Unckecked Category searched");
		usp.clickSearchCancel();
		logger.info("Clicked on cross cancel button");
		usp.clickAllCheckboxes();
		logger.info("Selected all Categories visible");
		usp.clickOk();
		logger.info("Clicked on Ok Button");

		usp.selectGroup();
		logger.info("Clicked on select Group button");
		usp.searchGroupClick();
		logger.info("Clicked and searching Group");
		usp.searchGroup("Rice & Sugar");
		logger.info("Clicked and searching Group");
		usp.clickcheckbox1();
		logger.info("Unckecked Group searched");
		usp.clickSearchCancel();
		logger.info("Clicked on cross cancel button");
		usp.clickAllCheckboxes();
		logger.info("Selected all Groups visible");
		usp.clickOk();
		logger.info("Clicked on Ok Button");
		usp.clickSubmitButton();
		logger.info("Clicked on Submit Button");
		if (usp.isupdatesuccessmsgdisplayed())
		{   
			softAssert.assertTrue(true, "Passed");
			new LoginPage(driver);
			logger.info("User Updated Successfully");
			
			
			
		}else if(usp.ismobilenoalreadyexistmsgdisplayed())
		{
			softAssert.assertTrue(false, "Failed");
			new LoginPage(driver);
			logger.error("Mobile number alredy exist, Please use different Number");
			
			
			
		}else 
		{
			softAssert.assertTrue(false);
			new LoginPage(driver);
			logger.error("User update failed");
			
			
		}
	}
	
	@Test(priority = 3)
	public void editUserwithselectAll() throws InterruptedException {
		UserPage usp = new UserPage(driver);
		lp = new LoginPage(driver);
		usp.clicksidebarUser();
		logger.info("Clicked on sidebar user");
		usp.searchUser(user);
		
		usp.userResultClick();
		logger.info("Searching Username");

		usp.selectAllWarehouse();
		logger.info("Clicked on All Warehouse checkbox");
		
	    usp.selectAllCategory();
	    logger.info("Clicked on All Category checkbox");
		
		usp.selectAllGroup();
		logger.info("Clicked on All Group checkbox");
		
		usp.clickSubmitButton();
		logger.info("Clicked on Submit button");
		
		if (usp.isupdatesuccessmsgdisplayed())
		{   
			softAssert.assertTrue(true, "Passed");
			logger.info("User Updated with select all Successfully");
			
			
			
		}else if(usp.ismobilenoalreadyexistmsgdisplayed())
		{
			softAssert.assertTrue(false, "Failed");
			new LoginPage(driver);
			logger.error("Mobile number alredy exist, Please use different Number");
			
			
			
		}else 
		{   
			softAssert.assertTrue(false, "Failed");
			new LoginPage(driver);
			logger.error("User update with select all failed");
			
			
		}
	
	}
}
