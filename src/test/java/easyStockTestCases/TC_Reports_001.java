package easyStockTestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import easyStockPageObjects.UserStockUpdate;
import easyStockUtilities.InventoryDetailsUtil;
import easyStockUtilities.InventoryFileParser;
import easyStockUtilities.InventoryManager;
import easyStockUtilities.InventoryManager.ItemDetails;
import easyStockUtilities.ItemInventoryDetails;

public class TC_Reports_001 extends BaseClass {
	
	
    private InventoryDetailsUtil inventoryUtil;
    private static final Logger logger = LogManager.getLogger(TC_Reports_001.class);
    
	public TC_Reports_001() {
        // Call this in the constructor to skip login for this test class
		 inventoryUtil = new InventoryDetailsUtil();
	
        setSkipProfileClick(true);
    }
	
	@Test
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
		
		us.clickBrach();
		logger.info("Clicked on Branch Dropdown");
		
		us.selectbranch(branchnm);
		logger.info("Selected Branch "+ branchnm);
		
		us.clickItemBtn();
		logger.info("Clicked on Items Options ");
		
       
		
		InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
       // InventoryDetailsUtil inventory=new InventoryDetailsUtil();
        
        
        List<String> allItems = parser.getAllItemNames();
        
        List<ItemInventoryDetails> allItemDetails = new ArrayList<>();

        for (String itemName : allItems) {
            
        	Thread.sleep(2000);
                // Search and click operations using your existing UtilityService
                us.searchItem(itemName);
                logger.info("Searched for Item " + itemName);

                us.ItemClick();
                logger.info("Clicked on Item " + itemName);

                // Get warehouse elements using your existing method
              List<WebElement>  warehouseElements1 = us.findWarehouseElements();

                // Process the elements using our utility
                ItemInventoryDetails itemDetails = 
                        inventoryUtil.processWarehouseElements(itemName, warehouseElements1);
                    allItemDetails.add(itemDetails);

                    us.ClickBack();
            } 
        }

        // Verify the results
        
    }

   


