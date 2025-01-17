package easyStockPageObjects;




import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class UserStockUpdate extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	
	 public UserStockUpdate(FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
	 
	 @AndroidFindBy(accessibility = "Verify")
	 private WebElement verifybtn;

	 @AndroidFindBy(xpath = "//android.widget.EditText/preceding-sibling::android.widget.Button")
	 private WebElement branchclick;
	 
	 @AndroidFindBy(xpath = "//android.widget.EditText")
	 private WebElement Searchwarehouse;
	 
	 @AndroidFindBy(xpath = "//android.widget.CheckBox[@content-desc=\"Warehouses\"]/following-sibling::android.view.View[1]/android.view.View[1]/android.view.View")
	 private WebElement Searchresultwarehouse;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Back\"]/parent::android.view.View/following-sibling::android.widget.EditText[1]")
	 private WebElement ItemSearchfield;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View/android.widget.ImageView/android.widget.EditText[1]")
	 private WebElement StockValueField;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View//android.widget.ImageView/android.widget.EditText")
	 private List<WebElement> StockValueFields;
	
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View/android.widget.ImageView/android.widget.EditText[1]/following-sibling::android.widget.Button[1]")
	 private WebElement uploadbtn;
	 
	 public WebElement getbranch(String branchnm) {
	        String xpath = String.format("//android.widget.Button[@content-desc=\"%s\"]", branchnm);
	        return driver.findElement(AppiumBy.xpath(xpath));
	    }
	 
	 
	 
	 
	 
	 public void clickVerify()
		{
			waitForElementTobeClickable(verifybtn, driver);
		      verifybtn.click();
		}
	 
	 public void clickBrach()
		{
			waitForElementTobeClickable(branchclick, driver);
			branchclick.click();
		}
	 
	 public void selectbranch(String branchnm)
		{
			waitForElementTobeClickable(getbranch(branchnm), driver);
		      getbranch(branchnm).click();
		}
	 
	 public void searchWarehouse(String warehousename)
		{
			waitForElementTobeClickable(Searchwarehouse, driver);
			Searchwarehouse.click();
			Searchwarehouse.sendKeys(warehousename);
		}
	 
	 public void clickWarehouseResult()
		{
			waitForElementTobeClickable(Searchresultwarehouse, driver);
			Searchresultwarehouse.click();
		}
	 
	 public void searchItem(String ItemName)
		{
		 waitForElementTobeClickable(ItemSearchfield, driver);
		 ItemSearchfield.click();
		 ItemSearchfield.sendKeys(ItemName);
		}
	 
	 public void enterStock(String StockQuantity)
		{
		 waitForElementTobeClickable(StockValueField, driver);
		 StockValueField.click();
		 StockValueField.sendKeys(StockQuantity);
		}
	 
	 public void enterAllItems2(String StockQuantity) throws InterruptedException {
	       
	        
                      for (WebElement element : StockValueFields) {
	                     try {
	                	
	                    	waitForElementToAppear(element, driver); 
	                    	element.click();
	                    	element.sendKeys(StockQuantity);
	                    	uploadbtn.click();
	                        
	                        /*Thread.sleep(200);
	                       if(isNotificationDisplayed())
	                        {
	                        	swipeAction(Notification, "right");
	                        }
	                        System.out.println("Notification swipped succefully");*/
	                       
	                     //waitForElementTobeDisappear(succefullmsg, driver);
	                 
	                     
	                     } catch (Exception e) {
	                         continue;
	                }
	              }
	        	

	            	boolean canScrollMore;
	        		do
	        		{
	        		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
	        			    "left", 100, "top", 500, "width", 200, "height", 500,
	        			    "direction", "down",
	        			    "percent", 5.0
	        			    
	        			));
	        		 for (WebElement element : StockValueFields) {
	                     try {
	                	
	                    	waitForElementToAppear(element, driver); 
	                    	element.click();
	                    	element.sendKeys(StockQuantity);
	                    	uploadbtn.click();
	                        
	                        /*Thread.sleep(200);
	                       if(isNotificationDisplayed())
	                        {
	                        	swipeAction(Notification, "right");
	                        }
	                        System.out.println("Notification swipped succefully");*/
	                       
	                     //waitForElementTobeDisappear(succefullmsg, driver);
	                 
	                     
	                     } catch (Exception e) {
	                         continue;
	                    }
	                
	                  }
	            }while(canScrollMore);
	            	
	            	
	            	/*if(isNotificationDisplayed())
	            	{
	            		swipeAction(Notification, "left");
	            	}*/
	            //waitForElementTobeDisappear(succefullmsg, driver);
	           
	       }
	        	
	        	
      /*if(scrollCount < maxScrollAttempts && scrollCount > 0);
       {  
       	swipeAction(StockValueFields.getFirst(), "up");
       	System.out.println("First element swipped down");
       	StockValueFields.getLast().click();
       	StockValueFields.getLast().sendKeys(StockQuantity);
       	uploadbtn.click();
       	
       	/*if(isNotificationDisplayed())
       	{
       		swipeAction(Notification, "left");
       	}
       //waitForElementTobeDisappear(succefullmsg, driver);
      
       	
       	scrollCount++;
       	
      
       }*/
	            
	 
	       
	    
	
	 /*
	 
	 //3rd Technoque to scroll and enter
	 public void enterAllItems3(String stockQuantity) {
		    if (StockValueFields.isEmpty()) {
		        throw new NoSuchElementException("No checkboxes found on the page");
		    }

		    // Keep track of processed elements
		    Set<String> processedElements = new HashSet<>();
		    boolean canScrollMore = true;

		    while (canScrollMore) {
		        // Get currently visible elements
		        List<WebElement> visibleCheckboxes = StockValueFields.stream()
		            .filter(element -> {
		                try {
		                    return element.isDisplayed();
		                } catch (Exception e) {
		                    return false;
		                }
		            })
		            .collect(Collectors.toList());

		        // Process visible elements
		        for (WebElement checkbox : visibleCheckboxes) {
		            try {
		                // Generate unique identifier for element
		                String elementIdentifier = generateElementIdentifier(checkbox);
		                
		                if (processedElements.contains(elementIdentifier)) {
		                    continue;
		                }

		                waitForElementToAppear(checkbox, driver);
		                checkbox.click();
		                checkbox.sendKeys(stockQuantity);
		                uploadbtn.click();
		                processedElements.add(elementIdentifier);

		                Thread.sleep(500);

		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            } catch (Exception e) {
		                System.out.println("Failed to process checkbox: " + e.getMessage());
		                continue;
		            }
		        }

		        // Try to scroll using different methods
		        canScrollMore = scrollToNextSection();
		    }
		}
	 
	// Method 1: Using AndroidUIAutomator for Android
	 private boolean scrollUsingAndroidUIAutomator() {
	     try {
	         String previousPageSource = driver.getPageSource();
	         
	       (FlutterAndroidDriver) driver).findElementByAndroidUIAutomator(
	             "new UiScrollable(new UiSelector().scrollable(true))" +
	             ".scrollForward()"); 
	      
	         // Check if page content changed after scroll
	         return !previousPageSource.equals(driver.getPageSource());
	     } catch (Exception e) {
	         return false;
	     }
	 }

	 // Method 2: Using JavaScript Executor
	 private boolean scrollUsingJavaScript() {
	     try {
	         String previousPageSource = driver.getPageSource();
	         
	         JavascriptExecutor js = (JavascriptExecutor) driver;
	         js.executeScript("arguments[0].scrollIntoView(true);", 
	             StockValueFields.get(StockValueFields.size() - 1));
	         
	         return !previousPageSource.equals(driver.getPageSource());
	     } catch (Exception e) {
	         return false;
	     }
	 }

	 // Method 3: Using Mobile: scroll command
	 private boolean scrollUsingMobileCommand() {
	     try {
	         String previousPageSource = driver.getPageSource();
	         
	         HashMap<String, String> scrollObject = new HashMap<>();
	         scrollObject.put("direction", "down");
	         driver.executeScript("mobile: scroll", scrollObject);
	         
	         return !previousPageSource.equals(driver.getPageSource());
	     } catch (Exception e) {
	         return false;
	     }
	 }

	 // Try different scroll methods in sequence
	 private boolean scrollToNextSection() {
	     // Try Android UIAutomator first (for Android)
	    if (driver instanceof FlutterAndroidDriver && scrollUsingAndroidUIAutomator()) {
	         return true;
	     } 
	     
	     // Try JavaScript scroll
	     if (scrollUsingJavaScript()) {
	         return true;
	     }
	     
	     // Try Mobile scroll command
	     if (scrollUsingMobileCommand()) {
	         return true;
	     }
	     
	     // If none of the scroll methods worked, we've probably reached the end
	     return false;
	 }

	 private String generateElementIdentifier(WebElement element) {
	     try {
	         // Combine multiple attributes to create a unique identifier
	         return element.getDomAttribute("resourceId") + "|" +
	                element.getDomAttribute("content-desc") + "|" +
	                element.getLocation().toString();
	     } catch (Exception e) {
	         return element.toString();
	     }
	 }
	  */
	 
	 public void enterAllItems3(String stockQuantity) {
		    if (StockValueFields.isEmpty()) {
		        throw new NoSuchElementException("No checkboxes found on the page");
		    }

		    Set<String> processedElements = new HashSet<>();
		    boolean canScrollMore = true;
		    int consecutiveNoNewElements = 0;
		    int maxAttempts = 3; // Stop after 3 attempts with no new elements

		    while (canScrollMore) {
		        // Store count of processed elements before processing current view
		        int previousProcessedCount = processedElements.size();

		        // Get currently visible elements
		        List<WebElement> visibleCheckboxes = StockValueFields.stream()
		            .filter(element -> {
		                try {
		                    return element.isDisplayed();
		                } catch (Exception e) {
		                    return false;
		                }
		            })
		            .collect(Collectors.toList());

		        // Process visible elements
		        for (WebElement checkbox : visibleCheckboxes) {
		            try {
		                String elementIdentifier = generateElementIdentifier(checkbox);
		                
		                if (processedElements.contains(elementIdentifier)) {
		                    continue;
		                }

		                waitForElementToAppear(checkbox, driver);
		                checkbox.click();
		                checkbox.sendKeys(stockQuantity);
		                uploadbtn.click();
		                processedElements.add(elementIdentifier);

		                Thread.sleep(500);

		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            } catch (Exception e) {
		                System.out.println("Failed to process checkbox: " + e.getMessage());
		                continue;
		            }
		        }

		        // Check if we found and processed any new elements
		        int currentProcessedCount = processedElements.size();
		        if (currentProcessedCount > previousProcessedCount) {
		            consecutiveNoNewElements = 0;
		        } else {
		            consecutiveNoNewElements++;
		        }

		        // Stop if we've had too many attempts with no new elements
		        if (consecutiveNoNewElements >= maxAttempts) {
		            canScrollMore = false;
		            continue;
		        }

		        // Attempt to scroll using available methods
		        canScrollMore = scrollToNextSection();
		    }
		}

		private boolean scrollToNextSection() {
		    try {
		        WebElement lastVisibleElement = findLastVisibleElement();
		        Point lastPosition = lastVisibleElement != null ? lastVisibleElement.getLocation() : null;

		        
		            return scrollAndroidWithVerification(lastPosition);
		       
		    } catch (Exception e) {
		        System.out.println("Scroll failed: " + e.getMessage());
		        return false;
		    }
			
			
		}

		private boolean scrollAndroidWithVerification(Point lastPosition) {
		    // Try each scroll method in sequence until one works
		    return tryScrollGesture(lastPosition) ||
		           trySwipeScroll(lastPosition) ||
		           tryScrollIntoView(lastPosition);
		}

		private boolean tryScrollGesture(Point lastPosition) {
		    try {
		        Map<String, Object> scrollParams = new HashMap<>();
		        scrollParams.put("left", 100);
		        scrollParams.put("top", 100);
		        scrollParams.put("width", 200);
		        scrollParams.put("height", 200);
		        scrollParams.put("direction", "down");
		        scrollParams.put("percent", 10.0);

		        driver.executeScript("mobile: scrollGesture", scrollParams);
		        Thread.sleep(500);
		        return verifyScrollSuccess(lastPosition);
		    } catch (Exception e) {
		        System.out.println("ScrollGesture failed: " + e.getMessage());
		        return false;
		    }
		}

		/*private boolean tryUiAutomatorScroll(Point lastPosition) {
		    try {
		        ((FlutterAndroidDriver) driver).findElementByAndroidUIAutomator(
		            "new UiScrollable(new UiSelector().scrollable(true))" +
		            ".setAsVerticalList().scrollForward()");
		        Thread.sleep(500);
		        return verifyScrollSuccess(lastPosition);
		    } catch (Exception e) {
		        System.out.println("UiAutomator scroll failed: " + e.getMessage());
		        return false;
		    }
		} */

		private boolean trySwipeScroll(Point lastPosition) {
		    try {
		        Dimension size = driver.manage().window().getSize();
		        int startY = (int) (size.height * 0.7);
		        int endY = (int) (size.height * 0.3);
		        int centerX = size.width / 2;

		        Map<String, Object> swipeParams = new HashMap<>();
		        swipeParams.put("startX", centerX);
		        swipeParams.put("startY", startY);
		        swipeParams.put("endX", centerX);
		        swipeParams.put("endY", endY);
		        swipeParams.put("duration", 200);

		        driver.executeScript("mobile: swipe", swipeParams);
		        Thread.sleep(500);
		        return verifyScrollSuccess(lastPosition);
		    } catch (Exception e) {
		        System.out.println("Swipe scroll failed: " + e.getMessage());
		        return false;
		    }
		}

		private boolean tryScrollIntoView(Point lastPosition) {
		    try {
		        List<WebElement> allElements = driver.findElements(By.xpath("//android.widget.ScrollView/*"));
		        if (!allElements.isEmpty()) {
		            WebElement lastElement = allElements.get(allElements.size() - 1);
		            
		            Map<String, Object> params = new HashMap<>();
		            params.put("elementId", ((RemoteWebElement) lastElement).getId());
		            params.put("strategy", "scroll");
		            
		            driver.executeScript("mobile: scrollIntoView", params);
		            Thread.sleep(500);
		            return verifyScrollSuccess(lastPosition);
		        }
		        return false;
		    } catch (Exception e) {
		        System.out.println("ScrollIntoView failed: " + e.getMessage());
		        return false;
		    }
		}

		/*private boolean scrollIOSWithVerification(Point lastPosition) {
		    try {
		        HashMap<String, Object> scrollObject = new HashMap<>();
		        scrollObject.put("direction", "down");
		        scrollObject.put("element", findScrollableContainer());
		        driver.executeScript("mobile: scroll", scrollObject);
		        
		        return verifyScrollSuccess(lastPosition);
		    } catch (Exception e) {
		        return scrollUsingJavaScriptWithVerification(lastPosition);
		    }
		} */

		private boolean verifyScrollSuccess(Point lastPosition) {
		    
			if (lastPosition == null) {
		        return true;
		    }

		    try {
		        Thread.sleep(300);
		        WebElement newLastVisible = findLastVisibleElement();
		        if (newLastVisible == null) {
		            return false;
		        }

		        Point newPosition = newLastVisible.getLocation();
		        return Math.abs(newPosition.getY() - lastPosition.getY()) > 10;
		    } catch (Exception e) {
		        System.out.println("Verification failed: " + e.getMessage());
		        return false;
		    }
		}

		private WebElement findLastVisibleElement() {
		    return StockValueFields.stream()
		        .filter(element -> {
		            try {
		                return element.isDisplayed();
		            } catch (Exception e) {
		                return false;
		            }
		        })
		        .reduce((first, second) -> second)
		        .orElse(null);
		}

		private String generateElementIdentifier(WebElement element) {
		    try {
		        StringBuilder identifier = new StringBuilder();
		        String[] attributes = {"resourceId", "content-desc", "text", "class"};
		        
		        for (String attribute : attributes) {
		            try {
		                String value = element.getDomAttribute(attribute);
		                if (value != null && !value.isEmpty()) {
		                    identifier.append(attribute).append(":").append(value).append("|");
		                }
		            } catch (Exception e) {
		                // Skip if attribute not available
		            }
		        }
		        
		        try {
		            identifier.append("loc:").append(element.getLocation().toString());
		        } catch (Exception e) {
		            // Skip location if not available
		        }
		        
		        return identifier.toString();
		    } catch (Exception e) {
		        return element.toString();
		    }
		}
	 
	
	 
	 //1st enter value technique
	 public void enterAllItems(String StockQuantity) {
	        if (StockValueFields.isEmpty()) {
	            throw new NoSuchElementException("No checkboxes found on the page");
	        }
	        
	        for (WebElement checkbox : StockValueFields) {
	            try {
	                waitForElementToAppear(checkbox, driver); 
	                checkbox.click();
	                checkbox.sendKeys(StockQuantity);
	                uploadbtn.click();
	                // Add a small delay between clicks
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                System.out.println("Failed to click checkbox: " + e.getMessage());
	                continue; // Continue with next checkbox if one fails
	            }
	        }
	        
	    }
	  
	  public void enterValuesAll(String StockQuantity) throws InterruptedException
		{
			//waitForElementTobeClickable(StockValueField, driver);
			enterAllItems2(StockQuantity);
		}
	 
	 public void clickUpload()
		{
			waitForElementTobeClickable(uploadbtn, driver);
			uploadbtn.click();
		}
}
