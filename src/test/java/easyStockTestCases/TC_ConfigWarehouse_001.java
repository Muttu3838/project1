package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.confWarehousePage;

public class TC_ConfigWarehouse_001 extends BaseClass {

	String Warehousecnt ="All"; 
	    @Test()
	    public void Configure() throws InterruptedException {
		
		 confWarehousePage conf =new confWarehousePage(driver);
		 conf.settingClick();
		 conf.confWarehouseClick();
		 conf.branchClick();
		 conf.selectbranch(branchnm);
		 String subcount=conf.subscriptionCount();
	        System.out.println("Subscription count is "+subcount);
	        
	        int count = Integer.parseInt(subcount.substring(subcount.lastIndexOf(":") + 1).trim());
		       
			if(count<5)
			{    
				conf.ClickBack();
				conf.ClickBack();
				System.out.println("Subscription count is '"+count+"' need to get more Warehouses");
				//Implemented Addwarehouse from subscription
				
				TC_subscriptionAddon_001 subscription=new TC_subscriptionAddon_001();
				subscription.Addonbywallet("warehouse",5);
				System.out.println("Warehouses got added successfully");
				conf.settingClick();
				conf.confWarehouseClick();
			}
		 
	        Configure(Warehousecnt);  // Call the Addon method with the default value or dynamic value
	        //conf.ClickBack();  change
	        //conf.ClickBack();  change
	        softAssert.assertTrue(true, "Warehouse configuration successfull");
	 }

	    public void Configure(String Warehousecnt) throws InterruptedException {
	        
	        confWarehousePage conf =new confWarehousePage(driver);
	        
	        /* String subcount=conf.subscriptionCount();
	        System.out.println("Subscription count is "+subcount);
	       
	        // Check if the input string is not null and contains a colon
	        if (subcount != null && subcount.contains(":")) {
	            // Split the string by ":"
	            String[] parts = subcount.split(":");
	            
	            // Ensure that there's something after the colon and trim any extra spaces
	            if (parts.length > 1) {
	                String lastPart = parts[1].trim();
	                
	                // Convert the last part into an integer
	                try {
	                    int lastDigit = Integer.parseInt(lastPart);
	                    System.out.println("The last digit is: " + lastDigit);  // Output: 0
	                } catch (NumberFormatException e) {
	                    System.out.println("Error: The part after the colon is not a valid integer.");
	                }
	            } else {
	                System.out.println("No value found after the colon.");
	            }
	        } else {
	            System.out.println("Input string is null or does not contain a colon.");
	        }
	       int count = Integer.parseInt(subcount.substring(subcount.lastIndexOf(":") + 1).trim());
	       
			if(count<5)
			{    
				conf.ClickBack();
				conf.ClickBack();
				conf.FinalBack();
				System.out.println("Subscription count is '"+count+"' need to get more Warehouses");
				//need to implement Addwarehouse from subscription
				TC_subscriptionAddon_001 subscription=new TC_subscriptionAddon_001();
				subscription.Addon("warehouse",5);
			}*/
	    
	        if (Warehousecnt.equalsIgnoreCase("1st")) {
	        	
	        	
	        	conf.chooseWarehouse1();
	        	
	        } 
	        else if (Warehousecnt.equalsIgnoreCase("2nd")) 
	        {
	        	conf.chooseWarehouse2();
	        	 
	        } 
	        else if (Warehousecnt.equalsIgnoreCase("3rd")) 
	        {
	        	conf.chooseWarehouse3();
	        	 
	        } 
	        else if (Warehousecnt.equalsIgnoreCase("4th"))
	        {
	        	conf.chooseWarehouse4();
	        } 
	        else if (Warehousecnt.equalsIgnoreCase("5th")) 
	        {
	        	conf.chooseWarehouse5();
	        } else if (Warehousecnt.equalsIgnoreCase("All")) 
	        {
	        	if(conf.isWarehouse1Enabled() && conf.isWarehouse2Enabled() && conf.isWarehouse3Enabled() && conf.isWarehouse4Enabled() && conf.isWarehouse5Enabled())
	        	{
	        		conf.chooseWarehouse1();
	        		conf.chooseWarehouse2();
	        		conf.chooseWarehouse3();
	        		conf.chooseWarehouse4();
	        		conf.chooseWarehouse5();
		        
	        	}else if(conf.isWarehouse1selected()==false && conf.isWarehouse2selected()==false && conf.isWarehouse3selected()==false && conf.isWarehouse4selected()==false && conf.isWarehouse5selected()==false)
	        	{
	        		logger.info("Warehouses has been already configured");
	        	    
	        	}
	        }
	        if(conf.isWarehouse1selected() || conf.isWarehouse2selected() || conf.isWarehouse3selected() || conf.isWarehouse4selected() || conf.isWarehouse5selected())
        	{
	        	conf.clickConfigure(); 
	        	conf.clickYes();
	        softAssert.assertTrue(true, "Configuration successfull");
        	}
	       
	        	
	        
	    }
}
