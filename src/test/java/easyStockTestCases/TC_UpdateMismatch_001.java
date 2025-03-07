package easyStockTestCases;

import java.util.List;


import org.testng.annotations.Test;

import easyStockPageObjects.UserStockUpdate;

public class TC_UpdateMismatch_001 extends BaseClass  {
	
	
	public TC_UpdateMismatch_001() {
		
        // Call this in the constructor to skip login for this test class
        setSkipProfileClick(true);
    }
	
	@Test(priority = 1)
	public void stockUpadteMissmatchAll() throws InterruptedException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		
		us.clickVerify();
		logger.info("Clicked on Verify button");
		
		us.clickBranch();
		logger.info("Clicked on Branch Dropdown");
		
		us.selectbranch(branchnm);
		logger.info("Selected Branch "+ branchnm);
		
		List<String> WarehouseNames=us.mismatchWarehouseNames();
	//String[]	Warehouses=WarehouseNames.toArray(new String[0]);
	if(!WarehouseNames.isEmpty()) {
	    StringBuilder WarehouseNm = new StringBuilder();

	    for(int i = 0; i < WarehouseNames.size(); i++) {
	        WarehouseNm.append(WarehouseNames.get(i));
	        if (i < WarehouseNames.size() - 1) {
	            WarehouseNm.append(", ");
	        }
	    }

	    
	    logger.info("Warehouses with Mismatched Items are  "+WarehouseNm.toString());
	}
		
		if(!WarehouseNames.isEmpty())
		{
		for (String names : WarehouseNames) {
			
			logger.info("Updating Mismatched Items of  "+names);
			
			us.searchWarehouse(names);  
			logger.info("Searched warehouse as "+names);
			
			us.clickWarehouseResult();
			logger.info("Clicked on warehouse "+names);
			
			us.enterMismatchValues();
			
			
		   
			String[] log1=us.enterMismatchValues();
		  
		   int count=log1.length;
		  
		   for(int i = 0 ; i < count ; i++) {
			   
		   logger.info(log1[i]);
		   
		     }
		   
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
