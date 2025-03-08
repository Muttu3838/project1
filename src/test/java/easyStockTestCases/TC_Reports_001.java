package easyStockTestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.ReportsPage;
import easyStockPageObjects.UserStockUpdate;
import easyStockUtilities.InventoryDataReader;
import easyStockUtilities.InventoryDetailsUtil;
import easyStockUtilities.InventoryFileManager;
import easyStockUtilities.InventoryFileParser;
import easyStockUtilities.ItemInventoryDetails;
import easyStockUtilities.WarehouseDetails;

public class TC_Reports_001 extends BaseClass {
	
	
    private InventoryDetailsUtil inventoryUtil;
    private static final Logger logger = LogManager.getLogger(TC_Reports_001.class);
    List<ItemInventoryDetails> allItemDetails;
    
	public TC_Reports_001() {
        // Call this in the constructor to skip login for this test class
		 inventoryUtil = new InventoryDetailsUtil();
	
        setSkipProfileClick(true);
    }
	
	
	
	
	@Test(priority = 0)
    public void testInventoryDetails() throws IOException, InterruptedException {
		
		UserStockUpdate us=new UserStockUpdate(driver);
		
		us.clickVerify();
		logger.info("Clicked on Verify button");
		
		us.ClickFilter();
		logger.info("Clicked on Filter");
		
		Thread.sleep(3000);
		
		//us.ClickDate();
		//us.tapAtPosition(172, 1096);
		us.ClickAtPosition(172, 1096);
		logger.info("Clicked on Date");
		
		us.ClickEdit();
		logger.info("Clicked on Edit button");
		
		us.chooseDate(filterDate);
		logger.info("Entered Date");
		
		us.clickOk();
		logger.info("Clicked on Ok Button");
		
		us.closeFilterBtn();
		logger.info("Clicked on close button in filter drawer");
		
		us.clickBranch();
		logger.info("Clicked on Branch Dropdown");
		
		us.selectbranch(branchnm);
		logger.info("Selected Branch "+ branchnm);
		
		us.clickItemBtn();
		logger.info("Clicked on Items Options ");
		
       
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
       // InventoryDetailsUtil inventory=new InventoryDetailsUtil();
        
        
        List<String> allItems = parser.getAllItemNames();
        
         allItemDetails = new ArrayList<>();

        for (String itemName : allItems) {
            
        	Thread.sleep(2000);
                // Search and click operations using your existing UtilityService
                us.searchItem2(itemName);
                logger.info("Searched for Item " + itemName);

                us.ItemClick();
                logger.info("Clicked on Item " + itemName);
                Thread.sleep(2000);
                // Get warehouse elements using your existing method
              List<WebElement>  warehouseElements1 = us.findWarehouseElements();

                // Process the elements using our utility
                ItemInventoryDetails itemDetails = inventoryUtil.processWarehouseElements(itemName, warehouseElements1);
                    allItemDetails.add(itemDetails);

                    us.ClickBack();
                    logger.info("Clicked on Back Button");
            }
        
        for(ItemInventoryDetails detail : allItemDetails) {
        	
        System.out.println("ItemName --->"+detail.getItemName());
        System.out.println("Total Entered Quantity --->"+detail.getTotalEnteredQuantity());
        System.out.println("Total Tally Stock Quantity --->"+detail.getTotalTallyStock());
        System.out.println("Total Excess Quantity --->"+detail.getTotalExcessQuantity());
        System.out.println("Total Short Quantity --->"+detail.getTotalShortQuantity());
        for (WarehouseDetails warehouse : detail.getWarehouseDetails()) {
        	 System.out.println("\nWarehouse: "+ warehouse.getWarehouseName());
        	 System.out.println("  Short Quantity: "+ warehouse.getShortQuantity());
        	 System.out.println("  Excess Quantity: "+ warehouse.getExcessQuantity());
        	 System.out.println("  Entered Quantity: "+ warehouse.getEnteredQuantity());
        	 System.out.println("  Tally Stock: "+ warehouse.getTallyStock());
        }
        
       }
        
        InventoryFileManager fileManager = new InventoryFileManager();
        fileManager.saveInventoryDetails(filterDate, allItemDetails);
        
        // Optionally generate a report
        //String reportPath = "reports/inventory_report_" + filterDate.replace("/", "_") + ".txt";
        fileManager.generateInventoryReport(filterDate);
        //MissmatchItemsReport(allItemDetails);
        // Verify the results
        
    }
	
