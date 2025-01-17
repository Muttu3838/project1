package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.UserStockUpdate;

public class TC_UserStockUpdate_01 extends BaseClass {
	
	@Test
	public void stockVerify()
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
	public void stockVerifyAll() throws InterruptedException
	{
		UserStockUpdate us=new UserStockUpdate(driver);
		LoginPage lp=new LoginPage(driver);
		
		lp.FinalBack();
		
		us.clickVerify();
		
		us.clickBrach();
		
		us.selectbranch(branchnm);
		
		us.searchWarehouse("Centeral");
		
		us.clickWarehouseResult();
		
		//us.searchItem("Agasi");
		
		//us.enterStock("30");
		
		us.enterValuesAll("10");
		
		us.clickUpload();
		
		
		
		
		
	}

}
