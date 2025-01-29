package easyStockPageObjects;

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

public class UploadPage extends AndroidActions {

	
	private FlutterAndroidDriver driver;
	
	 public UploadPage (FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
	
	@AndroidFindBy(accessibility = "Upload")
	private WebElement sidebarUpload;
	
	@AndroidFindBy(xpath = "(//android.widget.Button)[3]")
	private WebElement clickbranch;
	
	@AndroidFindBy(xpath = "(//android.widget.Button)[1]")
	private WebElement selectBranch;
	
	@AndroidFindBy(accessibility = "Select file")
	private WebElement Selectfile;
	
	@AndroidFindBy(accessibility = "Warehouse Master")
	private WebElement SelectWarehouseMaster;
	
	@AndroidFindBy(accessibility = "Item Master")
	private WebElement SelectItemMaster;
	
	@AndroidFindBy(accessibility = "Opening Stock")
	private WebElement SelectOpeningStock;
	
	@AndroidFindBy(accessibility = "Closing Stock")
	private WebElement SelectClosingStock;
	
	@AndroidFindBy(accessibility = "Select file type")
	private WebElement SelectFileType;
	
	@AndroidFindBy(accessibility = "XML")
	private WebElement SelectXML;
	
	@AndroidFindBy(accessibility = "ASCII")
	private WebElement SelectASCII;
	
	@AndroidFindBy(accessibility = "upload your file here\nBrowse")
	private WebElement Uploadfile;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Search\"]")
	private WebElement searchicon;
	
	@AndroidFindBy(className  = "android.widget.AutoCompleteTextView")
	private WebElement searchfiletxt;
	
	//android.widget.TextView[@resource-id=\"android:id/title\"])[1]
	@AndroidFindBy(xpath  = "//android.widget.LinearLayout[@resource-id=\"com.google.android.documentsui:id/item_root\"]/android.widget.LinearLayout/android.widget.LinearLayout")
	private WebElement selectfile;
	
	@AndroidFindBy(uiAutomator   = "new UiSelector().className(\"android.widget.Button\").instance(5)")
	private WebElement ClickSubmit;
	
	@AndroidFindBy(accessibility = "Yes")
	private WebElement ConfirmYes;
	
	@AndroidFindBy(accessibility = "No")
	private WebElement ConfirmNo;
	
	@AndroidFindBy(accessibility = "Warehouses uploaded successfully.")
	private WebElement successmsgWarehouse;
	
	@AndroidFindBy(accessibility = "Items uploaded successfully.")
	private WebElement successmsgItems;
	
	@AndroidFindBy(accessibility = "Tally stock added succesfully.")
	private WebElement successmsgtallystk;
	
	@AndroidFindBy(accessibility = "Congratulations")
	private WebElement congrats;
	
	@AndroidFindBy(accessibility = "Continue")
	private WebElement continuebtn;
	
	@AndroidFindBy(accessibility = "Please Upload Valid File")
	private WebElement Invalidmsg;
	
