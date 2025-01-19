package easyStockTestCases;

import java.util.List;
import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserStockUpdate;

public class TC_UserStockUpdate_01 extends BaseClass {
	
	@Test
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
		
		us.enterStock("30");
		
		us.clickUpload();
		
		
		
		
		
	}
	
	@Test
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
		
		
		
		us.enterValuesAll("10");
		
		us.ClickBulkUpload();
		
		
		
		
		
		
	}
	
	@Test
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
			
			
			
			us.enterValuesAll("10");
			
			us.ClickBulkUpload();
			
			us.ClickBack();
            }
        }
		
		
	@Test
	public void stockValidationAllWarehouses() throws InterruptedException
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
			
			us.enterValuesAll2();
			
			us.ClickBulkUpload();
			
			us.ClickBack();
            }
        }
		
		
		
		
		
		
	}


