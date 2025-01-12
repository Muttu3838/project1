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

public class UserPage extends AndroidActions{

private FlutterAndroidDriver driver;
	
	public UserPage (FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
	
	@AndroidFindBy(accessibility = "Users")
	private WebElement sidebarUser;
	
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button")
	private WebElement Userplusbtn;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
	private WebElement Username;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
	private WebElement Mobilenumber;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[3]")
	private WebElement Password;
	
	@AndroidFindBy(accessibility = "Select role")
	private WebElement Selectrole;

	@AndroidFindBy(accessibility = "Branch Manager")
	private WebElement BranchManager;
	
	@AndroidFindBy(accessibility = "Warehouse Manager")
	private WebElement WarehouseManager;
	
	@AndroidFindBy(accessibility = "Supervisor")
	private WebElement Supervisor;
	
	@AndroidFindBy(accessibility = "Select branch")
	private WebElement Selectbranch;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search branch\"]/android.widget.Button")
	private WebElement Searchbranch;
	
	@AndroidFindBy(xpath  = "//android.widget.EditText")
	private WebElement Searchbranchtxt;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[1]")
	private WebElement branchcheckbox;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[1]")
	private WebElement branchcheckbox1;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[2]")
	private WebElement branchcheckbox2;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[3]")
	private WebElement branchcheckbox3;
	
	@AndroidFindBy(accessibility  = "OK")
	private WebElement OKbtn;
	
