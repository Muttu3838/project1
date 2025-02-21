package easyStockTestCases;

import org.testng.annotations.Test;

import easyStockPageObjects.LoginPage;
import easyStockPageObjects.SubscriptionPage;
import easyStockUtilities.AndroidActions;

public class TC_subscriptionAddon_001 extends BaseClass {

    // Set the default value for addonName to "All"
    String addonName = "All"; // Default value
    int numof=1;
    @Test()
    public void Addon() throws InterruptedException {
    	Addonbywallet(addonName,numof);  // Call the Addon by Upi method with the default value or dynamic value
    	//Addon(addonName,numof);  // Call the Addon by wallet method with the default value or dynamic value
    }

    public void Addonbywallet(String addonName,int numof) throws InterruptedException {
       
        SubscriptionPage sp = new SubscriptionPage(driver);
        AndroidActions scroll = new AndroidActions(driver);
        LoginPage lp=new LoginPage(driver);
        // Click on profile
        
        sp.setSidebarsubscription();

        // Click on Addons section
        sp.setAddonsClick();

        // Handle different addon scenarios
        if (addonName.equalsIgnoreCase("branch")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
            sp.setPlusBranch();
            }
        } 
        else if (addonName.equalsIgnoreCase("User")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusUser();
            }
            
        } else if (addonName.equalsIgnoreCase("Warehouse")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusWarehouse();
            }
            
        } else if (addonName.equalsIgnoreCase("Support")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusSupport();
            }
            
            
        } else if (addonName.equalsIgnoreCase("All")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusBranch();
                sp.setPlusUser();
                sp.setPlusWarehouse();
                sp.setPlusSupport();
            }
            
        }

        // Proceed with the payment flow
        sp.setPaynow();
        Thread.sleep(5000);
        sp.setProceedtopay();
        
        sp.setWalletMode();
        
        sp.setTestWallet();
       
       //String txt=sp.setAmountdeatils();
       //System.out.println(txt);
        //Thread.sleep(2000);
       // sp.setProceedtopay2();
        //sp.setWalletcard();
        //Thread.sleep(2000);
        //sp.setBackToPayment();
        sp.setpaybtn();
        //sp.setTestWalletprocced();

        // Scroll to the bottom
        //scroll.scrollTwithpercentAction(1);
        
        // Handle OTP flow
        sp.setOtpClick();
        
        sp.setOtp("111000");

        // Finalize the subscription
        sp.setSuccess();
        sp.setSubmitBtn();
        softAssert.assertTrue(sp.ispaymentSuccessfull(), "Payment Succefull");
        sp.setDoneBtn();
        lp.setprofilebtn();
        
    }
    
    public void AddonbyUpi(String addonName,int numof) throws InterruptedException {
        
        SubscriptionPage sp = new SubscriptionPage(driver);
        AndroidActions scroll = new AndroidActions(driver);
        LoginPage lp=new LoginPage(driver);
        // Click on profile
        
        sp.setSidebarsubscription();

        // Click on Addons section
        sp.setAddonsClick();

        // Handle different addon scenarios
        if (addonName.equalsIgnoreCase("branch")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
            sp.setPlusBranch();
            }
        } 
        else if (addonName.equalsIgnoreCase("User")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusUser();
            }
            
        } else if (addonName.equalsIgnoreCase("Warehouse")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusWarehouse();
            }
            
        } else if (addonName.equalsIgnoreCase("Support")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusSupport();
            }
            
            
        } else if (addonName.equalsIgnoreCase("All")) {
        	for (int i = 1; i <= numof; i++) 
        	{  
        		sp.setPlusBranch();
                sp.setPlusUser();
                sp.setPlusWarehouse();
                sp.setPlusSupport();
            }
            
        }

        // Proceed with the payment flow
        sp.setPaynow();
        
        sp.setProceedtopay();
        
        //sp.setWalletMode();
        //sp.setTestWallet();
        sp.setbyUpiId();
        sp.setUsePass();
       
       //String txt=sp.setAmountdeatils();
       //System.out.println(txt);
        Thread.sleep(2000);
        sp.setProceedtopay2();
        //sp.setWalletcard();
        //Thread.sleep(2000);
        //sp.setBackToPayment();
        //sp.setpaybtn();
        //sp.setTestWalletprocced();

        // Scroll to the bottom
       // scroll.scrollTwithpercentAction(1);
        
        // Handle OTP flow
        //sp.setOtpClick();
        
       // sp.setOtp("111000");

        // Finalize the subscription
        //sp.setSuccess();
        //sp.setSubmitBtn();
        Thread.sleep(8000);
       // softAssert.assertTrue(sp.ispaymentSuccessfull(), "Payment Succefull");
        sp.setDoneBtn();
        lp.setprofilebtn();
        
    }
}
