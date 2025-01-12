package easyStockTestCases;

import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import easyStockPageObjects.UploadPage;
import easyStockPageObjects.confWarehousePage;

public class TC_Upload_002 extends BaseClass {

	

	@Test(enabled = false) //Newly adding warehouse scenario & need to attach this to create user scenario 
	public void uploadNewWarehouse() throws FindFailed, InterruptedException, IOException {

		UploadPage up = new UploadPage(driver);

		Thread.sleep(2000);
		up.setSidebarUpload();
		logger.info("Clicked on sidebar upload");
		
		if(up.isContinuepresent())
				{		
		          up.continueBtn();
				}
		
		up.setSelectFile();

		Thread.sleep(2000);
		up.selectWarehouseMaster();

		up.selectFiletype();

		up.selectXml();

		Thread.sleep(2000);
		up.uploadFile();

		up.searchClick();

		up.searchFiletxt("Dm GD Master");

		//up.selectFilebyName("Dm GD Master");  //This is to select File dynamically
		//Thread.sleep(2000);
		up.selectFile();

		up.clickSubmit();
		
		
       
		
		if (up.iscongratsMsg() && up.isSuccessMsgWarehouse())//congragulation.equalsIgnoreCase("Congratulations")) //conmsg.equalsIgnoreCase("Warehouses uploaded successfully."
		{
			
			up.continueBtn();
			
			Thread.sleep(3000);
			TC_ConfigWarehouse_001	configure=new TC_ConfigWarehouse_001();
			confWarehousePage	cp=new confWarehousePage(driver);
			String subcount=cp.subscriptionCount();
	        
	        
	        int count = Integer.parseInt(subcount.substring(subcount.lastIndexOf(":") + 1).trim());
		       
			if(count<5)
			{    
				cp.ClickBack();
				//cp.FinalBack();
				System.out.println("Subscription count is '"+count+"' need to get more Warehouses");
				
				TC_subscriptionAddon_001 subscription=new TC_subscriptionAddon_001();
				subscription.Addon("warehouse",5);
				uploadExistingWarehouse();
			}
			configure.Configure("All");
			
			
		}else 
		{
			
			softAssert.assertTrue(false, "failed");
		}
		
		
	}
	
	@Test(enabled = true,priority = 1) // for already existing warehouse scenario
	public void uploadExistingWarehouse() throws FindFailed, InterruptedException, IOException {

		UploadPage up = new UploadPage(driver);
		//LoginPage lp = new  LoginPage(driver);
		
		//Thread.sleep(2000);
		up.setSidebarUpload();
		logger.info("Clicked on sidebar upload");
		
		up.setClickbranch();
		logger.info("Clicked on branch");
		
		up.selectbranch(branchnm);
		logger.info("Selected respective branch");
		
		
		up.setSelectFile();
		logger.info("Clicked on select file");
		Thread.sleep(2000);
		
		up.selectWarehouseMaster();
		logger.info("Selected on Warehouse master");
		
		up.selectFiletype();
		logger.info("Clicked on Filetype");
		
		up.selectXml();
		logger.info("Selectd file as XML");
		Thread.sleep(2000);
		
		up.uploadFile();
		logger.info("Clicked on upload file");
		
		up.searchClick();
		logger.info("Clicked on Search bar");
		
		up.searchFiletxt("Dm GD Master");
		logger.info("Searched for warehouse file");
		
		
		//up.selectFilebyName("Dm GD Master");
		//Thread.sleep(2000);
		up.selectFile();
		logger.info("File selected");
		
		up.clickSubmit();
		logger.info("clicked on submit");
		
		//String conmsg=up.issSuccessMsgWarehouse();
		//System.out.println(conmsg);
		
		
		//up.confirmNo();
		if (up.isSuccessMsgWarehouse() && up.isconfirmYes())//conmsg.equalsIgnoreCase("Warehouses uploaded successfully." ))
		{
			softAssert.assertTrue(true, "Warehouses upload got Passed");
			up.confirmYes();
			
			
			TC_ConfigWarehouse_001	configure=new TC_ConfigWarehouse_001();
			confWarehousePage	cp=new confWarehousePage(driver);
			String subcount=cp.subscriptionCount();
	        
	        
	        int count = Integer.parseInt(subcount.substring(subcount.lastIndexOf(":") + 1).trim());
		       
			if(count<5)
			{    
				cp.ClickBack();
				//cp.FinalBack();
				System.out.println("Subscription count is '"+count+"' need to get more Warehouses");
				//need to implement Addwarehouse from subscription
				TC_subscriptionAddon_001 subscription=new TC_subscriptionAddon_001();
				subscription.Addon("warehouse",5);
				uploadExistingWarehouse();
			}
			
			configure.Configure("All");
			
			
		}
		else 
		{
			softAssert.assertTrue(false, "failed");
		}
		
		//lp.setprofilebtn();
	}
	
	
	@Test(enabled = true,priority = 2) // for already existing warehouse scenario
	public void uploadItems() throws FindFailed, InterruptedException, IOException {

		UploadPage up = new UploadPage(driver);
		
		
		//Thread.sleep(2000);
		up.setSidebarUpload();
		logger.info("Clicked on sidebar upload");
		
		up.setClickbranch();
		logger.info("Clicked on branch");
		
		up.selectbranch(branchnm);
		logger.info("Selected respective branch");
		
		up.setSelectFile();
		logger.info("Clicked on select file");

		up.selectItemMaster();
		logger.info("Selected Item master");
		
		up.selectFiletype();
		logger.info("Clicked file type");
		
		up.selectXml();
		logger.info("Selected file type as xml");
		
		Thread.sleep(2000);
		up.uploadFile();
		logger.info("Clicked on upload button");
		
		up.searchClick();
		logger.info("Clicked on Search");
		
		up.searchFiletxt("DM Item Master");
		logger.info("Entered File name");

		
		//up.selectFilebyName("DM Item Master");
		//Thread.sleep(2000);
		up.selectFile();
		logger.info("Selected file");
		
		up.clickSubmit();
		logger.info("Clicked on submit button");
		
		//String conmsg1=up.isSuccessMsgItems();
        
		if (up.isSuccessMsgItems())//conmsg1.equalsIgnoreCase("Items uploaded successfully."))
		{
			//System.out.println(conmsg1);
		    softAssert.assertTrue(true, "Upload Items got Passed");
			
			
			
		}
	else 
		{
			softAssert.assertTrue(false, "failed");
		}
	
	}
	
