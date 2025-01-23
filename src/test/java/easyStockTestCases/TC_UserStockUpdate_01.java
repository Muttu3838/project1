package easyStockTestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserStockUpdate;
import easyStockUtilities.AndroidActions;
import easyStockUtilities.InventoryFileParser;
import easyStockUtilities.InventoryFileParser.ItemQuantityDetails;


public class TC_UserStockUpdate_01 extends BaseClass {
	
	
	public TC_UserStockUpdate_01() {
        // Call this in the constructor to skip login for this test class
        setSkipLogin(true);
        setSkipProfileClick(true);
    }
	
	@Test(enabled = false)
	public void stockVerify() throws InterruptedException, FindFailed, IOException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		TC_LoginDDT_001 la=new TC_LoginDDT_001();
		LoginPage lp=new LoginPage(driver);
		AndroidActions Actions=new AndroidActions(driver);
		la.performLoginSteps(lp,Actions , usernumber, password);
		logger.info("Logined as User with "+usernumber+ " and Password "+password);
		//lp.FinalBack();
		
		us.clickVerify();
		
		us.clickBrach();
		
		us.selectbranch(branchnm);
		
		us.searchWarehouse("Centeral");
		
		us.clickWarehouseResult();
		
		us.searchItem("Agasi");
		
		us.enterStockOnefield("30");
		
		us.clickUpload();
		
		
		
		
		
	}
	
	@Test(enabled = true)
	public void stockVerifywithSearch() throws InterruptedException, IOException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		TC_LoginDDT_001 la=new TC_LoginDDT_001();
		LoginPage lp=new LoginPage(driver);
		AndroidActions Actions=new AndroidActions(driver);
		la.performLoginSteps(lp,Actions , usernumber, password);
		
		//lp.FinalBack();
		
		us.clickVerify();
		
		us.clickBrach();
		
		us.selectbranch(branchnm);
		
		
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
			
	     
		
		for (String names : WarehouseNames) {
			
			logger.info("Moving dorward with warehouse "+names);
			
			
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
			
			String rmnumber = generateRandomNumberAsString(-50, 50);
			
			if(us.isenterStockOnefieldDisplayed())
			{
			   us.enterStockOnefield(rmnumber);
			   logger.info("Entered Stock quantity is "+rmnumber);
			   
			   us.clickUpload();
				logger.info("Clicked Upload");
				
			   softAssert.assertTrue(true);
			   
			}else
			{
				logger.info("Item Not present in the Warehouse"+names);
				softAssert.assertTrue(false);
				
			}
						}
			
			us.ClickBulkUpload();
			logger.info("Clicked on Submit button");
			
			us.ClickBack();
			logger.info("Clicked on Back button");
			
            }
		
		
		
        }
	
		
		
	
	
	@Test(priority = 3)
	public void stockVerifywithScroll() throws InterruptedException, IOException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		TC_LoginDDT_001 la=new TC_LoginDDT_001();
		LoginPage lp=new LoginPage(driver);
		AndroidActions Actions=new AndroidActions(driver);
		la.performLoginSteps(lp,Actions , usernumber, password);
		logger.info("Logined as User");
		//lp.FinalBack();
		
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

	    	    
	    	    logger.info("Available Warehouse in the file Opening Stock are:  "+ItemsNm.toString());
	    	}
		
		for (String names : WarehouseNames) {
			
			logger.info("Moving dorward with warehouse "+names);
			
			
			if( warehouses.contains(names))
			{
				softAssert.assertTrue(true);
				logger.info(names+" warehouse present in the Closing Stock file");
			}else
			{
				softAssert.assertTrue(false);
				logger.info(names+" warehouse not present in the Closing Stock file");
			}
			us.searchWarehouse(names);  
			logger.info("Searched for "+names);
			
			us.clickWarehouseResult();
			logger.info("Selected ware as "+names);
			
			us.enterAllStockQuantity();
			logger.info("Satarting with Stock Entry for "+names);
			
			us.ClickBulkUpload();
			logger.info("Clicked on Submit button");
			
			us.ClickBack();
			logger.info("Clicked on Back button");
            }
        }
	
	/* @Test(priority = 3)
	public void stockVerifyAllWarehouses() throws InterruptedException, IOException {
	    UserStockUpdate us = new UserStockUpdate(driver);
	    TC_LoginDDT_001 la = new TC_LoginDDT_001();
	    LoginPage lp = new LoginPage(driver);
	    AndroidActions Actions = new AndroidActions(driver);
	    
	    // Login steps
	    la.performLoginSteps(lp, Actions, usernumber, password);
	    
	    us.clickVerify();
	    us.clickBrach();
	    us.selectbranch(branchnm);
	    
	    List<String> warehouseNames = us.warehouseNames();
	    
	    try {
	        // Parse inventory file
	        List<InventoryItem> inventory = InventoryFileParser.parseInventoryFile("Dm Opening Stk.txt");
	        List<String> allLocations = InventoryFileParser.getAllLocations(inventory);
	        
	        System.out.println("Available locations in file:");
	        for (String location : allLocations) {
	            System.out.println(location);
	        }
	        
	        for (String name : warehouseNames) {
	            
	        	System.out.println("Checking warehouse: " + name);
	            
	            // Normalize the warehouse name for comparison
	            String normalizedName = name.trim();
	            if (normalizedName.endsWith(" Godown")) {
	                normalizedName = normalizedName + ""; // Keep as is
	            } else if (!normalizedName.contains("Warehouse")) {
	                normalizedName = normalizedName + " Godown";
	            }
	            
	            if (allLocations.contains(normalizedName)) {
	                System.out.println("File Contains Warehouse as " + name);
	            } else {
	                System.out.println("File not Contains any Warehouse as " + name);
	            }
	            
	            us.searchWarehouse(name);
	            us.clickWarehouseResult();
	            us.enterAllStockQuantity();
	            us.ClickBulkUpload();
	            us.ClickBack();
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading inventory file: " + e.getMessage());
	        throw e;
	    }
	}
		*/
		
	
		
		
		
		
	}


