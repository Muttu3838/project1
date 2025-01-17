package easyStockPageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SubscriptionPage extends AndroidActions {

	private FlutterAndroidDriver driver;

	 public SubscriptionPage(FlutterAndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

	} 

	@AndroidFindBy(accessibility = "Subscription & Addons")
	private WebElement Subscription;

	@AndroidFindBy(accessibility = "Addons")
	private WebElement Addons;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(2)")
	private WebElement plusbranch;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(4)")
	private WebElement plususer;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(6)")
	private WebElement pluswarehouse;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(8)")
	private WebElement plussupport;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Pay Now\"]")
	private WebElement Paynow;
	
	@AndroidFindBy(accessibility = "Proceed to Pay")
	private WebElement pcoceedtopay;
	
	@AndroidFindBy(accessibility  = "Wallets")
	private WebElement walletmode;
	
	@AndroidFindBy(xpath  = "//android.view.View[@text=\"Wallet icon Test Wallet\"]")
	private WebElement testwallet;
	//android.widget.TextView[@text=\"Amount Details\"]/parent::android.view.View/android.view.View/android.widget.Button
	@FindBy(xpath  = "//android.widget.Button[@text=\"Back to Payment Methods\"]")
	private WebElement BacktoPayment;
	
	@FindBy(xpath  = "//android.widget.TextView[@text=\"Amount Details\"]")
	private WebElement Amountdetails;
	
	@FindBy(xpath  = "//android.webkit.WebView[@text=\"Cashfree Checkout\"]/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.Button")
	private WebElement ProceedtoPay2;
	
	@FindBy(xpath  = "//android.widget.Button[@text=\"Pay using Test Wallet\"]")
	private WebElement TestWalletProceed;
	//contains(text(), 'search text') //android.widget.Button[contains(text(), 'Proceed to Pay')]
	@AndroidFindBy(className = "androidx.cardview.widget.CardView")
	private WebElement choosewalletcard;
	
	@AndroidFindBy(id = "com.mwb.stock_taker:id/btn_wallet")
	private WebElement paybtn;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"basic-otp\"]")
	private WebElement otpfield;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\" SUCCESS\"]")
	private WebElement success;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Submit\"]")
	private WebElement submitbtn;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Done\"]")
	private WebElement donebtn;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Your Payment has been processed successfully\"]")
	private WebElement SuccessfullPayment;

	public void setSidebarsubscription() {
		waitForElementTobeClickable(Subscription, driver);
		Subscription.click();
	}

	public void setAddonsClick() {
		waitForElementTobeClickable(Addons, driver);
		Addons.click();
	}

	public void setPlusBranch() {
		waitForElementTobeClickable(plusbranch, driver);
		plusbranch.click();
	}

	public void setPlusUser() {
		waitForElementTobeClickable(plususer, driver);
		plususer.click();
	}

	public void setPlusWarehouse() {
		waitForElementTobeClickable(pluswarehouse, driver);
		pluswarehouse.click();
	}

	public void setPlusSupport() {
		waitForElementTobeClickable(plussupport, driver);
		plussupport.click();
	}

	public void setPaynow() {
		waitForElementTobeClickable(Paynow, driver);
		Paynow.click();
	}

	public void setProceedtopay() {
		waitForElementTobeClickable(pcoceedtopay, driver);
		pcoceedtopay.click();
	}

	public void setWalletMode() {
		waitForElementTobeClickable(walletmode, driver);
		walletmode.click();
	}
	
	public void setTestWallet() {
		waitForElementTobeClickable(testwallet, driver);
		testwallet.click();
	}
	
	public String setAmountdeatils() {
		waitForElementTobeClickable(testwallet, driver);
		return Amountdetails.getText();
	}
	
	public void setProceedtopay2() {
		waitForElementTobeClickable(ProceedtoPay2, driver);
		ProceedtoPay2.click();
	}
	
	public void setBackToPayment() {
		waitForElementTobeClickable(BacktoPayment, driver);
		BacktoPayment.click();
	}
	
	public void setTestWalletprocced() {
		waitForElementTobeClickable(TestWalletProceed, driver);
		TestWalletProceed.click();
	}

	public void setWalletcard() {
		waitForElementTobeClickable(choosewalletcard, driver);
		choosewalletcard.click();
	}

	public void setpaybtn() {
		waitForElementTobeClickable(paybtn, driver);
		paybtn.click();
	}

	public void setOtpClick() {
		waitForElementTobeClickable(otpfield, driver);
		otpfield.click();
	}

	public void setOtp(String OTP) {
		waitForElementToAppear(otpfield, driver);
		otpfield.sendKeys(OTP);
		
	}

	public void setSuccess() {
		waitForElementTobeClickable(success, driver);
		success.click();
	}

	public void setSubmitBtn() {
		waitForElementTobeClickable(submitbtn, driver);
		submitbtn.click();

	}
	
	public boolean ispaymentSuccessfull()
	{
		 try {
	        	waitForElementToAppear(SuccessfullPayment, driver);
	            return isElementPresent(SuccessfullPayment);
	        } catch (NoSuchElementException e) {
	            return false;//null != null;
	        }
		
	      
	}

	public void setDoneBtn() {
		waitForElementTobeClickable(donebtn, driver);
		donebtn.click();

	}

}
