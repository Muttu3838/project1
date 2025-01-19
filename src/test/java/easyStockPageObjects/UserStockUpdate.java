package easyStockPageObjects;




import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.google.common.collect.ImmutableMap;


import easyStockUtilities.AndroidActions;
import io.appium.java_client.AppiumBy;
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
	 
	 @AndroidFindBy(xpath = "//android.widget.CheckBox[@content-desc=\"Warehouses\"]/preceding-sibling::android.widget.EditText[1]")
	 private WebElement Searchwarehouse;
	 
	 @AndroidFindBy(xpath = "//android.widget.CheckBox[@content-desc=\"Warehouses\"]/following-sibling::android.view.View[1]/android.view.View[1]/android.view.View")
	 private WebElement Searchresultwarehouse;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Back\"]/parent::android.view.View/following-sibling::android.widget.EditText[1]")
	 private WebElement ItemSearchfield;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View/android.widget.ImageView/android.widget.EditText[1]")
	 private WebElement StockValueField;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View//android.widget.ImageView[not(contains(@content-desc, '=')) and (contains(@content-desc, 'Excess:') or contains(@content-desc, 'Short:'))]")
	 private List<WebElement> MissmatchFields;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View//android.widget.ImageView[@content-desc[not(contains(., '='))]]/android.widget.EditText")
	 private List<WebElement> StockValueFields;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View//android.widget.ImageView[not(contains(@content-desc, '=')) and (contains(@content-desc, 'Excess:') or contains(@content-desc, 'Short:'))]/android.widget.EditText")
	 private List<WebElement> StockValueFields2;
	 
	//android.widget.ImageView[@content-desc[not(contains(., '='))]]
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View/android.widget.ImageView/android.widget.EditText[1]/following-sibling::android.widget.Button[1]")
	 private WebElement uploadbtn;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Stock Added Successfully\"]")
	 private WebElement successmsg;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"No warehouse schedules planned for today. Please contact admin for more details.\"]")
	 private WebElement nomorewarehousemsg;
	
	 
	 @AndroidFindBy(xpath = "//android.widget.CheckBox[@content-desc=\"Warehouses\"]/following-sibling::android.view.View[1]/android.view.View[1]//android.view.View")
	 private List<WebElement> Warehouses;
	 
	 @FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]/following-sibling::android.widget.Button[2]")
	 private WebElement BulkUploadbtn;
	 
	 @FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]")
	 private WebElement BackButton;
	 
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
		    waitForElementTobeDisappear(nomorewarehousemsg, driver);
			waitForElementTobeClickable(branchclick, driver);
			branchclick.click();
		}
	 
	 public void selectbranch(String branchnm) throws InterruptedException
		{  Thread.sleep(2000);
			waitForElementToAppear(getbranch(branchnm), driver);
		     getbranch(branchnm).click();
		}
	 
	 public void searchWarehouse(String warehousename) throws InterruptedException
		{   
		 Thread.sleep(2000);
			waitForElementTobeClickable(Searchwarehouse, driver);
			Searchwarehouse.clear();
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
	 
	
	      
	 
	 public void enterAllVerifications(String StockQuantity) throws InterruptedException {
		 
		 System.out.println("First Iteration Started");
		 
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
                  
		 System.out.println("Second Iteration Started");

	         	boolean canScrollMore = true;
	         	 while (canScrollMore) {
	     	        try {
	     	        	
	     	        	for (WebElement element : StockValueFields) {
		                     try {
		                	    waitForElementTobeDisappear(successmsg, driver);
		                    	//waitForElementToAppear(element, driver); 
		                    	element.click();
		                    	element.sendKeys(StockQuantity);
		                    	uploadbtn.click();
		                     
		                     } catch (Exception e) {
		                         continue;
		                    }
		                
		                  }
	     	        	
	     	            if (StockValueFields.getFirst().isDisplayed()) {
	     	                return;
	     	            }
	     	        } catch (Exception e) {
	     	            // Element not visible yet, continue scrolling
	     	            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", 
	     	                ImmutableMap.of(
	     	                    "left", 100, "top", 500, "width", 200, "height", 300,
	     	                    "direction", "down",
	     	                    "percent", 5
	     	                ));
	     	        }
	     	    }
	         	 
	         	System.out.println("Third Iteration Started");
	         	
	         	 if(canScrollMore==false)
	         	 {
	         		 scrollToEndAction();
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
	                       
	                     waitForElementTobeDisappear(successmsg, driver);
	                 
	                     
	                     } catch (Exception e) {
	                         continue;
	                }
	              }
	         	 }
	        		 
	        		
	            }
            	
	 
	// Find all elements with content-desc for warehouse fetch
	 public List<String> getAllFirstTwoWords() {
	        List<String> namesList = new ArrayList<>();
	        try {
	           
	            for (WebElement element : Warehouses) {
	            	waitForElementToAppear(element, driver);
	                String firstTwoWords = getFirstTwoWords(element);
	                if (!firstTwoWords.isEmpty()) {
	                    namesList.add(firstTwoWords);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error finding elements: " + e.getMessage());
	        }
	        return namesList;
	    }

	    private String getFirstTwoWords(WebElement element) {
	        try {
	            String contentDesc = element.getDomAttribute("content-desc");
	            if (contentDesc != null && !contentDesc.isEmpty()) {
	                String[] words = contentDesc.trim().split("\\s+");
	                if (words.length >= 2) {
	                    return words[0] + " " + words[1];
	                } else if (words.length == 1) {
	                    return words[0];
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error getting content description: " + e.getMessage());
	        }
	        return "";
	    }
	    
	    

	    
public void enterAllValidation() throws InterruptedException {
			
			System.out.println("First Iteration Started");
			 
			

			for (WebElement element : StockValueFields2){
			     try {
			        
			         String  StockQuantity=extractQuantityList(MissmatchFields.getFirst());
			         // Perform actions on both elements
			         //waitForElementToAppear(stockElement, driver);
			         element.click();
			         element.sendKeys(StockQuantity);
                     uploadbtn.click();
			         waitForElementTobeDisappear(successmsg, driver);
			         
			     } catch (Exception e) {
			         // Handle exception, continue to next iteration
			         continue;
			     }
			 } 
	                  
			 

		         	boolean canScrollMore = true;
		         	 while (canScrollMore) {
		     	        try {
		     	        	System.out.println("Second Iteration Started");
		     	        	  // Re-fetch updated lists of elements
			                
		     	        	
		     	        	
		     	        	
		     	        	for (WebElement element : StockValueFields2){
		     				     try {
		     				    	waitForElementTobeDisappear(successmsg, driver);
		     				         String  StockQuantity=extractQuantityList(MissmatchFields.getFirst());
		     				         // Perform actions on both elements
		     				         //waitForElementToAppear(stockElement, driver);
		     				         element.click();
		     				         element.sendKeys(StockQuantity);
		     	                     uploadbtn.click();
		     				        
		     				         
		     				     } catch (Exception e) {
		     				         // Handle exception, continue to next iteration
		     				         continue;
		     				     }
		     				 } 
		     	        	
		     	           if (StockValueFields2.getFirst().isDisplayed()) {
		     	                return;
		     	            }
		     	        } catch (Exception e) {
		     	            // Element not visible yet, continue scrolling
		     	            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", 
		     	                ImmutableMap.of(
		     	                    "left", 100, "top", 500, "width", 200, "height", 300,
		     	                    "direction", "down",
		     	                    "percent", 5
		     	                ));
		     	        }
		     	    }
		         	 
		         	System.out.println(canScrollMore);
		         	
		         	 if(!canScrollMore)
		         	 {  
		         		System.out.println("Third Iteration Started");
		         		 //TimeUnit.SECONDS.sleep(1000);
		         		scrollToEndAction();
		        
		         		for (WebElement element : StockValueFields2){
	     				     try {
	     				    	waitForElementTobeDisappear(successmsg, driver);
	     				         String  StockQuantity=extractQuantityList(MissmatchFields.getFirst());
	     				         // Perform actions on both elements
	     				         //waitForElementToAppear(stockElement, driver);
	     				         element.click();
	     				         element.sendKeys(StockQuantity);
	     	                     uploadbtn.click();
	     	
	     				         
	     				     } catch (Exception e) {
	     				         // Handle exception, continue to next iteration
	     				         continue;
	     				     }
	     				 } 
		         	 }
		        		 
		        		
		            }
	    

	    

	    
	    
	    public String extractQuantityList(WebElement element) {
	        try {
	            String contentDesc = element.getDomAttribute("content-desc");
	            if (contentDesc != null && !contentDesc.isEmpty()) {
	                // Using regex to directly find "Excess: X" or "Short: X" pattern
	                String regexPattern = "(Excess|Short):\\s*(\\d+\\.?\\d*)";
	                Pattern pattern = Pattern.compile(regexPattern);
	                Matcher matcher = pattern.matcher(contentDesc);
	                
	                if (matcher.find()) {
	                    String type = matcher.group(1);    // "Excess" or "Short"
	                    String number = matcher.group(2);  // The actual number
	                    
	                    // Return with appropriate sign
	                    return type.equals("Excess") ? "-" + number : number;
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error extracting value: " + e.getMessage());
	        }
	        return "";  // Return empty string instead of null
	    }
	
	    
	    
	
	  
	  public void enterValuesAll(String StockQuantity) throws InterruptedException
		{
			//waitForElementTobeClickable(StockValueField, driver);
			enterAllVerifications(StockQuantity);
		}
	  
	  public void enterValuesAll2() throws InterruptedException
		{
			//waitForElementTobeClickable(StockValueField, driver);
			enterAllValidation();
		}
	 
	 public void clickUpload()
		{
			waitForElementTobeClickable(uploadbtn, driver);
			uploadbtn.click();
		}
	 
	 public List<String> warehouseNames()
		{
			
			return getAllFirstTwoWords();
		}
	 
	 public  void ClickBulkUpload()
		{
			waitForElementTobeClickable(BulkUploadbtn, driver);
			BulkUploadbtn.click();;
			
		}
	 
	 public  void ClickBack()
		{
			waitForElementTobeClickable(BackButton, driver);
			BackButton.click();;
			
		}
}
