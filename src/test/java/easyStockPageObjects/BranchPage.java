package easyStockPageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BranchPage extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	
	 public BranchPage (FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
	 
	@AndroidFindBy(accessibility = "Branch")
	private WebElement sidebarbranch;
	
	@FindBy(xpath  = "(//android.widget.Button)[4]")
	private WebElement createbranchbtn;
	
	@FindBy(xpath  = "(//android.widget.EditText)[1]")
	private WebElement branchname;
	
	@FindBy(xpath  = "(//android.widget.EditText)[2]")
	private WebElement landmark;
	
	@FindBy(xpath  = "(//android.widget.EditText)[3]")
	private WebElement location;
	
	@AndroidFindBy(xpath  = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]")
	private WebElement locpermision;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement Enterloctxt;
	
	@AndroidFindBy(xpath = "(//android.widget.Button)[3]")
	private WebElement searchbtn;
	
	@AndroidFindBy(xpath = "(//android.view.View)[6]")
	private WebElement searcresult;
	
	@AndroidFindBy(xpath  = "(//android.widget.Button)[2]")
	private WebElement passbtn;
	
	@AndroidFindBy(xpath  = "(//android.widget.Button)[2]")
	private WebElement submitbranchbtn;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Out of Plan! Kindly subscribe.\"]")
	private WebElement outofplanmsg;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Branch Added Successfully\"]")
	private WebElement successmsg;
	
	@FindBy(className  = "android.widget.EditText")
	private WebElement Searchbranch;
	
	@FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]")
	private WebElement BackButton;
	
	 	//calssname--android.widget.EditText
	//location permission--//android.widget.Button[@resource-id="com.android.permissioncontroller:id/permission_allow_one_time_button"]
	//---xpath for pass button---//android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[2]
	public void setSidebarbranch()
	{
		waitForElementTobeClickable(sidebarbranch, driver);
	      sidebarbranch.click();
	}
	
	public void setCreatebranch()
	{
		waitForElementTobeClickable(createbranchbtn, driver);
		createbranchbtn.click();
	}
	
	public void setbranchnameclick()
	{ 
		waitForElementTobeClickable(branchname, driver);
		branchname.click();
		
	}
	
	public void setbranchname(String branchnm)
	{
		waitForElementToAppear(branchname, driver);
		branchname.sendKeys(branchnm);
		
	}
	public void landmarkclick()
	{   
		waitForElementTobeClickable(landmark, driver);
		landmark.click();
		
	}
	public void landmarkclickname(String lndmk)
	{
		waitForElementToAppear(landmark, driver);
		landmark.sendKeys(lndmk);
		
	}
	public void locclick()
	{
		waitForElementTobeClickable(location, driver);
		location.click();
		
	}
	public void locEntertxt(String loc)
	{
		waitForElementTobeClickable(landmark, driver);
		landmark.sendKeys(loc);
		
	}
	public void locpesmisiion()
	{
		waitForElementTobeClickable(locpermision, driver);
		locpermision.click();
		
	}
	public void locnametxt()
	{
		waitForElementTobeClickable(Enterloctxt, driver);
		Enterloctxt.click();
		
	}
	public void loctxt(String loctxt)
	{
		waitForElementToAppear(Enterloctxt, driver);
		Enterloctxt.sendKeys(loctxt);
		
	}
	
	public void Searchbtn()
	{
		waitForElementTobeClickable(searchbtn, driver);
		searchbtn.click();
		
	}
	public void Searchresult()
	{
		waitForElementTobeClickable(searcresult, driver);
		searcresult.click();
		
	}
	public void passbtn()
	{
		waitForElementTobeClickable(passbtn, driver);
		passbtn.click();
		
	}
	public void Submitbranch()
	{
		waitForElementTobeClickable(submitbranchbtn, driver);
		submitbranchbtn.click();
		
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
	public String toastmsg()
	{
		waitForElementToAppear(outofplanmsg, driver);
		return outofplanmsg.getDomAttribute("content-desc");
		
	}
	public  void searchBranchClick()
	{
		waitForElementTobeClickable(Searchbranch, driver);
		Searchbranch.click();;
		
	}
	public  void searchBranchName(String branchnm)
	{
		waitForElementToAppear(Searchbranch, driver);
		Searchbranch.sendKeys(branchnm);
		
	}
	public  void ClickBack()
	{
		waitForElementTobeClickable(BackButton, driver);
		BackButton.click();;
		
	}

	

}
