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
import easyStockUtilities.InventoryDetailsUtil;
import easyStockUtilities.InventoryFileParser;
import easyStockUtilities.InventoryManager;
import easyStockUtilities.InventoryManager.ItemDetails;
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
	
	@Test(enabled = false)
	public void missMatchItemDetails() throws InterruptedException, IOException 
	
	{
         UserStockUpdate us=new UserStockUpdate(driver);
		
		us.clickVerify();
		logger.info("Clicked on Verify button");
		
		us.clickBrach();
		logger.info("Clicked on Branch Dropdown");
		
		us.selectbranch(branchnm);
		logger.info("Selected Branch "+ branchnm);
		
		us.clickItemBtn();
		logger.info("Clicked on Items Options ");
		
       
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
		
		
		List<String> allItems = parser.getAllItemNames();
		
		InventoryManager details = new InventoryManager();
		
		List<ItemDetails> itemsList = new ArrayList<>();
		
	    for (String ItemNames : allItems)
			
			{
				
	    	us.searchItem(ItemNames);
			logger.info("Searched for Item "+ItemNames);
			
			us.ItemClick();
			logger.info("Clicked on Item "+ItemNames);
			
			
			us.ItemContentDesc();
			
			
			
			itemsList.add(details.parseAndStoreItemDetails("ContentDesc"));
            System.out.println("Stored item: " + details);
			
		
			}
	}
	
	
	
	@Test
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
		
		us.clickBrach();
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
		
        //MissmatchItemsReport(allItemDetails);
        // Verify the results
        
    }
	
	@Test(dependsOnMethods = "testInventoryDetails")
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
		
		rs.ClickEdit();
		
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
	}else 
	{
    	
    	softAssert.assertTrue(true,"There is No any Missmatch items in the report");
		logger.info("There is No any Missmatch items in the report");
    }
	
 }

	
    
}


   


