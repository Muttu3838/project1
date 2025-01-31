package easyStockPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CreateAccountPage extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	
	
	 public CreateAccountPage(FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}

	 @AndroidFindBy(xpath  = "//android.view.View[@content-desc=\"Create Account\"]")
	 private WebElement CraeteAccbtn;
	//android.widget.ScrollView/android.widget.EditText[1]
	 
	 @AndroidFindBy(xpath  = "//android.widget.ScrollView/android.widget.EditText[1]")
	 private WebElement mobilenm;
	 
	 @AndroidFindBy(xpath  = "//android.widget.ScrollView/android.widget.EditText[2]")
	 private WebElement email;
	 
	 @AndroidFindBy(xpath  = "//android.widget.ScrollView/android.widget.EditText[3]")
	 private WebElement pwd;
	//android.widget.ScrollView/android.widget.CheckBox[1]
	 
	 @AndroidFindBy(xpath  = "//android.widget.ScrollView/android.widget.CheckBox[1]")
	 private WebElement acceptTC;
	 
	 @AndroidFindBy(xpath  = "//android.widget.ScrollView/android.widget.CheckBox[1]")
	 private WebElement submitBtn;
	 
	 public void createAccBtn()
		{
			waitForElementTobeClickable(CraeteAccbtn, driver);
			CraeteAccbtn.click();
		      
		}
	 
	 public void mobileNmField(String MoblieNm)
		{
			waitForElementTobeClickable(mobilenm, driver);
			mobilenm.click();
			mobilenm.clear();
			mobilenm.sendKeys(MoblieNm);
		      
		}
	 
	 public void emailIdField(String EmailId)
		{
			waitForElementTobeClickable(email, driver);
			email.click();
			email.clear();
			email.sendKeys(EmailId);
		      
		}
	 
	 public void passwordField(String password)
		{
			waitForElementTobeClickable(pwd, driver);
			pwd.click();
			pwd.clear();
			pwd.sendKeys(password);
		      
		}
	
	public void acceptTC()
		{
			waitForElementTobeClickable(acceptTC, driver);
			acceptTC.click();
			
		}
	
	public void submitBtn()
	{
		waitForElementTobeClickable(submitBtn, driver);
		submitBtn.click();
		
	}
}
