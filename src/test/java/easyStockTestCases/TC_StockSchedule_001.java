package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.StockSchedulingPage;

public class TC_StockSchedule_001 extends BaseClass {
	

	

	public void schedule(StockSchedulingPage ss) throws InterruptedException {
		
		//StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		ss.settingClick();
		
		ss.stockSchedulingClick();
		
		ss.setClickbranch();
		
		ss.selectbranch(branchnm);
		
		
		
		/*if(ss.isNotificationDisplayed())
		{
		ss.slideNotication();
		logger.info("Notication got displayed and swiped succefully");
		
		}else
		{
			logger.info("Notication not coming");
		}*/
		
	}
	
	
	@Test(priority = 3)
	public void Daily() throws InterruptedException
	{
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		schedule(ss);
	   
		ss.clickAllDailyElements();
	}
	
	@Test(priority = 2)
    public void Weekly() throws InterruptedException {
		
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		
		schedule(ss);
		
		ss.selectDay("Monday");
		
		/*if(ss.isNotificationDisplayed())
		{
		ss.slideNotication();
		logger.info("Notication got displayed and swiped succefully");
		
		}else
		{
			logger.info("Notication not coming");
		}*/
		
	}
	
	 @Test(priority = 1)
     public void Monthly() throws InterruptedException {
		
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		
		schedule(ss);
		
		ss.selectDaterange("16", "20");
		
		/*if(ss.isNotificationDisplayed())
		{
		ss.slideNotication();
		logger.info("Notication got displayed and swiped succefully");
		
		}else
		{
			logger.info("Notication not coming");
		}*/
		
	}
	 
	 @Test(priority = 0)
     public void Yearly() throws InterruptedException {
		
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		
		schedule(ss);
		
		ss.selectMonth("16","20","February");
		
		/*if(ss.isNotificationDisplayed())
		{
		ss.slideNotication();
		logger.info("Notication got displayed and swiped succefully");
		
		}else
		{
			logger.info("Notication not coming");
		}*/
		
	}
	 

}
