package easyStockTestCases;

import java.io.IOException;
import java.util.List;


import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserStockUpdate;
import easyStockUtilities.AndroidActions;

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
	
	@Test(enabled = false)
	public void stockVerifywithWarehouseName() throws InterruptedException
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
		
		us.searchWarehouse("Rice Godown");  //Pulses Godown  , Centeral Warehouse  , Masala Godown  , Rice Godown
		
		us.clickWarehouseResult();
		
		
		
		us.enterAllStockQuantity();
		
		us.ClickBulkUpload();
		
		
		
		
		
		
	}
	
	@Test(priority = 3)
	public void stockVerifyAllWarehouses() throws InterruptedException
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
		
		
		for (String names : WarehouseNames) {
			
			System.out.println(names);
			us.searchWarehouse(names);  
			
			us.clickWarehouseResult();
			
			us.enterAllStockQuantity();
			
			us.ClickBulkUpload();
			
			us.ClickBack();
            }
        }
		
		
	
		
		
		
		
	}