	@Test(dependsOnMethods = "testInventoryDetails",enabled = false)
    public void MissmatchItemsReport() throws IOException, InterruptedException {
		
		UserStockUpdate us=new UserStockUpdate(driver);
		ReportsPage rs=new ReportsPage(driver); 
		LoginPage lp=new LoginPage(driver);
		
		lp.setprofilebtn();
		
		rs.reportsBtn();
		
		Thread.sleep(2000);
		rs.missMatchReportBtn();
		
		rs.filterBtn();
		Thread.sleep(3000);
		
		rs.ClickAtPosition(129, 786);
		
		rs.clickEdit();
		
		rs.chooseDate(filterDate); //"02/03/2025"
		
		rs.clickOk();
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
	       // InventoryDetailsUtil inventory=new InventoryDetailsUtil();
	        

    	if(rs.isSearchPresent()) 
    	{
	        List<String> allItems = parser.getAllItemNames();

	        for (String itemName : allItems) {
	            
	        	
	        	
	        	Thread.sleep(2000);
	                // Search and click operations using your existing UtilityService
	                rs.searchItem(itemName);
	                logger.info("Searched for Item " + itemName);
	                
	                us.scrollToEndActionwithdirection("right");
	                logger.info("scolled to right");
	        		
	               
	                //List<ItemInventoryDetails> allItemDetails=testInventoryDetails();
	               
	               for(ItemInventoryDetails detail : allItemDetails) {
	                	
	                	
	                if(detail.getItemName().equalsIgnoreCase(itemName))
	                	{
	                	
	                	double TotalTallyStock=rs.TallyStock();
	 	        		double ToatalEnteredQuantity=rs.EnteredQuantity();
	 	        		double TotalDiffQuantity=rs.DiffQuantity();
	 	        		
	                   if((TotalTallyStock!=0 || ToatalEnteredQuantity!=0) && TotalDiffQuantity!=0 ) 
	                	 {
	                			
	                		if(TotalTallyStock==detail.getTotalTallyStock()) {
	                		softAssert.assertEquals(TotalTallyStock, detail.getTotalTallyStock());
	                		logger.info("Tally stock from UI is: " + TotalTallyStock + " & Tally stock from Stock verification is: "+detail.getTotalTallyStock()+" for Item "+detail.getItemName());
	                		}
	                		else 
	                		{
	                			softAssert.assertTrue(false, "For "+itemName+" both Tally stock from UI : " + TotalTallyStock + " & Tally stock from Stock verification : "+detail.getTotalTallyStock()+" Not Matching");
	                			logger.error("For "+itemName+" both Tally stock from UI : " + TotalTallyStock + " & Tally stock from Stock verification : "+detail.getTotalTallyStock()+" Not Matching");
	                		}
	                		
	                		if(ToatalEnteredQuantity==detail.getTotalEnteredQuantity()) {
	                		softAssert.assertEquals(ToatalEnteredQuantity, detail.getTotalEnteredQuantity());
	                		logger.info("Toatal Entered Quantity from UI is: " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification is: "+detail.getTotalEnteredQuantity()+" for Item "+detail.getItemName());
	                		}
	                		else
	                		{
	                			softAssert.assertTrue(false, "For "+itemName+" both Total Entered Quantity from UI : " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification : "+detail.getTotalEnteredQuantity()+" Not Matching");
	                			logger.error("For "+itemName+" both Total Entered Quantity from UI : " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification : "+detail.getTotalEnteredQuantity()+" Not Matching");
	                		}
	                		
	                		if(TotalDiffQuantity==(detail.getTotalEnteredQuantity()-detail.getTotalTallyStock())) {
	                		softAssert.assertEquals(TotalDiffQuantity, detail.getTotalEnteredQuantity()-detail.getTotalTallyStock());
	                		logger.info("Total Diff Quantity from UI is: " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification is: "+(detail.getTotalEnteredQuantity()-detail.getTotalTallyStock())+" for Item "+detail.getItemName());
	                		}else
	                		{
	                			softAssert.assertTrue(false, "For "+itemName+" both Total Diff Quantity from UI : " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification : "+(detail.getTotalEnteredQuantity()-detail.getTotalTallyStock())+" Not Matching");
	                			logger.error("For "+itemName+" both Total Diff Quantity from UI : " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification : "+(detail.getTotalEnteredQuantity()-detail.getTotalTallyStock())+" Not Matching");
	                		}
	                	}
	                   else if(TotalTallyStock==ToatalEnteredQuantity && TotalDiffQuantity!=0 ) 
	            		{
	            			softAssert.assertTrue(false,"Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+TotalTallyStock+" & ToatalEnteredQuantity: "+ToatalEnteredQuantity+" Quantity are same");
	            			logger.error("Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+TotalTallyStock+" & ToatalEnteredQuantity: "+ToatalEnteredQuantity+" Quantity are same");
	            			
	            		}
	                   else if(ToatalEnteredQuantity==0 && TotalTallyStock==0 && TotalDiffQuantity==0) 
	            		{
	            			softAssert.assertTrue(true,"Item "+itemName+" not present in mismatch report as it's not have any mismatch");
	            			logger.info("Item "+itemName+" not present in mismatch report as it's not have any mismatch");
	            		}
	                   else if((ToatalEnteredQuantity!=0 || TotalTallyStock!=0) && TotalDiffQuantity==0) 
	            		{
	            			softAssert.assertTrue(true,"Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+" with DiffQuantity: "+TotalDiffQuantity);
	            			logger.info("Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+" with DiffQuantity: "+TotalDiffQuantity);
	            		}
	                		
	                }
	                


	                  
	            }
		
	        }
	}
	
 }

	@Test(priority = 1)
	public void MissmatchItemsReport2() throws IOException, InterruptedException {
	    UserStockUpdate us = new UserStockUpdate(driver);
	    ReportsPage rs = new ReportsPage(driver);
	    LoginPage lp = new LoginPage(driver);
	    
	    // Initialize the data reader and load stored data
	 // Initialize the data reader and load stored data
	    InventoryDataReader dataReader = new InventoryDataReader();
	    List<ItemInventoryDetails> storedDetails = dataReader.readInventoryData(filterDate);
	   
	    
		
		lp.setprofilebtn();
		logger.info("Clicked on profile");
		
		rs.reportsBtn();
		logger.info("Clicked on reports");
		
		Thread.sleep(2000);
		rs.missMatchReportBtn();
		logger.info("Clicked on missmatch report");
		
		rs.filterBtn();
		logger.info("Clicked on filter button");
		Thread.sleep(3000);
		
        rs.clickBranch();
        logger.info("Clicked on branch");
		
		rs.selectBranch(branchnm);
		logger.info("slected branch as"+branchnm);
		
//		rs.ClickAtPosition(129, 786);
//		logger.info("Clicked on date button");
//		
//		rs.clickEdit();
//		logger.info("Clicked on Edit date");
//		
//		rs.chooseDate(filterDate); //"02/03/2025"
//		logger.info("Entered date as"+filterDate);
//		
//		rs.clickOk();
//		logger.info("Clicked on ok button");
		
		
		
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
	       // InventoryDetailsUtil inventory=new InventoryDetailsUtil();
	        

	    if(rs.isSearchPresent()) {
	        List<String> allItems = parser.getAllItemNames();

	        for (String itemName : allItems) {
	            Thread.sleep(2000);
	            rs.searchItem(itemName);
	            logger.info("Searched for Item " + itemName);
	            
	            us.scrollToEndActionwithdirection("right");
	            logger.info("scrolled to right");
	            
	            // Get stored details for current item
	            ItemInventoryDetails storedItemDetails = dataReader.getItemDetails(itemName);
	            
	            if(storedItemDetails != null && storedItemDetails.getItemName().equalsIgnoreCase(itemName)) {
	                double TotalTallyStock = rs.TallyStock();
	                double ToatalEnteredQuantity = rs.EnteredQuantity();
	                double TotalDiffQuantity = rs.DiffQuantity();
	                
	                if((TotalTallyStock!=0 || ToatalEnteredQuantity!=0) && TotalDiffQuantity!=0 ) 
               	 {
               			
               		if(TotalTallyStock==storedItemDetails.getTotalTallyStock()) {
               		softAssert.assertEquals(TotalTallyStock, storedItemDetails.getTotalTallyStock());
               		logger.info("Tally stock from UI is: " + TotalTallyStock + " & Tally stock from Stock verification is: "+storedItemDetails.getTotalTallyStock()+" for Item "+storedItemDetails.getItemName());
               		}
               		else 
               		{
               			softAssert.assertTrue(false, "For "+itemName+" both Tally stock from UI : " + TotalTallyStock + " & Tally stock from Stock verification : "+storedItemDetails.getTotalTallyStock()+" Not Matching");
               			logger.error("For "+itemName+" both Tally stock from UI : " + TotalTallyStock + " & Tally stock from Stock verification : "+storedItemDetails.getTotalTallyStock()+" Not Matching");
               		}
               		
               		if(ToatalEnteredQuantity==storedItemDetails.getTotalEnteredQuantity()) {
               		softAssert.assertEquals(ToatalEnteredQuantity, storedItemDetails.getTotalEnteredQuantity());
               		logger.info("Toatal Entered Quantity from UI is: " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification is: "+storedItemDetails.getTotalEnteredQuantity()+" for Item "+storedItemDetails.getItemName());
               		}
               		else
               		{
               			softAssert.assertTrue(false, "For "+itemName+" both Total Entered Quantity from UI : " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification : "+storedItemDetails.getTotalEnteredQuantity()+" Not Matching");
               			logger.error("For "+itemName+" both Total Entered Quantity from UI : " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification : "+storedItemDetails.getTotalEnteredQuantity()+" Not Matching");
               		}
               		
               		if(TotalDiffQuantity==(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())) {
               		softAssert.assertEquals(TotalDiffQuantity, storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock());
               		logger.info("Total Diff Quantity from UI is: " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification is: "+(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())+" for Item "+storedItemDetails.getItemName());
               		}else
               		{
               			softAssert.assertTrue(false, "For "+itemName+" both Total Diff Quantity from UI : " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification : "+(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())+" Not Matching");
               			logger.error("For "+itemName+" both Total Diff Quantity from UI : " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification : "+(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())+" Not Matching");
               		}
               	}else if(TotalTallyStock==ToatalEnteredQuantity && TotalDiffQuantity!=0 ) 
        		{
        			softAssert.assertTrue(false,"Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+TotalTallyStock+" & ToatalEnteredQuantity: "+ToatalEnteredQuantity+" Quantity are same");
        			logger.error("Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+TotalTallyStock+" & ToatalEnteredQuantity: "+ToatalEnteredQuantity+" Quantity are same");
        			
        		}
               else if(ToatalEnteredQuantity==0 && TotalTallyStock==0 && TotalDiffQuantity==0) 
        		{
        			softAssert.assertTrue(true,"Item "+itemName+" not present in mismatch report as it's not have any mismatch");
        			logger.info("Item "+itemName+" not present in mismatch report as it's not have any mismatch");
        		}
               else if((ToatalEnteredQuantity!=0 || TotalTallyStock!=0) && TotalDiffQuantity==0) 
        		{
        			softAssert.assertTrue(true,"Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+" with DiffQuantity: "+TotalDiffQuantity);
        			logger.info("Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+" with DiffQuantity: "+TotalDiffQuantity);
        		}
	                
	                // Optional: Print detailed summary for the item
	                //dataReader.printItemSummary(itemName);
	            }else {
        			
        			logger.error("But there is error with validation");
        		}
	        }
	    }else if(rs.isSearchPresent()==false) 
	    {
	    	softAssert.assertTrue(true,"There is no Missmatch items in the Missmatch report");
			logger.info("There is no Missmatch items in the Missmatch report");
	    }else 
	    {
	    	softAssert.assertTrue(false,"Error with loading/Unknow error while fetching report data");
			logger.error("Error with loading/Unknow error while fetching report data");
	    }
	}	
	
	@Test(enabled = true)
	public void WarehousewiseReport() throws InterruptedException, IOException 
	{
		
		UserStockUpdate us = new UserStockUpdate(driver);
	    ReportsPage rs = new ReportsPage(driver);
	    LoginPage lp = new LoginPage(driver);
	    
	    // Initialize the data reader and load stored data
	 // Initialize the data reader and load stored data
	    InventoryDataReader dataReader = new InventoryDataReader();
	    List<ItemInventoryDetails> storedDetails = dataReader.readInventoryData(filterDate);
	   
	    
		
		lp.setprofilebtn();
		logger.info("Clicked on profile");
		
		rs.reportsBtn();
		logger.info("Clicked on reports");
		
		Thread.sleep(2000);
		rs.warehouseWiseReportBtn();
		logger.info("Clicked on missmatch report");
		
		rs.filterBtn();
		logger.info("Clicked on filter button");
		Thread.sleep(3000);
		
        rs.clickBranch();
        logger.info("Clicked on branch");
		
		rs.selectBranch(branchnm);
		logger.info("slected branch as"+branchnm);
		
		rs.ClickAtPosition(129, 786);
		logger.info("Clicked on date button");
		
		rs.clickEdit();
		logger.info("Clicked on Edit date");
		
		rs.chooseDate(filterDate); //"02/03/2025" filterDate
		logger.info("Entered date as"+filterDate);
		
		rs.clickOk();
		logger.info("Clicked on ok button");
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
	       // InventoryDetailsUtil inventory=new InventoryDetailsUtil();
	        

	    if(rs.isSearchPresent()) {
	        List<String> allItems = parser.getAllItemNames();

	        for (String itemName : allItems) {
	            Thread.sleep(2000);
	            rs.searchItem(itemName);
	            logger.info("----------------------- Searched for Item " + itemName+"-----------------------");
	            
	            us.scrollUntilElementVisible("right",rs.DiffStockElement());
	            logger.info("scrolled to right");
	            
	            // Get stored details for current item
	            ItemInventoryDetails storedItemDetails = dataReader.getItemDetails(itemName);
	            List <WarehouseDetails> warehousedetail = storedItemDetails.getWarehouseDetails();
	            if(storedItemDetails != null && storedItemDetails.getItemName().equalsIgnoreCase(itemName)) {
	              
	            	
	            	//Settings All Total values as 0 primarily
	            	double TotalTallyStock = 0;
	                double ToatalEnteredQuantity = 0;
	                double TotalDiffQuantity = 0;
	                
	                for(WarehouseDetails warehouse : warehousedetail) 
	                {
	                	String WarehouseNm=warehouse.getWarehouseName();
	                	
	                	//Getting All values from UI 
	                	Double TallyStock=rs.TallyStockbyWarehouse(WarehouseNm);
	                	Double EnteredQuantity=rs.EnteredQuantitybyWarehouse(WarehouseNm);
	                	Double DiffQuantity=rs.DiffQuantitybyWarehouse(WarehouseNm);
	                	
	                	TotalTallyStock=TotalTallyStock+TallyStock;
	                	ToatalEnteredQuantity=ToatalEnteredQuantity+EnteredQuantity;
	                	TotalDiffQuantity=TotalDiffQuantity+DiffQuantity;
	                	
	                	logger.info("******For Warehouse: "+WarehouseNm+" *******");
	                	//Warehousewise Quantity Verification
	                	
	                	if(TallyStock==warehouse.getTallyStock())
	                	{
	                		softAssert.assertEquals(TallyStock, warehouse.getTallyStock());
	                		logger.info("Tally stock from UI is: " + TallyStock + " & Tally stock from Stock verification is: "+warehouse.getTallyStock()+" for Warehouse "+WarehouseNm+" & for Item "+storedItemDetails.getItemName());
	                		
	                	}
	                	
	                	if(EnteredQuantity==warehouse.getEnteredQuantity())
	                	{
	                		softAssert.assertEquals(EnteredQuantity, warehouse.getEnteredQuantity());
	                		logger.info("Entered Quantity from UI is: " + EnteredQuantity + " & Entered Quantity from Stock verification is: "+warehouse.getEnteredQuantity()+" for Warehouse "+WarehouseNm+" & for Item "+storedItemDetails.getItemName());
	                		
	                	}
	                	
	                	if(DiffQuantity==(warehouse.getEnteredQuantity()-warehouse.getTallyStock()) && DiffQuantity!=0)
	                	{
	                		softAssert.assertEquals(DiffQuantity,(warehouse.getEnteredQuantity()-warehouse.getTallyStock()));
	                		logger.info("Diff Quantity from UI is: " + DiffQuantity + " & Diff Quantity from Stock verification is: "+(warehouse.getEnteredQuantity()-warehouse.getTallyStock())+" for Warehouse "+WarehouseNm+" & for Item "+storedItemDetails.getItemName());
	                		
	                	}
	                	
	                }
	                
	                logger.info("Total Tally Stock Is: "+TotalTallyStock+" & Total Entered Quantity Is: "+ToatalEnteredQuantity+" & Total Diff Quantity Is: "+TotalDiffQuantity+"For Item: "+storedItemDetails.getItemName());
	                logger.info("Complted for Item "+itemName+("/n"));
	           //Total Quantity Verification   
	                
	       /*         if((TotalTallyStock!=0 || ToatalEnteredQuantity!=0) && TotalDiffQuantity!=0 ) 
            	 {
            			
            		if(TotalTallyStock==storedItemDetails.getTotalTallyStock()) {
            		softAssert.assertEquals(TotalTallyStock, storedItemDetails.getTotalTallyStock());
            		logger.info("Total Tally stock from UI is: " + TotalTallyStock + " & Total Tally stock from Stock verification is: "+storedItemDetails.getTotalTallyStock()+" for Item "+storedItemDetails.getItemName());
            		}
            		else 
            		{
            			softAssert.assertTrue(false, "For "+itemName+" both Tally stock from UI : " + TotalTallyStock + " & Tally stock from Stock verification : "+storedItemDetails.getTotalTallyStock()+" Not Matching");
            			logger.error("For "+itemName+" both Tally stock from UI : " + TotalTallyStock + " & Tally stock from Stock verification : "+storedItemDetails.getTotalTallyStock()+" Not Matching");
            		}
            		
            		if(ToatalEnteredQuantity==storedItemDetails.getTotalEnteredQuantity()) {
            		softAssert.assertEquals(ToatalEnteredQuantity, storedItemDetails.getTotalEnteredQuantity());
            		logger.info("Toatal Entered Quantity from UI is: " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification is: "+storedItemDetails.getTotalEnteredQuantity()+" for Item "+storedItemDetails.getItemName());
            		}
            		else
            		{
            			softAssert.assertTrue(false, "For "+itemName+" both Total Entered Quantity from UI : " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification : "+storedItemDetails.getTotalEnteredQuantity()+" Not Matching");
            			logger.error("For "+itemName+" both Total Entered Quantity from UI : " + ToatalEnteredQuantity + " & Total Entered Quantity from Stock verification : "+storedItemDetails.getTotalEnteredQuantity()+" Not Matching");
            		}
            		
            		if(TotalDiffQuantity==(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())) {
            		softAssert.assertEquals(TotalDiffQuantity, storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock());
            		logger.info("Total Diff Quantity from UI is: " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification is: "+(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())+" for Item "+storedItemDetails.getItemName());
            		}else
            		{
            			softAssert.assertTrue(false, "For "+itemName+" both Total Diff Quantity from UI : " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification : "+(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())+" Not Matching");
            			logger.error("For "+itemName+" both Total Diff Quantity from UI : " + TotalDiffQuantity + " & Total Diff Quantity from Stock verification : "+(storedItemDetails.getTotalEnteredQuantity()-storedItemDetails.getTotalTallyStock())+" Not Matching");
            		}
            	}else if(TotalTallyStock==ToatalEnteredQuantity && TotalDiffQuantity!=0 ) 
     		{
     			softAssert.assertTrue(false,"Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+TotalTallyStock+" & ToatalEnteredQuantity: "+ToatalEnteredQuantity+" Quantity are same");
     			logger.error("Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+TotalTallyStock+" & ToatalEnteredQuantity: "+ToatalEnteredQuantity+" Quantity are same");
     			
     		}
            else if(ToatalEnteredQuantity==0 && TotalTallyStock==0 && TotalDiffQuantity==0) 
     		{
     			softAssert.assertTrue(true,"Item "+itemName+" not present in mismatch report as it's not have any mismatch");
     			logger.info("Item "+itemName+" not present in mismatch report as it's not have any mismatch");
     		}
            else if((ToatalEnteredQuantity!=0 || TotalTallyStock!=0) && TotalDiffQuantity==0) 
     		{
     			softAssert.assertTrue(true,"Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+" with DiffQuantity: "+TotalDiffQuantity);
     			logger.info("Item "+itemName+" present in mismatch report as even with TotalTallyStock: "+" with DiffQuantity: "+TotalDiffQuantity);
     		}
	             */   
	                // Optional: Print detailed summary for the item
	                //dataReader.printItemSummary(itemName);
	            }else {
     			
     			logger.error("But there is error with validation");
     		}
	        }
	    }else if(rs.isSearchPresent()==false) 
	    {
	    	softAssert.assertTrue(true,"There is no Missmatch items in the Missmatch report");
			logger.info("There is no Missmatch items in the Missmatch report");
	    }else 
	    {
	    	softAssert.assertTrue(false,"Error with loading/Unknow error while fetching report data");
			logger.error("Error with loading/Unknow error while fetching report data");
	    }
		
		
		
	}
	
    
}


   