	@AndroidFindBy(accessibility = "Select warehouse")
	private WebElement Selectwarehouse;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search Warehouse\"]/android.widget.Button")
	private WebElement Searchwarehouse;
	
	@AndroidFindBy(xpath  = "//android.widget.EditText")
	private WebElement Searchwarehousetxt;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[1]")
	private WebElement checkbox1;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[2]")
	private WebElement checkbox2;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[3]")
	private WebElement checkbox3;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[4]")
	private WebElement checkbox4;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[5]")
	private WebElement checkbox5;
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[@content-desc=\"Main Location\"]")
	private WebElement warehousecheckbox;
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox") //To get all checkbox from the list
	private List<WebElement> allCheckboxes;
	    
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Warehouse*\"]/following-sibling::android.widget.CheckBox") //To click select all for warehouse
	private WebElement selectallWarehouse;	
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Select Category\"]")
	private WebElement SelectCategory;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search Category\"]/android.widget.Button")
	private WebElement SearchCategory;
	
	@AndroidFindBy(xpath  = "//android.widget.EditText")
	private WebElement SearchCategorytxt;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Category\"]/following-sibling::android.widget.CheckBox")
	private WebElement SelectAllCategory;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Select Group\"]")
	private WebElement SelectGroup;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Group\"]/following-sibling::android.widget.CheckBox")
	private WebElement SelectAllGroup;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search Group\"]/android.widget.Button")
	private WebElement SearchGroup;
	
	@AndroidFindBy(xpath  = "//android.widget.EditText")
	private WebElement SearchGrouptxt;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.Button") //To get all button elements from the list
	private List<WebElement> allButtons;
	
	@AndroidFindBy(accessibility = "Done")
	private WebElement Donebtn;
	
	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement SearchUser;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc]")
	private List<WebElement> UseResult;
	
	@FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]")
	private WebElement BackButton;
	
	@FindBy(xpath  = "//android.widget.EditText/android.widget.Button")
	private WebElement searchcancel;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Out of Plan! Kindly subscribe.\"]")
	private WebElement outofplanmsg;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Success\"]")
	private WebElement successmsg;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Mobile number already exist.\"]")
	private WebElement mobilenoalreadyexist;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"user updated successfully\"]")
	private WebElement updatesuccessmsg;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"user list found\"]")
	private WebElement userfound;

	  //1st method to select Warehouses dynamically 
	 public WebElement getCheckbox(String Checkboxname) {
	        String xpath = String.format("//android.widget.CheckBox[@content-desc=\"%s\"]", Checkboxname);
	        return driver.findElement(AppiumBy.xpath(xpath));
	    }
	    
	 // Alternative method using stream if you want to find from existing elements ---Best one for framework
	    public WebElement findCheckbox(String Checkboxname) {
	        return allCheckboxes.stream()
	            .filter(checkbox -> checkbox.getDomAttribute("content-desc").equals(Checkboxname))
	            .findFirst()
	            .orElseThrow(() -> new NoSuchElementException("No checkbox found with name: " + Checkboxname));
	    }
	    
	    
	    public void selectCheckboxes(String checkboxnameNames) {
	        // Split the comma-separated string into array
	        String[] checkboxes = checkboxnameNames.split(",");
	        
	        // Trim and click each warehouse checkbox
	        for (String Names : checkboxes) {
	            String trimmedName = Names.trim(); // Remove any whitespace
	            WebElement checkbox = allCheckboxes.stream()
	                .filter(element -> element.getDomAttribute("content-desc").equals(trimmedName))
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
	    
	    public void selectAllCheckboxes() {
	        if (allCheckboxes.isEmpty()) {
	            throw new NoSuchElementException("No checkboxes found on the page");
	        }
	        
	        for (WebElement checkbox : allCheckboxes) {
	            try {
	                waitForElementToAppear(checkbox, driver); 
	                checkbox.click();
	                
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
	    
	    public void clickMultiCheckbox(String checkboxNames)
		{
			//waitForElementTobeClickable(sidebarUser, driver);
	    	selectCheckboxes(checkboxNames);
		}
	    
	    public void clickAllCheckboxes()
		{
			//waitForElementTobeClickable(sidebarUser, driver);
	    	selectAllCheckboxes();
		}
	    
	    public void clickSearchCancel()
		{
			//waitForElementTobeClickable(sidebarUser, driver);
	    	searchcancel.click();
		}
	    
	    
	    public void clickcheckboxbyname(String checkboxName)
		{
			//waitForElementTobeClickable(sidebarUser, driver);
			getCheckbox(checkboxName).click();
		}
	    
	    public void clickcheckboxbyname2(String checkboxName)
		{
			//waitForElementTobeClickable(sidebarUser, driver);
			findCheckbox(checkboxName).click();
		}
	    
	    public void clickcheckbox1()
		{
			//waitForElementTobeClickable(sidebarUser, driver);
			checkbox1.click();
		}
	    
	    public void clicksidebarUser()
		{
			waitForElementTobeClickable(sidebarUser, driver);
			sidebarUser.click();
		}
	    
	    public void clickPlusBtn()
		{
			waitForElementTobeClickable(Userplusbtn, driver);
			Userplusbtn.click();
		}
	    
	    public void userName(String Name)
		{
			waitForElementTobeClickable(Username, driver);
			Username.click();
			Username.sendKeys(Name);
		}
	    
	    public void userMobileno(String mobileno)
		{
			waitForElementTobeClickable(Mobilenumber, driver);
			Mobilenumber.click();
			Mobilenumber.sendKeys(mobileno);
		}
	    
	    public void userPassword(String password)
		{
			waitForElementTobeClickable(Password, driver);
			Password.click();
			Password.sendKeys(password);
		}
	    
	    public void selectRole()
		{
			waitForElementTobeClickable(Selectrole, driver);
			Selectrole.click();
			
		}
	    
	    public void selectBranchManager()
		{
			waitForElementTobeClickable(BranchManager, driver);
			BranchManager.click();
			
		}
	    
	    public void selectWarehouseManager()
		{
			waitForElementTobeClickable(WarehouseManager, driver);
			WarehouseManager.click();
			
		}
	    
	    public void selectSupervisor()
		{
			waitForElementTobeClickable(Supervisor, driver);
			Supervisor.click();
			
		}
	    
	    public void selectBranch()
		{
			waitForElementTobeClickable(Selectbranch, driver);
			Selectbranch.click();
			
		}
	    
	    public void searchBranchClick()
		{
			waitForElementTobeClickable(Searchbranch, driver);
			Searchbranch.click();
			
			
		}
	    
	    public void searchBranch(String branchName)
		{
			waitForElementTobeClickable(Searchbranchtxt, driver);
			Searchbranchtxt.click();
			Searchbranchtxt.sendKeys(branchName);
			
		}
	    
	    public void selectBranchCheck()
		{
			waitForElementTobeClickable(branchcheckbox, driver);
			branchcheckbox.click();
			
		}
	    
	    public void clickOk()
		{
			waitForElementTobeClickable(OKbtn, driver);
			OKbtn.click();
			
		}
	    
	    public void selectWarehouse()
		{   scrollToElement(selectallWarehouse);
		    scrollTwithpercentAction(1);
			waitForElementTobeClickable(Selectwarehouse, driver);
			Selectwarehouse.click();
			
		}
	    
	    public void searchWarehouseClick()
		{
			waitForElementTobeClickable(Searchwarehouse, driver);
			Searchwarehouse.click();
			
			
		}
	    
	    public void searchWarehouse(String warehouseName)
		{
			waitForElementTobeClickable(Searchwarehousetxt, driver);
			Searchwarehousetxt.click();
			Searchwarehousetxt.sendKeys(warehouseName);
			
		}
	    
	    public void selectAllWarehouse()
		{  scrollToElement(selectallWarehouse);
			//waitForElementTobeClickable(selectallWarehouse, driver);
			selectallWarehouse.click();
			
		}
	    
	    public void selectCategory()
		{   
	    	scrollToElement(SelectAllCategory);
	    	//scrollToEndAction();
	    	//scrollToText("Category");
	    	scrollTwithpercentAction(1);
			waitForElementTobeClickable(SelectCategory, driver);
			SelectCategory.click();
			
		}
	    
	    public void searchCategoryClick()
		{
			waitForElementTobeClickable(SearchCategory, driver);
			SearchCategory.click();
				
		}
	    
	    public void searchCategory(String CategoryNm)
		{
			waitForElementTobeClickable(SearchCategorytxt, driver);
			SearchCategorytxt.click();
			SearchCategorytxt.sendKeys(CategoryNm);
			
		}
	    
	    public void selectAllCategory()
		{   
	    	//scrollToText("Category");
	    	scrollToElement(SelectAllCategory);
		   // scrollTwithpercentAction(10);
			//waitForElementTobeClickable(SelectAllCategory, driver);
			SelectAllCategory.click();
			
		}
	    
	    public void selectGroup()
		{  
	    	scrollToElement(SelectAllGroup);
	    	scrollTwithpercentAction(1);
	    	//scrollToEndAction();
	    	//scrollToText("Group");
			waitForElementTobeClickable(SelectGroup, driver);
			SelectGroup.click();
			
		}
	    
	    public void searchGroupClick()
		{
			waitForElementTobeClickable(SearchGroup, driver);
			SearchGroup.click();
			
			
		}
	    
	    public void searchGroup(String GroupNm)
		{
			waitForElementTobeClickable(SearchGrouptxt, driver);
			SearchGrouptxt.click();
			SearchGrouptxt.sendKeys(GroupNm);
			
		}
	    
	    public void selectAllGroup()
		{   
	    	scrollToElement(SelectAllGroup);
	    	//scrollToEndAction();
	    	//scrollToText("Group");
	    	//scrollTwithpercentAction(10);
			//waitForElementTobeClickable(SelectAllGroup, driver);
			SelectAllGroup.click();
			
		}
	    
	    public void clickSubmitButton() {
	    	
	    	  
	        if (!allButtons.isEmpty()) {
	        	  scrollToEndAction();
	            allButtons.get(allButtons.size() - 1).click();
	        } else {
	            throw new NoSuchElementException("No buttons found on the page");
	        }
	    
	    }
	    
	    public void ClickDone()
		{
			waitForElementTobeClickable(Donebtn, driver);
			Donebtn.click();
				
		}
	    
	    public void searchUser(String UserNm)
		{
			waitForElementTobeClickable(SearchUser, driver);
			SearchUser.click();
			SearchUser.sendKeys(UserNm);
				
		}
	    
	    public void userResultClick()
		{
			//waitForElementTobeClickable(UseResult, driver);
			UseResult.get(1).click();
				
		}
	    
	    public  void ClickBack()
		{
			waitForElementTobeClickable(BackButton, driver);
			BackButton.click();;
			
		}
	    
	    public boolean isOutofplanmsgdisplayed()
		{
			try {
	        	//waitForElementToAppear(toastmesg, driver);
	            return isElementPresent(outofplanmsg);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
			
		      
		}
		public boolean issuccessmsgdisplayed()
		{
			try {
	        	//waitForElementToAppear(toastmesg, driver);
	            return isElementPresent(successmsg);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		}
		
		public boolean ismobilenoalreadyexistmsgdisplayed()
		{
			try {
	        	//waitForElementToAppear(toastmesg, driver);
	            return isElementPresent(mobilenoalreadyexist);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		}

		public boolean isupdatesuccessmsgdisplayed()
		{
			try {
	        	//waitForElementToAppear(toastmesg, driver);
	            return isElementPresent(updatesuccessmsg);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		}
		
		 public boolean isUselistfounddisplayed()
			{
				try {
		        	//waitForElementToAppear(toastmesg, driver);
		            return isElementPresent(userfound);
		        } catch (NoSuchElementException e) {
		            return false;//null != null;
		        }
				
			      
			}
}
