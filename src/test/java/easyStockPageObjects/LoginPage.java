package easyStockPageObjects;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	
	     
	   
	    	// TODO Auto-generated method stub
	    	
		// TODO Auto-generated method stub
		
	
	
	
	public LoginPage(FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
		
	}
                               
	@FindBy(xpath   = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
	private WebElement permallowbtn; 
	                           
	@FindBy(xpath   = "(//android.widget.EditText)[1]")
	private WebElement txtUserName; 
	
	@FindBy(xpath = "(//android.widget.EditText)[2]" )
	private WebElement txtPassword; 
	
	@FindBy(xpath = "//android.widget.Button")
	private WebElement btnLogin;   
	
	//WebElement btnprofile=driver.findElement(FlutterBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView"));
	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView[1]")
	private WebElement btnprofile;
	
	@FindBy(xpath = "//android.view.View[@content-desc='Logout']")
	private WebElement btnlogout;
	
	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Yes\"]")
	private WebElement yesbtn;

	@AndroidFindBy(accessibility = "Login Successfull")
    private WebElement loginSuccessMessage;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Invalid mobile number\"]")
    private WebElement invalidMobileMessage;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Invalid password\r\n"
    		+ "*Password must contain at least eight characters,at least one number and both lower and uppercase letters and special characters\"]")
    private WebElement invalidPasswordMessage;
    
    @AndroidFindBy(accessibility  = "User not found. Create your account.")
    private WebElement userNotFoundMessage;
    
    @FindBy(xpath  = "//android.widget.Button[@content-desc=\"Back\"]")
	private WebElement BackButton;

  //android.view.View[@content-desc=\"Branch\"]/parent::android.view.View/parent::android.view.View/preceding-sibling::android.view.View/android.widget.Button
	@FindBy(xpath   = "//android.view.View[@content-desc=\"Branch\"]/ancestor::android.view.View[2]/preceding-sibling::android.view.View/android.widget.Button")
	private WebElement FinalButton;
	//android.view.View[@content-desc=\"Version 1.0.0 A product by MWB Technologies India\"]/android.view.View[1]/android.widget.Button
    
	public boolean isBackButtonVisible() {
        try {
            return isElementPresent(BackButton);  // Use your existing locator
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	public boolean isFinalBackVisible() {
        try {
            return isElementPresent(FinalButton);  // Use your existing locator
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
    public boolean isLoginSuccessful() {

        try {
        	//waitForElementToAppear(loginSuccessMessage, driver);
            return isElementPresent(loginSuccessMessage);
        } catch (NoSuchElementException e) {
            return false;//null != null;
        }
        //waitForElementToAppear(loginSuccessMessage, driver);
        //return isElementPresent(loginSuccessMessage);
    }

    public boolean isInvalidMobileNumberDisplayed() {
    	
    	 try {
    		    
    	        return isElementPresent(invalidMobileMessage);
         } catch (NoSuchElementException e) {
             return false;
         }
    	
    }

    public boolean isInvalidPasswordDisplayed() {
    	try {
    		
            return isElementPresent(invalidPasswordMessage);
        } catch (NoSuchElementException e) {
            return false;
        }
    	
    }
    
    public boolean isUserNotFoundMessageDisplayed() throws InterruptedException {
          try {
        	 
        	  return isElementPresent(userNotFoundMessage);
        } catch (NoSuchElementException e) {
            return false;
        }
    	
    }

    
	
	public void setpermisionallow()
	{
		
		waitForElementTobeClickable(permallowbtn, driver);
		permallowbtn.click();
		
	}
	public void setprofilebtn()
	{
		
		waitForElementTobeClickable(btnprofile, driver);
		btnprofile.click();
		
	}
	public boolean isprofilebtndisplayed()
	{
		 try {
	        	waitForElementToAppear(btnprofile, driver);
	            return isElementPresent(btnprofile);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		
	      
	}
	
	public void setlogoutbtn() 
	{   
		scrollTwithpercentAction(1);
		waitForElementTobeClickable(btnlogout, driver);
			btnlogout.click();
	}
	
	public void setUserNameclick() throws InterruptedException
	{
		waitForElementTobeClickable(txtUserName, driver);	
		txtUserName.click();
		
		
	}
	public void setUserNameclear() throws InterruptedException
	{
		waitForElementTobeClickable(txtUserName, driver);	
		txtUserName.clear();
		
		
	}
	public void setUserName(String uname)
	{
		
		waitForElementToAppear(txtUserName, driver);
		txtUserName.sendKeys(uname);
	}
	
	public void setPasswordClick()
	{
		waitForElementTobeClickable(txtPassword, driver);
		txtPassword.click();
		
		
	}
	public void setPasswordClear()
	{
		
		txtPassword.clear();
		
		
	}
	public void setPassword(String pwd)
	{
		
		waitForElementToAppear(txtPassword, driver);
		txtPassword.sendKeys(pwd);
	}
	
	public BranchPage clicksubmit() throws InterruptedException
	{
		
		btnLogin.click();
		return new BranchPage(driver);
	}
	
	public void setyes() throws InterruptedException
	{
		waitForElementTobeClickable(yesbtn, driver);	
		yesbtn.click();
	}
	
	public  void ClickBack()
	{
		waitForElementTobeClickable(BackButton, driver);
		BackButton.click();;
		
	}
	
	public  void FinalBack()
	{
		waitForElementTobeClickable(FinalButton, driver);
		FinalButton.click();
		
	}
	
	

}
