package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.StockSchedulingPage;

public class TC_StockSchedule_001 extends BaseClass {
	

	

	public void schedule(StockSchedulingPage ss) throws InterruptedException {
		
		//StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		ss.settingClick();
		logger.info("Clicked on setting from sidebar");
		
		ss.stockSchedulingClick();
		logger.info("Clicked on Stockschedule from Setting Page");
		
		ss.setClickbranch();
		logger.info("Clicked on branch dropdown");
		
		ss.selectbranch(branchnm);
		logger.info("Selected Branch as "+branchnm);
		
		
		
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
		logger.info("Scheduled all Warehouses for Day");
	}
	
	@Test(priority = 2)
    public void Weekly() throws InterruptedException {
		
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		
		schedule(ss);
		
		ss.selectDay("Monday");
		logger.info("Scheduled all Warehouses for Monday for every week");
		
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
		logger.info("Scheduled all Warehouses from 16th to 20th for every month");
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
		logger.info("Scheduled all Warehouses from 16th to 20th for every Year in February Month");
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
