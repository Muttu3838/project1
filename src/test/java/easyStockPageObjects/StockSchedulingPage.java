package easyStockPageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
	    
	    @AndroidFindBy(accessibility = "M\nMonthly")
		private List<WebElement> Monthlybtns;
	    
	   /* @AndroidFindBy(xpath = "//android.view.View")
		private List<WebElement> Datepick;*/
	    
	    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"S\"])[2]/parent::android.view.View/following-sibling::android.view.View//android.view.View")
		private List<WebElement> Datepick;
	    
	    @AndroidFindBy(accessibility = "Y\nYearly")
		private List<WebElement> yearlybtn;
	    
	    @AndroidFindBy(xpath = "//android.view.View")
		private List<WebElement> Choosemonth;
	    
	    @AndroidFindBy(xpath = "(//android.view.View[@content-desc])[1]")
		private WebElement ChooseMonthbtn;
	    
	    @AndroidFindBy(accessibility = "Confirm")
		private WebElement Confirmbtn;
	    
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
		 
		 public void clickAllElementswithMonthly(String FromDate,String ToDate) throws InterruptedException {
		        int maxScrollAttempts = 2;
		        int scrollCount = 0;
		        
		       

		        while (scrollCount < maxScrollAttempts) {
		            // Click all currently visible elements
		            
		        	if(scrollCount==0) 
		        	{
		        	
		        	for (WebElement element : Monthlybtns) {
		                try {
		                	
		                    if (element.isDisplayed()) {
		                        
		                    	element.click();
		                    	System.out.println("Clicked an element");
		                    	TimeUnit.SECONDS.sleep(1);
		                        selectDateRange2(FromDate, ToDate);
		                        TimeUnit.SECONDS.sleep(1);
		                    	Confirmbtn.click();
		                    	TimeUnit.SECONDS.sleep(1);
		                        
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
              	swipeAction(Monthlybtns.getFirst(), "up");
              	System.out.println("First element swipped down");
              	TimeUnit.SECONDS.sleep(1);
              	Monthlybtns.getLast().click();
              	TimeUnit.SECONDS.sleep(1);
              	selectDateRange2(FromDate, ToDate);
              	TimeUnit.SECONDS.sleep(1);
              	Confirmbtn.click();
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
		 
		 public void findMonth(String Month) {
		        
		        
		        
			 // Remove any whitespace
		            WebElement month = Choosemonth.stream()
		                .filter(element -> element.getDomAttribute("content-desc").contains(Month))
		                .findFirst()
		                .orElseThrow(() -> new NoSuchElementException("No checkbox found with name: " + Month));
		            
		            month.click();
		            
		            
		            System.out.println("selected date as " + Month);
		            
		 }
		    
		 
		 public void chooseMonth(String month)
		 {
			 findMonth(month);
		 }
		 
		 public void clickAllElementswithYearly(String FromDate,String ToDate,String month) throws InterruptedException {
		        int maxScrollAttempts = 2;
		        int scrollCount = 0;
		        
		       

		        while (scrollCount < maxScrollAttempts) {
		            // Click all currently visible elements
		            
		        	if(scrollCount==0) 
		        	{
		        	
		        	for (WebElement element : yearlybtn) {
		                try {
		                	
		                    if (element.isDisplayed()) {
		                        
		                    	element.click();
		                    	System.out.println("Clicked an element");
		                    	TimeUnit.SECONDS.sleep(1);
		                        ChooseMonthbtn.click();
		                        TimeUnit.SECONDS.sleep(1);
		                        chooseMonth(month);
		                        TimeUnit.SECONDS.sleep(1);
		                        selectDateRange2(FromDate, ToDate);
		                    	Confirmbtn.click();
		                        
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
           	swipeAction(yearlybtn.getFirst(), "up");
           	System.out.println("First element swipped down");
           	
           	yearlybtn.getLast().click();
           	TimeUnit.SECONDS.sleep(1);
            ChooseMonthbtn.click();
            TimeUnit.SECONDS.sleep(1);
            chooseMonth(month);
            TimeUnit.SECONDS.sleep(1);
           	selectDateRange2(FromDate, ToDate);
           	TimeUnit.SECONDS.sleep(1);
           	Confirmbtn.click();
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
		 
		 
		 
		public void selectDateRange(String FromDate,String ToDate) {
		        // Split the comma-separated string into array
		        String[] FromDates = FromDate.split(" ");
		        String[] ToDates = ToDate.split(" ");
		        // Trim and click each warehouse checkbox
		        for (String Date : FromDates) {
		            String trimmedName = Date.trim(); // Remove any whitespace
		            WebElement checkbox = Datepick.stream()
		                .filter(element -> element.getDomAttribute("content-desc").contains(Date))
		                .findFirst()
		                .orElseThrow(() -> new NoSuchElementException("No checkbox found with name: " + trimmedName));
		            
		            checkbox.click();
		            // Add a small delay if needed
		            try {
		                Thread.sleep(500);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		        for (String Date : ToDates) {
		            String trimmedName = Date.trim(); // Remove any whitespace
		            WebElement checkbox = Datepick.stream()
		                .filter(element -> element.getDomAttribute("content-desc").contains(Date))
		                .findFirst()
		                .orElseThrow(() -> new NoSuchElementException("No checkbox found with name: " + trimmedName));
		            
		            checkbox.click();
		            // Add a small delay if needed
		            try {
		                Thread.sleep(500);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		 
		 //Second method to select date
		/*  public void selectDateRange2(String fromDate, String toDate) {
			 
		
		        // deSelect from date
			    
			 // Select to from date
			    
		        selectDate(fromDate, "From");
		        
		        selectDate(fromDate, "From");
			    
		        // Select to  todate
		        selectDate(toDate, "To");
		        
		       
		    }
		    
		    private void selectDate(String date, String type) {
		        if (date == null || date.trim().isEmpty()) {
		            throw new IllegalArgumentException(type + " date cannot be null or empty");
		        }
		        
		        try {
		            WebElement dateElement = Datepick.stream()
		                .filter(element -> element.getDomAttribute("content-desc")
		                    .contains(date))//.trim()))
		                .findFirst()
		                .orElseThrow(() -> new NoSuchElementException(
		                    String.format("No date element found for %s date: %s", type, date)));
		            
		            dateElement.click();
		            System.out.println("selected date as " + date);
		            
		        } catch (Exception e) {
		            throw new RuntimeException(
		                String.format("Failed to select %s date: %s. Error: %s", 
		                    type, date, e.getMessage()), e);
		        }
		    } */
		    
		
		//new selectDate range method
		public void selectDateRange2(String fromDate, String toDate) {
		    if (fromDate == null || toDate == null) {
		        throw new IllegalArgumentException("From date and To date cannot be null");
		    }

		    try {
		        // Select from date (only once)
		        selectDate(fromDate, "From");
		        
		        selectDate(fromDate, "From");
		        // Select to date
		        selectDate(toDate, "To");
		        
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to select date range: " + e.getMessage(), e);
		    }
		}

		private void selectDate(String date, String type) {
		    if (date == null || date.trim().isEmpty()) {
		        throw new IllegalArgumentException(type + " date cannot be null or empty");
		    }

		    try {
		        WebElement dateElement = Datepick.stream()
		            .filter(element -> {
		                String contentDesc = element.getDomAttribute("content-desc");
		                if (contentDesc != null) {
		                    // Extract the date part after the comma
		                    // Pattern: "Thu, 01 January 2025" -> we want "01"
		                    String[] parts = contentDesc.split(",");
		                    if (parts.length > 1) {
		                        String datePart = parts[1].trim().split(" ")[0]; // Get "01"
		                        // Ensure two-digit format for comparison
		                        String paddedDate = date.length() == 1 ? "0" + date : date;
		                        return datePart.equals(paddedDate);
		                    }
		                }
		                return false;
		            })
		            .findFirst()
		            .orElseThrow(() -> new NoSuchElementException(
		                String.format("No date element found for %s date: %s", type, date)));

		        dateElement.click();
		        System.out.println("Selected " + type + " date: " + dateElement.getDomAttribute("content-desc"));

		    } catch (Exception e) {
		        throw new RuntimeException(
		            String.format("Failed to select %s date: %s. Error: %s",
		                type, date, e.getMessage()), e);
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
			
			public void selectDay(String Day) throws InterruptedException
			{
				clickAllElementswithWeekly(Day);
			}
			
			public void selectDaterange(String FromDate,String ToDate) throws InterruptedException
			{
				
				clickAllElementswithMonthly(FromDate,ToDate);
			}
			
			public void selectMonth(String FromDate,String ToDate,String month) throws InterruptedException
			{
				
				clickAllElementswithYearly(FromDate,ToDate,month);
			}
	 
	 

}