	@FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]")
	private WebElement BackButton;
	
	@FindBy(xpath  = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]")
	private WebElement Notification;
	
	
	public WebElement getbranch(String branchnm) {
        String xpath = String.format("//android.widget.Button[@content-desc=\"%s\"]", branchnm);
        return driver.findElement(AppiumBy.xpath(xpath));
    }
	//android.widget.FrameLayout[@content-desc=\"Preview the file \"+\"%s\"]/android.widget.ImageView
	public WebElement getfile(String filename) {
        String xpath = String.format("//android.widget.TextView[contains(@text, \"%s"+".xml\")]", filename);
        		
        return driver.findElement(AppiumBy.xpath(xpath));
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
	public void setSidebarUpload()
	{
		waitForElementTobeClickable(sidebarUpload, driver);
	      sidebarUpload.click();
	}
	
	public void setSelectFile()
	{
		waitForElementTobeClickable(Selectfile, driver);
	      Selectfile.click();
	}
	
	public void setClickbranch()
	{
		waitForElementTobeClickable(clickbranch, driver);
	      clickbranch.click();
	}
	
	public void selectbranch(String branchnm)
	{
		waitForElementTobeClickable(getbranch(branchnm), driver);
	      getbranch(branchnm).click();
	}
	public void selectWarehouseMaster()
	{
		waitForElementTobeClickable(SelectWarehouseMaster, driver);
	      SelectWarehouseMaster.click();
	}
	
	public void selectItemMaster()
	{
		waitForElementTobeClickable(SelectItemMaster, driver);
	      SelectItemMaster.click();
	}
	
	public void selectOpeningStock()
	{
		waitForElementTobeClickable(SelectOpeningStock, driver);
	      SelectOpeningStock.click();
	}
	
	public void selectClosingStock()
	{
		waitForElementTobeClickable(SelectClosingStock, driver);
	      SelectClosingStock.click();
	}
	
	public void selectFiletype()
	{
		waitForElementTobeClickable(SelectFileType, driver);
	      SelectFileType.click();
	}
	
	public void selectXml()
	{
		waitForElementTobeClickable(SelectXML, driver);
	      SelectXML.click();
	}
	
	public void selectASCII()
	{
		waitForElementTobeClickable(SelectASCII, driver);
	      SelectASCII.click();
	}
	
	public void uploadFile()
	{
		waitForElementTobeClickable(Uploadfile, driver);
	      Uploadfile.click();
	}
	
	public void searchClick()
	{
		waitForElementTobeClickable(searchicon, driver);
	      searchicon.click();
	}
	
	public void searchFiletxt(String filename)
	{
		waitForElementToAppear(searchfiletxt, driver);
	      searchfiletxt.sendKeys(filename);;
	}
	
	public void selectFilebyName(String filename) throws InterruptedException
	{
		TimeUnit.SECONDS.sleep(5);
		
			
		  waitForElementTobeClickable(getfile(filename), driver);
	      getfile(filename).click();
		
	}
	public void selectFile() throws InterruptedException
	{
		TimeUnit.SECONDS.sleep(3);
		waitForElementTobeClickable(selectfile, driver);
	    selectfile.click();
	}
	
	public void clickSubmit()
	{
		waitForElementTobeClickable(ClickSubmit, driver);
	      ClickSubmit.click();
	}
	public boolean isconfirmYes()
	{
		 try {
	        	waitForElementToAppear(ConfirmYes, driver);
	            return isElementPresent(ConfirmYes);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		
	      
	}
	
	public void confirmYes()
	{
		waitForElementTobeClickable(ConfirmYes, driver);
	      ConfirmYes.click();
	}
	
	public void confirmNo()
	{
		waitForElementTobeClickable(ConfirmNo, driver);
	      ConfirmNo.click();
	}
	
	
	
	public boolean isSuccessMsgWarehouse()
	{
		 try {
	        	//waitForElementToAppear(successmsgWarehouse, driver);
	            return isElementPresent(successmsgWarehouse);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		
	      
	}
	
	public boolean isSuccessMsgItems()
	{
		try {
        	//waitForElementToAppear(successmsgItems, driver);
            return isElementPresent(successmsgItems);
        } catch (NoSuchElementException e) {
            return false;//null != null;
        }
		
	}
	
	public boolean issuccessMsgTallyStock()
	{
		try {
        	//waitForElementToAppear(successmsgtallystk, driver);
            return isElementPresent(successmsgtallystk);
        } catch (NoSuchElementException e) {
            return false;//null != null;
        }
		
	}
	
	public boolean iscongratsMsg()
	{
		try {
        	//waitForElementToAppear(congrats, driver);
            return isElementPresent(congrats);
        } catch (NoSuchElementException e) {
            return false;//null != null;
        }
		
	}
	
	public boolean isContinuepresent()
	{
		try {
        	//waitForElementToAppear(continuebtn, driver);
            return isElementPresent(continuebtn);
        } catch (NoSuchElementException e) {
            return false;//null != null;
        }
		
	}
	
	public void continueBtn()
	{
		waitForElementToAppear(continuebtn, driver);
		continuebtn.click();
	      
	}
	
	public boolean invalidMsg()
	{
		try {
        	//waitForElementToAppear(Invalidmsg, driver);
            return isElementPresent(Invalidmsg);
        } catch (NoSuchElementException e) {
            return false;//null != null;
        }
		
	      
	}
	
	
	
	public  void ClickBack()
	{
		waitForElementTobeClickable(BackButton, driver);
		BackButton.click();;
		
	}
}
