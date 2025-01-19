package easyStockTestCases;

import java.util.List;
import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserStockUpdate;

public class TC_UserStockUpdate_01 extends BaseClass {
	
	@Test(enabled = false)
	public void stockVerify() throws InterruptedException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		LoginPage lp=new LoginPage(driver);
		
		lp.FinalBack();
		
		us.clickVerify();
		
		us.clickBrach();
		
		us.selectbranch(branchnm);
		
		us.searchWarehouse("Centeral");
		
		us.clickWarehouseResult();
		
		us.searchItem("Agasi");
		
		us.enterStockOnefield("30");
		
		us.clickUpload();
		
		
		
		
		
	}
	
	@Test(enabled = false)
	public void stockVerifywithWarehouseName() throws InterruptedException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		LoginPage lp=new LoginPage(driver);
		
		lp.FinalBack();
		
		us.clickVerify();
		
		us.clickBrach();
		
		us.selectbranch(branchnm);
		
		us.searchWarehouse("Rice Godown");  //Pulses Godown  , Centeral Warehouse  , Masala Godown  , Rice Godown
		
		us.clickWarehouseResult();
		
		
		
		us.enterAllStockQuantity("10");
		
		us.ClickBulkUpload();
		
		
		
		
		
		
	}
	
	@Test(priority = 3)
	public void stockVerifyAllWarehouses() throws InterruptedException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		LoginPage lp=new LoginPage(driver);
		
		lp.FinalBack();
		
		us.clickVerify();
		
		us.clickBrach();
		
		us.selectbranch(branchnm);
		
		List<String> WarehouseNames=us.warehouseNames();
		
		
		for (String names : WarehouseNames) {
			
			System.out.println(names);
			us.searchWarehouse(names);  
			
			us.clickWarehouseResult();
			
			
			
			us.enterAllStockQuantity("10");
			
			us.ClickBulkUpload();
			
			us.ClickBack();
            }
        }
		
		
	@Test(priority = 4)
	public void stockValidationAllWarehouses() throws InterruptedException
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
		
		
		for (String names : WarehouseNames) {
			
			logger.info("Updating Mismatched Items of warehouse "+names);
			
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
        }
		
		
		
		
		
		
	}


