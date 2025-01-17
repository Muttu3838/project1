package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.BranchPage;
import easyStockPageObjects.LoginPage;

public class TC_CreateBrach_001 extends BaseClass {

	
	
	@Test(priority = 1)
	public void CreateBranchTest() throws InterruptedException {
		
		BranchPage bp = new BranchPage(driver);
		
		bp.setSidebarbranch();
		logger.info("clicked on Branch");
		

		bp.setCreatebranch();
		logger.info("clicked on CreateBranch button");
		

		bp.setbranchnameclick();
		logger.info("clicked on create branch successfully");
		
		
		bp.setbranchname(branchnm);
	
		logger.info("Entered Branch name successfully");

		bp.landmarkclick();
	
		
		bp.landmarkclickname("Kehwapur");
		
		logger.info("Entered Landmark successfully");

		bp.locclick();
		
		logger.info("Cliked on Map location successfully");

		bp.locnametxt();
		
		bp.loctxt("Keshwapur");
		
		logger.info("Entered Location name successfully");

		Thread.sleep(5000);
		bp.Searchbtn();
		logger.info("Clicked on search button succefully");

		//Thread.sleep(5000);
		bp.Searchresult();
		logger.info("Clicked on serach result button succefully");

		
		bp.passbtn();
		
		logger.info("Clicked on pass mark or button succefully");

		Thread.sleep(3000);
		bp.Submitbranch();
		logger.info("Clicked on submit brach button succefully");


		if (bp.isOutofplanmsgdisplayed()) {
			try
			{
		    logger.info("Out of plan, adding 1 more Branch from subscription");
			bp.ClickBack();
			bp.ClickBack();
			
			LoginPage lp=new LoginPage(driver);
			lp.setprofilebtn();
			TC_subscriptionAddon_001 Subscribepage=new TC_subscriptionAddon_001();
			
			Subscribepage.Addon("branch",1);
			
			
			CreateBranchTest();
			}catch(Exception e)
			{
				logger.info(e.getMessage());
				softAssert.assertTrue(false, e.getMessage());
			}
			
		}else if(bp.issuccessmsgdisplayed())
		{
			
			softAssert.assertTrue(true,"Passed");
			logger.info("Branch Added succefully");
		
			
		}
		else {
			softAssert.assertTrue(false, "failed");
			logger.info("Branch Adding failed");
		}
	}

}
