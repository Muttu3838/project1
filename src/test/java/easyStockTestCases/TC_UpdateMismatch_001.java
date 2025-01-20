package easyStockTestCases;

import java.util.List;


import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserStockUpdate;

public class TC_UpdateMismatch_001 extends BaseClass  {
	
	
	@Test(priority = 1)
	public void stockUpadteMissmatchAll() throws InterruptedException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		LoginPage lp=new LoginPage(driver);
		
		lp.FinalBack();
		logger.info("Clicked Final button Again to click verify");
		
		us.clickVerify();
		logger.info("Clicked on Verify button");
		
		us.clickBrach();
		logger.info("Clicked on Branch Dropdown");
		
		us.selectbranch(branchnm);
		logger.info("Selected Branch "+ branchnm);
		
		List<String> WarehouseNames=us.mismatchWarehouseNames();
		if(!WarehouseNames.isEmpty())
		{
		for (String names : WarehouseNames) {
			
			logger.info("Updating Mismatched Items of  "+names);
			
			us.searchWarehouse(names);  
			logger.info("Searched warehouse as "+names);
			
			us.clickWarehouseResult();
			logger.info("Clicked on warehouse "+names);
			
			us.enterMismatchValues();
		   String log=us.enterMismatchValues();
		   logger.info(log);
			
			us.ClickBulkUpload();
			logger.info("Clicked on Bulk Upload");
			
			us.ClickBack();
			logger.info("Clicked on Back Button");
			
            }
		}else if(WarehouseNames.isEmpty())
		{
			logger.info("There are no warehouses with mismatch");
		}
        }
		
		
		

}
