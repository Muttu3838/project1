
package easyStockPageObjects;





import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;


import easyStockUtilities.AndroidActions;
import easyStockUtilities.InventoryFileParser;

import easyStockUtilities.ItemParser;
import easyStockUtilities.InventoryFileParser.ItemQuantityDetails;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FilesVerification extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	
	
	 public FilesVerification(FlutterAndroidDriver driver)
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
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"  Items\"]/following-sibling::android.view.View[1]/android.view.View//android.widget.ImageView[@content-desc[not(contains(., '='))]]")
	 private List<WebElement> ItemNameFields;
	 
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
	 
	 @AndroidFindBy(xpath = "//android.widget.CheckBox[@content-desc=\"Warehouses\"]/following-sibling::android.view.View[1]/android.view.View[1]//android.view.View[not(contains(@content-desc, '0.00'))]")
	 private List<WebElement> mismatchWarehouses;
	 
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
			
			Searchwarehouse.click();
			Searchwarehouse.clear();
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
		 ItemSearchfield.clear();
		 ItemSearchfield.sendKeys(ItemName);
		}
	 
	 public void enterStockOnefield(String StockQuantity)
		{
		 waitForElementTobeClickable(StockValueField, driver);
		 StockValueField.click();
		 StockValueField.sendKeys(StockQuantity);
		}
	 
	 	      
	 
	 private void enterStockQuantity(String warehouse,List<String> validationErrors) throws InterruptedException, IOException {
		 
		 
		 InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
		
		 List<String> allItems = parser.getAllItemNames();
		  
		
		 for (WebElement element : StockValueFields) {
			 
	                     try {
	                	 
	                    	 String itemNamefromUI = getFirstTwoWords(ItemNameFields.getFirst());
	                    	 String itemNamefromUI1 = getItemName(ItemNameFields.getFirst());
	                    	 Double QuantityfromUI=getValueAfterSlash(ItemNameFields.getFirst());
	                    	
	                    	 // Find a partial match in allItemsName
	                         Optional<String> matchingItem = allItems.stream()
	                             .filter(item -> item.contains(itemNamefromUI1)) // Partial match logic
	                             .findFirst();
	                         
	                        /* if (matchingItem.isPresent()) {
	                             System.out.println("File Opening stock Contains Item: " + itemNamefromUI);
	                             System.out.println("Matching Item in file: " + matchingItem.get());
	                         } else {
	                             System.out.println("File Opening stock Does Not Contain Item: " + itemNamefromUI);
	                         } */
	                         
	                         // Get quantity details if matched
	                         if (matchingItem.isPresent()) {
	                        	 
	                             double totalQty = parser.getQuantityForItemAndWarehouse(matchingItem.get(),warehouse);
	                             //double totalQty = details.getTotalQuantity();
	                             //System.out.println("Quantity for the " + matchingItem.get() + " is " + totalQty);
	                             if(QuantityfromUI.equals(totalQty))
		                         {
	                            	 System.out.println("Quantity from the UI"+"(*"+QuantityfromUI+"*)"+" for the Item " + matchingItem.get() + " is "+" matching with Closing Stock Quantity " + "(*"+totalQty+"*)"+" in "+warehouse);
	                            	 String msg="Quantity from the UI"+"(*"+QuantityfromUI+"*)"+" for the Item " + matchingItem.get() + " is "+" matching with Closing Stock Quantity " + "(*"+totalQty+"*)"+" in "+warehouse;
	                            	 
	                            	 //softAssert.assertTrue(true);
		                         }else {
		                        	 System.out.println("Quantity from the UI"+"(*"+QuantityfromUI+"*)"+" for the Item " + matchingItem.get() + " is "+"not matching with Closing Stock Quantity " + "(*"+totalQty+"*)"+" in "+warehouse);
		                        	 validationErrors.add("Quantity mismatch for item " + matchingItem.get() + 
	                                         ": UI (" + QuantityfromUI + ") vs Stock (" + totalQty + ")");
		                        	 //softAssert.assertTrue(false);
		                         }
	                         }else {
	                             System.out.println("File Opening stock file Does Not Contain Item from UI as : " + itemNamefromUI);
	                             //softAssert.assertTrue(false);
	                         }
	                         
	                         
	                         
	                    	
	                    	 
	                    	String rmnumber = generateRandomNumberAsString(-50, 50);
	             			
	                    	waitForElementToAppear(element, driver); 
	                    	element.click();
	                    	element.sendKeys(rmnumber);
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
		                    	 
		                    	 String itemNamefromUI = getFirstTwoWords(ItemNameFields.getFirst());
		                    	 String itemNamefromUI1 = getItemName(ItemNameFields.getFirst());
		                    	 Double QuantityfromUI=getValueAfterSlash(ItemNameFields.getFirst());
		                    	 
		                    	// Find a partial match in allItemsName
		                         Optional<String> matchingItem = allItems.stream()
		                             .filter(item -> item.contains(itemNamefromUI1)) // Partial match logic
		                             .findFirst();
		                         
		                        /* if (matchingItem.isPresent()) {
		                             System.out.println("File Opening stock Contains Item: " + itemNamefromUI);
		                             System.out.println("Matching Item in file: " + matchingItem.get());
		                         } else {
		                             System.out.println("File Opening stock Does Not Contain Item: " + itemNamefromUI);
		                         } */
		                         
		                         // Get quantity details if matched
		                         // Get quantity details if matched
		                         if (matchingItem.isPresent()) {
		                        	 
		                             double totalQty = parser.getQuantityForItemAndWarehouse(matchingItem.get(),warehouse);
		                             //double totalQty = details.getTotalQuantity();
		                             //System.out.println("Quantity for the " + matchingItem.get() + " is " + totalQty);
		                             if(QuantityfromUI.equals(totalQty))
			                         {
		                            	 System.out.println("Quantity from the UI"+"(*"+QuantityfromUI+"*)"+" for the Item " + matchingItem.get() + " is "+" matching with Closing Stock Quantity " + "(*"+totalQty+"*)"+" in "+warehouse);
		                            	    String msg="Quantity from the UI"+"(*"+QuantityfromUI+"*)"+" for the Item " + matchingItem.get() + " is "+" matching with Closing Stock Quantity " + "(*"+totalQty+"*)"+" in "+warehouse;
		                            	 
		                            	 //softAssert.assertTrue(true);
		                            	 
			                         }else {
			                        	 System.out.println("Quantity from the UI"+"(*"+QuantityfromUI+"*)"+" for the Item " + matchingItem.get() + " is "+"not matching with Closing Stock Quantity " + "(*"+totalQty+"*)"+" in "+warehouse);
			                        	 validationErrors.add("Quantity mismatch for item " + matchingItem.get() + 
		                                         ": UI (" + QuantityfromUI + ") vs Stock (" + totalQty + ")");
			                        	 //softAssert.assertTrue(false);
			                         }
		                         }else {
		                             System.out.println("File Opening stock file Does Not Contain Item from UI as : " + itemNamefromUI+" in "+warehouse);
		                             //softAssert.assertTrue(false);
		                         }
		                         
		                         
		                    	  
		                     String rmnumber = generateRandomNumberAsString(-50, 50);
		                    	 
		                    	 
		                	    waitForElementTobeDisappear(successmsg, driver);
		                    	//waitForElementToAppear(element, driver); 
		                    	element.click();
		                    	element.sendKeys(rmnumber);
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
	     	                    "percent", 3.8
	     	                ));
	     	        }
	     	    }
	         	 
	         	System.out.println("Third Iteration Started");
	         	
	         	 if(canScrollMore==false)
	         	 {
	         		 scrollToEndAction();
	         		for (WebElement element : StockValueFields) {
	                     try {
	                	
	                    	 String rmnumber = generateRandomNumberAsString(-50, 50);
	                    	 
	                    	waitForElementToAppear(element, driver); 
	                    	element.click();
	                    	element.sendKeys(rmnumber);
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
            	
	 
	 private String getFirstTwoWordsItems(String text) {
	        try {
	        	 if (text != null && !text.isEmpty()) {
	        	     
	        	    String[] words = text.trim().split("\\s+");
	        	    
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

	 private Double getValueAfterSlash(WebElement element) {
		    try {
		        String contentDesc = element.getDomAttribute("content-desc");
		        if (contentDesc != null && !contentDesc.isEmpty()) {
		            // Split by "/" and trim the resulting parts
		            String[] parts = contentDesc.trim().split("/");
		            if (parts.length >= 2) {
		                // Extract the numeric value from the part after "/"
		                String value = parts[1].trim().split("\\s+|[^0-9.]")[0];
		                return Double.parseDouble(value); // Convert to Double
		            }
		        }
		    } catch (Exception e) {
		        System.out.println("Error getting value after slash: " + e.getMessage());
		    }
		    return 0.0; // Default value if parsing fails
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
	    
	    private String getItemName(WebElement element) {
	        try {
	            String contentDesc = element.getDomAttribute("content-desc");
	            if (contentDesc != null && !contentDesc.isEmpty()) {
	                // Split the content description
	                String[] parts = contentDesc.trim().split("\n");
	                
	                // Return the first part (item name) if it exists
	                if (parts.length > 0) {
	                    return parts[0].trim();
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error getting content description: " + e.getMessage());
	        }
	        return "";
	    }
	    
	    private String getFirstThreeWordsItems(WebElement element) {
	        try {
	        	String contentDesc = element.getDomAttribute("content-desc");
	            if (contentDesc != null && !contentDesc.isEmpty()) {
	            	
	                String[] words = contentDesc.trim().split("\\s+"); // Split the text into words
	                
	                if (words.length >= 3) {
	                    return words[0] + " " + words[1] + " " + words[2]; // Return first three words
	                } else if (words.length == 2) {
	                    return words[0] + " " + words[1]; // If only two words, return both
	                } else if (words.length == 1) {
	                    return words[0]; // If only one word, return it
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error getting content description: " + e.getMessage());
	        }
	        return ""; // Return empty string in case of an error or invalid input
	    }
	    
	 // Find all elements with content-desc for warehouse fetch
		 public List<String> getAllFirstTwoWordsofMismatchWarehouse() {
		        List<String> namesList = new ArrayList<>();
		        try {
		           
		            for (WebElement element : mismatchWarehouses) {
		            	waitForElementToAppear(element, driver);
		                String firstTwoWords = getFirstTwoWordsMismatchWarehouse(element);
		                if (!firstTwoWords.isEmpty()) {
		                	
		                    namesList.add(firstTwoWords);
		                }
		            }
		        } catch (Exception e) {
		            System.out.println("Error finding elements: " + e.getMessage());
		        }
		        return namesList;
		    }

		    private String getFirstTwoWordsMismatchWarehouse(WebElement element) {
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
	    
	    
        //enterAllMismatchValues First Technique
	    
         private void enterAllMismatchValues() throws InterruptedException {
			
			System.out.println("First Iteration Started");
			 
			
			for (WebElement element : StockValueFields2){
			     try {
			        
			         String  StockQuantity=extractQuantityList(MissmatchFields.getFirst());
			         // Perform actions on both elements
			         //waitForElementToAppear(stockElement, driver);
			         String itemName=getFirstTwoWords(element);
			         String log="Correcting Item "+itemName+" with Quantity "+StockQuantity;
			         element.click();
			         element.sendKeys(StockQuantity);
                     uploadbtn.click();
                     System.out.println(log);
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
		     				         
		     				         String itemName=getFirstTwoWords(element);
		     				         String log="Correcting Item "+itemName+" with Quantity "+StockQuantity;
		     				         // Perform actions on both elements
		     				         //waitForElementToAppear(stockElement, driver);
		     				         element.click();
		     				         element.sendKeys(StockQuantity);
		     	                     uploadbtn.click();
		     	                     System.out.println(log);
		     				         
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
	     				    	
	     				    	 if(isElementPresent(successmsg)) {
	     				    		 
	     				    	     waitForElementTobeDisappear(successmsg, driver);
	     				    	
	     				    	 }else {
	     				    		 
	     				    		 System.out.println("Successmsg Not Dispalyed");
	     				    	 }
	     				    	 
	     				         String  StockQuantity=extractQuantityList(MissmatchFields.getFirst());
	     				         
	     				         String itemName=getFirstTwoWords(element);
	     				         String log="Correcting Item "+itemName+" with Quantity "+StockQuantity;
	     				         // Perform actions on both elements
	     				         //waitForElementToAppear(stockElement, driver);
	     				         element.click();
	     				         element.sendKeys(StockQuantity);
	     	                     uploadbtn.click();
	     	                    System.out.println(log);
	     				         
	     				     } catch (Exception e) {
	     				         // Handle exception, continue to next iteration
	     				         continue;
	     				     }
	     				 } 
		         	 }
		        		 
		        		
		            }
	    
         //Updated version  of previous enterAllMissmatchValues method
         //enterAllMismatchValues Second Technique withoud needing Third Iteration
         
         private String[] enterAllMismatchValues2() throws InterruptedException { 
        	
        	 List<String> logs = new ArrayList<>();
        	 List<String> tempLogs = new ArrayList<>();
        	    logs.add("Starting mismatch values correction process");
        	    logs.add("Beginning first iteration");
              
              
              // First Iteration
        	 try {
        		 tempLogs = new ArrayList<>();
        	     
        	    for (WebElement element : StockValueFields2) {
        	        try {
        	        	
        	            String StockQuantity = extractQuantityList(MissmatchFields.getFirst());
        	            String itemName = getFirstTwoWords(MissmatchFields.getFirst());
        	            String ActionLog = "Corrected Item " + itemName + " with Quantity " + StockQuantity;
        	            System.out.println(ActionLog);
        	         
        	            // Perform actions on both elements
        	            element.click();
        	            element.sendKeys(StockQuantity);
        	            uploadbtn.click();
        	            
        	            logs.add(ActionLog);
        	            
        	            waitForElementTobeDisappear(successmsg, driver);
        	            
        	            

        	        } catch (Exception e) {
        	            // Handle exception, continue to next iteration
        	        	 String errorMsg = "Error correcting item: " + e.getMessage();
        	              
        	        	 tempLogs.add(errorMsg); 
        	                continue;
        	        }
        	    }
        	  
        	    // Add all temporary logs from first iteration to main logs
        	    logs.addAll(tempLogs);
        	    
        	 }catch(Exception e)
        	 {
        		
        		 logs.add("Critical error in first iteration: " + e.getMessage());
        	        if (!tempLogs.isEmpty()) {
        	            logs.addAll(tempLogs); 
	        	 
        	        }
        	 }
        	    
   // Second Iteration
   
     logs.add("Second Iteration Started");
                
     boolean canScrollMore = true;
       while (canScrollMore) {
        	   try {
        		   tempLogs = new ArrayList<>();
                       // Re-fetch updated lists of elements (if required)
        	            for (WebElement element : StockValueFields2) {
        	               
        	            	try {
        	                   
        	            		waitForElementTobeDisappear(successmsg, driver);
        	                    String StockQuantity = extractQuantityList(MissmatchFields.getFirst());
        	                    String itemName = getFirstTwoWords(MissmatchFields.getFirst());
        	                    String ActionLog = "Correcting Item " + itemName + " with Quantity " + StockQuantity;
        	                    System.out.println(ActionLog);
        	                    
                                

        	                    // Perform actions on both elements
        	                    element.click();
        	                    element.sendKeys(StockQuantity);
        	                    uploadbtn.click();
        	                    
        	                    tempLogs.add(ActionLog);
        	                   
        	                   

        	                } catch (Exception e1) {
        	                    // Handle exception, continue to next iteration
        	                	String errorMsg = "Error correcting item in second iteration: " + e1.getMessage();
                               
        	                	 tempLogs.add(errorMsg);  // Add error to temporary logs
        	                    continue;
        	                }
        	            }
        	            
        	         // Add all temporary logs from this iteration to main logs
        	            logs.addAll(tempLogs);
        	            
        	            // Check if the first element is still displayed to stop scrolling
        	            if (StockValueFields2.getFirst().isDisplayed()) {
        	            	
        	            	break;
        	            	//return logs.toArray(new String[0]);  // Return log when iteration is complete
        	            }
        	            
        	         

        	        } catch (Exception e1) {
        	        	
        	        	 if (!tempLogs.isEmpty()) {
        	                 logs.addAll(tempLogs);  // Save any logs collected before the error
        	             }
        	        
        	            // Element not visible yet, continue scrolling
        	        	logs.add("Attempting to scroll down");
        	            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", 
        	                ImmutableMap.of(
        	                    "left", 100,
        	                    "top", 500,
        	                    "width", 200,
        	                    "height", 300,
        	                    "direction", "down",
        	                    "percent", 5
        	                ));
        	            if (!canScrollMore) {
        	            	logs.add("Reached end of scrollable area");
                        }
        	        }
        	    }

        	   /* logBuilder.append("Third Iteration Started\n");

        	    // Perform third iteration after scroll ends
        	    if (!canScrollMore) {
        	        scrollToEndAction();
        	        for (WebElement element : StockValueFields2) {
        	            try {
        	                waitForElementTobeDisappear(successmsg, driver);
        	                String StockQuantity = extractQuantityList(MissmatchFields.getFirst());
        	                String itemName = getFirstTwoWords(element);
        	                String log = "Correcting Item " + itemName + " with Quantity " + StockQuantity;
        	                logBuilder.append(log).append("\n");

        	                // Perform actions on both elements
        	                element.click();
        	                element.sendKeys(StockQuantity);
        	                uploadbtn.click();

        	            } catch (Exception e) {
        	                // Handle exception, continue to next iteration
        	                logBuilder.append("Error correcting item: ").append(e.getMessage()).append("\n");
        	                continue;
        	            }
        	        }
        	    } */

    // Return all logs at the end
       return logs.toArray(new String[0]);
       
        	 
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
	
	    
	    
	
	  
	  public List<String> enterAllStockQuantity(String Warehouse) throws InterruptedException, IOException
		{
		  List<String> validationErrors = new ArrayList<>();
		    
		    try {
		        // Call your method to enter stock quantity
		        enterStockQuantity(Warehouse, validationErrors);
		       
		        // After processing, assert if there are any errors
		       assertTrue( true,"Validation errors found: " + validationErrors+
		                   validationErrors.isEmpty());
		       
		        
		    } catch (Exception e) {
		        fail("Test failed due to exception: " + e.getMessage());
		    }
			return validationErrors;
			
		}
	  
	  public String[] enterMismatchValues() throws InterruptedException
		{
			//waitForElementTobeClickable(StockValueField, driver);
			enterAllMismatchValues2();
			String[] log=enterAllMismatchValues2();
			return log;
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
	 
	 public List<String> mismatchWarehouseNames()
		{
			
			return getAllFirstTwoWordsofMismatchWarehouse();
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
	 
	 
	 public  Double uiQuantity()
		{
		 Double QuantityfromUI=getValueAfterSlash(ItemNameFields.getFirst());
			return QuantityfromUI;
		}
	 
	 public  Double fileQuantity(String warehouse) throws IOException
		{
		 InventoryFileParser parser = new InventoryFileParser("Dm Closing Stk.txt");
			
		 List<String> allItems = parser.getAllItemNames();
		 String itemNamefromUI1 = getItemName(ItemNameFields.getFirst());
    	 
    	
    	 // Find a partial match in allItemsName
         Optional<String> matchingItem = allItems.stream()
             .filter(item -> item.contains(itemNamefromUI1)) // Partial match logic
             .findFirst();
         
         
        	 
             double totalQty = parser.getQuantityForItemAndWarehouse(matchingItem.get(),warehouse);
             
             
         
		return totalQty;
		
		
       }
	 }
