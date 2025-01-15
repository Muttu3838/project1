package easyStockPageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.AppiumBy;

import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class StockSchedulingPage extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	
	
	 public StockSchedulingPage (FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
	 
		@AndroidFindBy(accessibility  = "Settings")
		private WebElement sidebarsetting;
		
		@AndroidFindBy(accessibility  = "Stock Scheduling")
		private WebElement StockSchedulingbtn;
		
	    @AndroidFindBy(accessibility = "D\nDaily")
		private List<WebElement> Dailybtns;
	    
	    @AndroidFindBy(accessibility = "W\nWeekly")
		private List<WebElement> Weeklybtns;
	    
	    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"S\"])[1]")
		private WebElement Sunday;
	    
	    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"M\"]")
		private WebElement Monday;
	    
	    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"T\"])[1]")
		private WebElement Tuesday;
	    
	    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"W\"]")
		private WebElement Wednesday;
	    
	    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"T\"])[2]")
		private WebElement Thursday;
	    
	    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"F\"]")
	  	private WebElement Friday;
	    
	    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"S\"])[2]")
	  	private WebElement Saturday;
	    
	    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Branch\"]/following-sibling::android.widget.Button[1]")
		private WebElement clickbranch; 
	    
	    @FindBy(xpath  = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]")
		private WebElement Notification;
		
	    @FindBy(xpath  = "//android.view.View[@content-desc=\"Stock Schedule has been set Successfully.\"]")
	    private WebElement succefullmsg;
	   
		 public void clickAllElementswithDaily() throws InterruptedException {
		        int maxScrollAttempts = 2;
		        int scrollCount = 0;
		        
		       

		        while (scrollCount < maxScrollAttempts) {
		            // Click all currently visible elements
		            
		        	if(scrollCount==0) 
		        	{
		        	
		        	for (WebElement element : Dailybtns) {
		                try {
		                	
		                    if (element.isDisplayed()) {
		                        element.click();
		                        System.out.println("Clicked an element");
		                        /*Thread.sleep(200);
		                       if(isNotificationDisplayed())
		                        {
		                        	swipeAction(Notification, "right");
		                        }
		                        System.out.println("Notification swipped succefully");*/
		                       
		                     waitForElementTobeDisappear(succefullmsg, driver);
		                 
		                     }
		                  } catch (Exception e) {
		                    continue;
		                }
		                
		             
		                scrollCount++;
                        
		              }
		        	}

		          
                   if(scrollCount < maxScrollAttempts && scrollCount > 0);
                    {  
                    	swipeAction(Dailybtns.getFirst(), "up");
                    	System.out.println("First element swipped down");
                    	Dailybtns.getLast().click();
                    	/*if(isNotificationDisplayed())
                    	{
                    		swipeAction(Notification, "left");
                    	}*/
                    	waitForElementTobeDisappear(succefullmsg, driver);
                   
                    	;
                    	scrollCount++;
                   
                    }
		            
		        }
		       
		    }
		 
		 
		 public void clickAllElementswithWeekly(String Day) throws InterruptedException {
		        int maxScrollAttempts = 2;
		        int scrollCount = 0;
		        
		       

		        while (scrollCount < maxScrollAttempts) {
		            // Click all currently visible elements
		            
		        	if(scrollCount==0) 
		        	{
		        	
		        	for (WebElement element : Weeklybtns) {
		                try {
		                	
		                    if (element.isDisplayed()) {
		                        
		                    	element.click();
		                    	System.out.println("Clicked an element");
		                        ChooseDay(Day);
		                    	
		                        
		                        /*Thread.sleep(200);
		                       if(isNotificationDisplayed())
		                        {
		                        	swipeAction(Notification, "right");
		                        }
		                        System.out.println("Notification swipped succefully");*/
		                       
		                     waitForElementTobeDisappear(succefullmsg, driver);
		                 
		                     }
		                  } catch (Exception e) {
		                    continue;
		                }
		                
		             
		                scrollCount++;
                     
		              }
		        	}

		          
                if(scrollCount < maxScrollAttempts && scrollCount > 0);
                 {  
                 	swipeAction(Weeklybtns.getFirst(), "up");
                 	System.out.println("First element swipped down");
                 	Weeklybtns.getLast().click();
                 	ChooseDay(Day);
                 	/*if(isNotificationDisplayed())
                 	{
                 		swipeAction(Notification, "left");
                 	}*/
                 	waitForElementTobeDisappear(succefullmsg, driver);
                
                 	;
                 	scrollCount++;
                
                 }
		            
		        }
		       
		    }
		
		public void ChooseDay(String Day)
		{
			
			if(Day.equalsIgnoreCase("Monday"))
            {
            	Monday.click();
            	
            }else if(Day.equalsIgnoreCase("Tuesday"))
            {
            	Tuesday.click();
            	
            }else if(Day.equalsIgnoreCase("Wednesday"))
            {
            	Wednesday.click();
            	
            }else if(Day.equalsIgnoreCase("Thursday"))
            {
            	Thursday.click();
            	
            }else if(Day.equalsIgnoreCase("Friday"))
            {
            	Friday.click();
            	
            }else if(Day.equalsIgnoreCase("Saturday"))
            {
            	Saturday.click();
            	
            }else if(Day.equalsIgnoreCase("Sunday"))
            {
            	Sunday.click();
            }
		}
		
		
		public WebElement getbranch(String branchnm) {
	        String xpath = String.format("//android.widget.Button[@content-desc=\"%s\"]", branchnm);
	        return driver.findElement(AppiumBy.xpath(xpath));
	        }
		
		public void setClickbranch()
		{
			waitForElementTobeClickable(clickbranch, driver);
		      clickbranch.click();
		}
		
		
		public void settingClick()
		{
			waitForElementTobeClickable(sidebarsetting, driver);
			sidebarsetting.click();
		      
		}
		
		public void stockSchedulingClick()
		{
			waitForElementTobeClickable(StockSchedulingbtn, driver);
			StockSchedulingbtn.click();
		      
		}
		
		public void selectbranch(String branchnm)
		{
			waitForElementTobeClickable(getbranch(branchnm), driver);
		      getbranch(branchnm).click();
		}
	 
		public void clickAllDailyElements() throws InterruptedException {
	        clickAllElementswithDaily();
	    }
	    
	 
		   public boolean isNotificationDisplayed()
			{
				try {
		        	//waitForElementToAppear(toastmesg, driver);
		            return isElementPresent(Notification);
		        } catch (NoSuchElementException e) {
		            return false;//null != null;
		        }
				
			      
			}

			public void slideNotication()
			{
				waitForElementTobeClickable(Notification, driver);
			      swipeAction(Notification, "right");
			}
			
			public void clickAllWeeklyElements(String Day) throws InterruptedException
			{
				clickAllElementswithWeekly(Day);
			}
	 
	 

}
