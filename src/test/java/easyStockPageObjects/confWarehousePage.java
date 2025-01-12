package easyStockPageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class confWarehousePage extends AndroidActions {

	private FlutterAndroidDriver driver;
	
	public confWarehousePage (FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
	
	@AndroidFindBy(accessibility  = "Settings")
	private WebElement sidebarsetting;
	
	@AndroidFindBy(accessibility  = "Configure Warehouse")
	private WebElement Configurewarehouse;
	
	@AndroidFindBy(xpath  = "//android.view.View[@content-desc=\"Configure Warehouse\"]/parent::android.view.View/following-sibling::android.widget.Button")
	private WebElement clickbranch;
	
	public WebElement getbranch(String branchnm) {
        String xpath = String.format("//android.widget.Button[@content-desc=\"%s\"]", branchnm);
        return driver.findElement(AppiumBy.xpath(xpath));
    }

	
	public void selectbranch(String branchnm)
	{
		waitForElementTobeClickable(getbranch(branchnm), driver);
	      getbranch(branchnm).click();
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'Available Warehouse Count :')]")//(//android.view.View)[2]
	private WebElement subcriptioncount;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[1]")
	private WebElement Firstcheckbox;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[2]")
	private WebElement Secondcheckbox;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[3]")
	private WebElement Thirdcheckbox;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[4]")
	private WebElement Fourthcheckbox;
	
	@AndroidFindBy(xpath = "(//android.widget.CheckBox)[5]")
	private WebElement Fifthcheckbox;
	
	@AndroidFindBy(accessibility = "Configure")
	private WebElement configurebtn;
	
	@AndroidFindBy(accessibility  = "Yes")
	private WebElement yesbtn;
	
	@FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]")
	private WebElement BackButton;
	
	@FindBy(className   = "android.widget.Button")
	private WebElement FinalButton;
	
	public void settingClick()
	{
		waitForElementTobeClickable(sidebarsetting, driver);
		sidebarsetting.click();
	      
	}
	public void branchClick()
	{
		waitForElementTobeClickable(clickbranch, driver);
		clickbranch.click();
	      
	}
	
	public void confWarehouseClick()
	{
		waitForElementTobeClickable(Configurewarehouse, driver);
		Configurewarehouse.click();
	      
	}
	
	public String subscriptionCount()
	{
		waitForElementToAppear(subcriptioncount, driver);
	      return subcriptioncount.getDomAttribute("content-desc");
	}
	
	public void chooseWarehouse1()
	{
		waitForElementTobeClickable(Firstcheckbox, driver);
		Firstcheckbox.click();
	      
	}
	public boolean isWarehouse1Enabled()
	{
		try {
			waitForElementToAppear(Firstcheckbox, driver);
	        return isElementEnabled(Firstcheckbox);
     } catch (NoSuchElementException e) {
         return false;
     }
	}
	
     public boolean isWarehouse1selected()
		{
			try {
				waitForElementToAppear(Firstcheckbox, driver);
		        return isElementEnabled(Firstcheckbox);
	     } catch (NoSuchElementException e) {
	         return false;
	     }

		}
	      
	
	public void chooseWarehouse2()
	{
		waitForElementTobeClickable(Secondcheckbox, driver);
		Secondcheckbox.click();
	      
	}
	public boolean isWarehouse2Enabled()
	{
		try {
			waitForElementToAppear(Secondcheckbox, driver);
	        return isElementEnabled(Secondcheckbox);
     } catch (NoSuchElementException e) {
         return false;
         }
     }
	
	public boolean isWarehouse2selected()
	{
		try {
			waitForElementToAppear(Secondcheckbox, driver);
	        return isElementEnabled(Secondcheckbox);
     } catch (NoSuchElementException e) {
         return false;
     }

	}
		
	public void chooseWarehouse3()
	{
		waitForElementTobeClickable(Thirdcheckbox, driver);
		Thirdcheckbox.click();
	      
	}
	public boolean isWarehouse3Enabled()
	{
		try {
			waitForElementToAppear(Thirdcheckbox, driver);
	        return isElementEnabled(Thirdcheckbox);
     } catch (NoSuchElementException e) {
         return false;
         }
     }
	
	public boolean isWarehouse3selected()
	{
		try {
			waitForElementToAppear(Thirdcheckbox, driver);
	        return isElementEnabled(Thirdcheckbox);
     } catch (NoSuchElementException e) {
         return false;
     }

	}
	
	public void chooseWarehouse4()
	{
		waitForElementTobeClickable(Fourthcheckbox, driver);
		Fourthcheckbox.click();
	      
	}
	public boolean isWarehouse4Enabled()
	{
		try {
			waitForElementToAppear(Fourthcheckbox, driver);
	        return isElementEnabled(Fourthcheckbox);
     } catch (NoSuchElementException e) {
         return false;
         }
     }
	
	public boolean isWarehouse4selected()
	{
		try {
			waitForElementToAppear(Fourthcheckbox, driver);
	        return isElementEnabled(Fourthcheckbox);
     } catch (NoSuchElementException e) {
         return false;
     }

	}
	
	public void chooseWarehouse5()
	{
		waitForElementTobeClickable(Fifthcheckbox, driver);
		Fifthcheckbox.click();
	      
	}
	public boolean isWarehouse5Enabled()
	{
		try {
			waitForElementToAppear(Fifthcheckbox, driver);
	        return isElementEnabled(Fifthcheckbox);
     } catch (NoSuchElementException e) {
         return false;
         }
     }
	
	public boolean isWarehouse5selected()
	{
		try {
			waitForElementToAppear(Fifthcheckbox, driver);
	        return isElementEnabled(Fifthcheckbox);
     } catch (NoSuchElementException e) {
         return false;
     }

	}
	
	public void clickConfigure()
	{
		waitForElementTobeClickable(configurebtn, driver);
		configurebtn.click();
	      
	}
	public void clickYes()
	{
		waitForElementTobeClickable(yesbtn, driver);
		yesbtn.click();
	      
	}
	public  void ClickBack()
	{
		waitForElementTobeClickable(BackButton, driver);
		BackButton.click();
		
	}
	public  void FinalBack()
	{
		waitForElementTobeClickable(FinalButton, driver);
		FinalButton.click();
		
	}
	
}
