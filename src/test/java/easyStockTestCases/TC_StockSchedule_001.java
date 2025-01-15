package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.StockSchedulingPage;

public class TC_StockSchedule_001 extends BaseClass {
	

	
	@Test
	public void scheduleDaily() throws InterruptedException {
		
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		ss.settingClick();
		
		ss.stockSchedulingClick();
		
		ss.setClickbranch();
		
		ss.selectbranch(branchnm);
		
		ss.clickAllDailyElements();
		
		/*if(ss.isNotificationDisplayed())
		{
		ss.slideNotication();
		logger.info("Notication got displayed and swiped succefully");
		
		}else
		{
			logger.info("Notication not coming");
		}*/
		
	}
	
	  @Test
      public void scheduleWeekly() throws InterruptedException {
		
		StockSchedulingPage ss=new StockSchedulingPage(driver);
		
		ss.settingClick();
		
		ss.stockSchedulingClick();
		
		ss.setClickbranch();
		
		ss.selectbranch(branchnm);
		
		ss.clickAllWeeklyElements("Monday");
		
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
