package easyStockTestCases;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import easyStockPageObjects.FilesVerification;
import easyStockPageObjects.LoginPage;
import easyStockUtilities.InventoryFileParser;

public class TC_FileVerification_001 extends BaseClass {

	public TC_FileVerification_001() {
        // Call this in the constructor to skip login for this test class
        //setSkipLogin(true);
        setSkipProfileClick(true);
    }
	
	@Test(priority = 1,enabled = false)
	public void closingStockVerfication() throws InterruptedException, IOException
	{
		FilesVerification us=new FilesVerification(driver);
		
		LoginPage lp=new LoginPage(driver);
		
		
		logger.info("Logined as User");
		
		lp.FinalBack();
		
		us.clickVerify();
		logger.info("Clicked on Verify");
		
		us.clickBrach();
		logger.info("Clicked on Branch");
		
		us.selectbranch(branchnm);
		logger.info("Clicked on Verify");
		
		
		
		List<String> WarehouseNames=us.warehouseNames();
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
		
		
		
		List<String> warehouses = parser.getAllWarehouses();
	
	        if(!warehouses.isEmpty()) {
	    	    StringBuilder WarehouseNm = new StringBuilder();

	    	    for(int i = 0; i < warehouses.size(); i++) {
	    	        WarehouseNm.append(warehouses.get(i));
	    	        if (i < warehouses.size() - 1) {
	    	            WarehouseNm.append(", ");
	    	        }
	    	    }

	    	    
	    	    logger.info("Available Warehouse in the file Closing Stock are:  "+WarehouseNm.toString());
	    	}
			
	        List<String> allItems = parser.getAllItemNames();
			 
	        if(!warehouses.isEmpty()) {
	    	    StringBuilder ItemsNm = new StringBuilder();

	    	    for(int i = 0; i < allItems.size(); i++) {
	    	    	ItemsNm.append(allItems.get(i));
	    	        if (i < allItems.size() - 1) {
	    	        	ItemsNm.append(", ");
	    	        }
	    	    }

	    	    
	    	    logger.info("Available Warehouse in the file Closing Stock are:  "+ItemsNm.toString());
	    	}
		
		for (String warehousename : WarehouseNames) {
			
			logger.info("Moving forward with warehouse "+warehousename);
			
			
			if( warehouses.contains(warehousename))
			{
				softAssert.assertTrue(true);
				logger.info(warehousename+" warehouse present in the Closing Stock file");
			}else
			{
				softAssert.assertTrue(false);
				logger.error(warehousename+" warehouse not present in the Closing Stock file");
			}
			us.searchWarehouse(warehousename);  
			logger.info("Searched for "+warehousename);
			
			us.clickWarehouseResult();
			logger.info("Selected warehouse as "+warehousename);
			
			us.enterAllStockQuantity(warehousename);
			List<String> logs=us.enterAllStockQuantity(warehousename);
			
			  
			if(!logs.isEmpty()) {
	    	    StringBuilder errors = new StringBuilder();

	    	    for(int i = 0; i < logs.size(); i++) {
	    	    	errors.append(logs.get(i));
	    	        if (i < logs.size() - 1) {
	    	        	errors.append(", ");
	    	        }
	    	    }
	    	    logger.info("Available logs for not matched Items:  "+errors.toString());
			}
			    /*    // After processing all items, perform soft assertions
			        for (String log : logs.toString().split("/n")) {
			            if (log.contains("not matching")) {
			                softAssert.fail("Quantity mismatch: " + log);
			                
			            }
			        } */
			     
			// logger.info("Available Warehouse in the file Closing Stock are:  "+log.toString());
			
			logger.info("Completed Stock Entry for "+warehousename);
			
			//us.ClickBulkUpload();
			//logger.info("Clicked on Submit button");
			
			us.ClickBack();
			logger.info("Clicked on Back button");
            }
        }
	
	
	@Test(enabled = true,priority = 2)
	public void closingStockVerficationwithSearch() throws InterruptedException, IOException
	{
		FilesVerification us=new FilesVerification(driver);
		
		//LoginPage lp=new LoginPage(driver);
		
		
		//lp.FinalBack();
		logger.info("Login Succesfull and clicked profile btn to expose Verify module");
		
		us.clickVerify();
		logger.info("Clicked on Verify Module");
		
		us.clickBrach();
		logger.info("Clicked on Branch");
		
		us.selectbranch(branchnm);
		logger.info("Selected branch as"+branchnm);
		
		
        List<String> WarehouseNames=us.warehouseNames();
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
		
		
		
		List<String> warehouses = parser.getAllWarehouses();
	
	        if(!warehouses.isEmpty()) {
	    	    StringBuilder WarehouseNm = new StringBuilder();

	    	    for(int i = 0; i < warehouses.size(); i++) {
	    	        WarehouseNm.append(warehouses.get(i));
	    	        if (i < warehouses.size() - 1) {
	    	            WarehouseNm.append(", ");
	    	        }
	    	    }

	    	    
	    	    logger.info("Available Warehouse in the file Opening Stock are:  "+WarehouseNm.toString());
	    	}
			
	     
		
		for (String names : WarehouseNames) {
			
			logger.info("Moving forward with warehouse "+names);
			
			
			if( warehouses.contains(names))
			{
				softAssert.assertTrue(true);
				logger.info(names+" warehouse present in the file");
			}else
			{
				softAssert.assertTrue(false);
				logger.info(names+" warehouse not present in the file");
			}
			us.searchWarehouse(names);  
			logger.info("Searched for "+names);
			
			us.clickWarehouseResult();
			logger.info("Selected ware as "+names);
			
			List<String> allItems = parser.getItemsInWarehouse(names);
			
	    for (String ItemNames : allItems)
			
			{
				
	    	us.searchItem(ItemNames);
			logger.info("Searched for Item "+ItemNames);
			
			if(us.isenterStockOnefieldDisplayed()) {
		
				Double uiquantity=us.uiQuantity();
		
		        Double filequantity=us.fileQuantity(names);
		
			if(uiquantity.equals(filequantity)) 
			{
				logger.info("Quantity from the UI"+"(*"+uiquantity+"*)"+" for the Item " + ItemNames + " is matching with Closing Stock Quantity " + "(*"+filequantity+"*)"+" in "+names);
				softAssert.assertTrue(true);
			}else 
			{
				
				logger.error("Quantity from the UI"+"(*"+uiquantity+"*)"+" for the Item " + ItemNames + " is Not matching with Closing Stock Quantity " + "(*"+filequantity+"*)"+" in "+names);
				softAssert.assertTrue(false);
				
			}
			
			 }else {
				
				 softAssert.assertTrue(false);
				 logger.error(ItemNames+" is not present in "+names);
				 
			 }
			
			}
			
			
	        us.ClickBulkUpload();
			logger.info("Clicked on Submit button");
			
			us.ClickBack();
			logger.info("Clicked on Back button");
			
            }
		
        }
	
		
	
}
	