	@Test(enabled = true,priority = 3) // for already existing warehouse scenario
	public void uploadOpeningStock() throws FindFailed, InterruptedException, IOException {

		UploadPage up = new UploadPage(driver);
		
		//Thread.sleep(2000);
		up.setSidebarUpload();
		logger.info("Clicked on sidebar upload");
		
		up.setClickbranch();
		logger.info("Clicked on branch");
		
		up.selectbranch(branchnm);
		logger.info("Selected respective branch");

		up.setSelectFile();
		logger.info("Clicked on slectfile");
		
		//Thread.sleep(2000);
		up.selectOpeningStock();
		logger.info("Selected Opening Stock");
		
		up.selectFiletype();
		logger.info("Clicked on filetype");
		
		up.selectASCII();
		logger.info("Selected filetype ASCII");
		
		//Thread.sleep(2000);
		up.uploadFile();
		logger.info("Clicked on upload button");
		
		up.searchClick();
		logger.info("Clicked on Searchbar");
		
		up.searchFiletxt("Dm Opening Stk");
		logger.info("Entered File name");
		
		//up.selectFilebyName("Dm Opening Stk");
		//Thread.sleep(2000);
		up.selectFile();
		logger.info("Selected file");
		
		up.clickSubmit();
		logger.info("Clicked on Submit button");
		
		//String conmsg2=up.issuccessMsgTallyStock();
        
		if (up.issuccessMsgTallyStock())//conmsg2.equalsIgnoreCase("Tally stock added succesfully."))
		{
			if(up.isNotificationDisplayed())
			{
			up.slideNotication();
			logger.info("Notication got displayed and swiped succefully");
			
			}else
			{
				logger.info("Notication not coming");
			}
			//System.out.println(conmsg2);
		    softAssert.assertTrue(true, "Upload Opening stock got Passed");
			
		}
	else 
		{
			softAssert.assertTrue(false, "failed");
		}
	}
	
	@Test(enabled = true,priority = 4) // for already existing warehouse scenario
	public void uploadClosingStock() throws FindFailed, InterruptedException, IOException {

		UploadPage up = new UploadPage(driver);
		
		
		//Thread.sleep(2000);
		up.setSidebarUpload();
		logger.info("Clicked on sidebar upload");
		
		up.setClickbranch();
		logger.info("Clicked on branch");
		
		up.selectbranch(branchnm);
		logger.info("Selected respective branch");

		up.setSelectFile();
		logger.info("Clicked on selectfile");
		
		//Thread.sleep(2000);
		up.selectClosingStock();
		logger.info("Selected as Closing stock");
		
		up.selectFiletype();
		logger.info("Clicked on Filetype");
		
		up.selectASCII();
		logger.info("Selected file as ASCII");
		
		//Thread.sleep(2000);
		up.uploadFile();
		logger.info("Clicked on upload button");
		
		up.searchClick();
		logger.info("Clicked on Searchbar");
		
		up.searchFiletxt("Dm Closing Stk");
		logger.info("Enterd File name");
		
		//up.selectFilebyName("Dm Closing Stk");
		//Thread.sleep(2000);
		up.selectFile();
		logger.info("Selected file");
		
		up.clickSubmit();
		logger.info("Clicked on submit button");
		
		//String conmsg2=up.issuccessMsgTallyStock();
        
		if (up.issuccessMsgTallyStock())//conmsg2.equalsIgnoreCase("Tally stock added succesfully."))
		{
			if(up.isNotificationDisplayed())
			{
			up.slideNotication();
			logger.info("Notication got displayed and swiped succefully");
			
			}else
			{
				logger.info("Notication not coming");
			}
			//System.out.println(conmsg2);
		    softAssert.assertTrue(true, "Upload closing stock got Passed");
		    
			
			
			
		}
	else 
		{
			softAssert.assertTrue(false, "failed");
		}
		
		
	}


}
