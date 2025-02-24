package easyStockPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import easyStockUtilities.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ReportsPage extends AndroidActions {
	
	private FlutterAndroidDriver driver;
	//private final PointerInput finger;
	
	 public ReportsPage(FlutterAndroidDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		//this.finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	}
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Reports\"]")
	 private WebElement reportsbtn;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Reports\"]/parent::android.view.View/following-sibling::android.view.View[1]/android.view.View/android.view.View/android.widget.ImageView[1]")
	 private WebElement mismatchreportbtn;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Mismatch Items Report\"]/following-sibling::android.widget.Button[1]")
	 private WebElement filterbtn;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Select year\"]/preceding-sibling::android.view.View[1]/android.view.View/android.widget.Button")
	 private WebElement EditDate;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Cancel\"]/preceding-sibling::android.widget.EditText")
	 private WebElement DateTxt;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
	 private WebElement Okbtn;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Sort & Filters\"]/android.widget.Button[1]")
	 private WebElement closeFilterbtn;
	
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Diff Stock\"]/following-sibling::android.view.View/android.view.View[2]")
	 private WebElement TallyStock;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Diff Stock\"]/following-sibling::android.view.View/android.view.View[3]")
	 private WebElement EnteredQuantity;
	 
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Diff Stock\"]/following-sibling::android.view.View/android.view.View[4]")
	 private WebElement DiffQuantity;
	 
	//android.widget.EditText
	 @AndroidFindBy(xpath = "//android.widget.EditText")
	 private WebElement serachbtn;
	 
	
	 @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Select Warehouse\"]/preceding-sibling::android.widget.Button")
	 private WebElement BranchBtn;
	 
	 public  void reportsBtn()
		{
			waitForElementTobeClickable(reportsbtn, driver);
			reportsbtn.click();
			
		}
	 
	 public  void missMatchReportBtn()
		{
			waitForElementTobeClickable(mismatchreportbtn, driver);
			mismatchreportbtn.click();
			
		}
	 
	 public  void filterBtn()
		{
			waitForElementTobeClickable(filterbtn, driver);
			filterbtn.click();
			
		}
	 
	 
	 public  void ClickAtPosition(int x,int y)
		{
			tapAtPosition(x, y, driver);
			
		}
	 
	 public  void clickEdit()
		{
			waitForElementTobeClickable(EditDate, driver);
			EditDate.click();
			
		}
	 
	 public  void clickBranch()
		{
			waitForElementTobeClickable(BranchBtn, driver);
			BranchBtn.click();
			
		}
	 
	//android.widget.Button[@content-desc="Branch08"]
	 public WebElement getbranch(String branchnm) {
	        String xpath = String.format("//android.widget.Button[@content-desc=\"%s\"]", branchnm);
	        return driver.findElement(AppiumBy.xpath(xpath));
	    }
	 
	 public  void selectBranch(String BranchName)
		{
			waitForElementTobeClickable(getbranch(BranchName), driver);
			getbranch(BranchName).click();
			
		}
	 
	 
	 
		public  void chooseDate(String EnterDate)
		{
			waitForElementTobeClickable(DateTxt, driver);
			DateTxt.clear();
			DateTxt.sendKeys(EnterDate);
			
		}
		
		public void clickOk()
		{
			waitForElementTobeClickable(Okbtn, driver);
			Okbtn.click();
			
		}
	 
		public void closeBtn()
		{
			waitForElementTobeClickable(closeFilterbtn, driver);
			closeFilterbtn.click();
			
		}
		
		public void searchItem(String ItemName)
		{
		 waitForElementTobeClickable(serachbtn, driver);
		 
		 serachbtn.click();
		 serachbtn.clear();
		 serachbtn.sendKeys(ItemName);
		}
		
		public boolean isSearchPresent() throws InterruptedException
		{
			Thread.sleep(1000);
		 return isElementPresent(serachbtn);
		}
		
		public double TallyStock()
		{
			try {
			waitForElementToAppear(TallyStock, driver);
		  String value=TallyStock.getDomAttribute("content-desc");
		  double Quantity = Double.parseDouble(value);
		  return Quantity;
			}catch(Exception e)
			{
				return 0;
			}
			
		}
		
		public double EnteredQuantity()
		{
			try {
			waitForElementToAppear(EnteredQuantity, driver);
		  String value=EnteredQuantity.getDomAttribute("content-desc");
		  double Quantity = Double.parseDouble(value);
			return Quantity;
			}catch(Exception e)
			{
				return 0;
			}
		}
		
		public  double DiffQuantity()
		{
			try {
			waitForElementToAppear(DiffQuantity, driver);
		  String value=DiffQuantity.getDomAttribute("content-desc");
		  double Quantity = Double.parseDouble(value);
			return Quantity;
			}catch(Exception e)
			{
				return 0;
			}
			
		}
}
