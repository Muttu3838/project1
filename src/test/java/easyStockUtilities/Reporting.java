package easyStockUtilities;
//Listener class used to generate Extent reports

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import easyStockTestCases.BaseClass;

public class Reporting extends TestListenerAdapter
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	String repName;
	
	//yyyy.MM.dd.HH.mm.ss
	public void onStart(ITestContext testContext)
	{
	    
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd. HH.mm.ss").format(new Date());//time stamp
		 repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/"+repName);//specify location of the report
		
		sparkReporter.config().setDocumentTitle("EasyStock Test Project"); // Tile of report
		sparkReporter.config().setReportName("Test Automation Report"); // name of the report
		
		extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","EasyStock");
		extent.setSystemInfo("user", System.getProperty("user.name"));
		extent.setSystemInfo("Tester","Mrutyunjaya");
		extent.setSystemInfo("Environment","QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser =testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
	    List<String> includegroups = testContext.getCurrentXmlTest().getIncludedGroups();
	    if(!includegroups.isEmpty())
	    {
	    	extent.setSystemInfo("Groups", includegroups.toString());
	    }
		
		sparkReporter.config().setTheme(Theme.STANDARD);
	}
	
	public void onTestSuccess(ITestResult result)
	{  //getTestClass().getName()
		logger=extent.createTest(result.getMethod().getMethodName()+" got passed"); // create new entry in th report
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.PASS,result.getName()+" got successfully executed");
		logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS, "Test case passed");
		//Screenshot taking on every TestCase Pass
		try {
			String imgPath=new BaseClass().captureScreen(result.getTestClass().getName());
		    logger.addScreenCaptureFromPath(imgPath);
			
			}
		catch(IOException e1)
		{
		e1.printStackTrace();
		}
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		
		logger=extent.createTest(result.getMethod().getMethodName()+" got failed"); // create new entry in th report
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.FAIL,result.getName()+" got failed");
		logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		logger.log(Status.FAIL, "Test case Failed due to : "+ result.getThrowable());
		
		
		try {
			String imgPath=new BaseClass().captureScreen(result.getTestClass().getName());
		    logger.addScreenCaptureFromPath(imgPath);
			
			}
		catch(IOException e1)
		{
		e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		logger=extent.createTest(result.getTestClass().getName()+" got skipped"); // create new entry in th report
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.SKIP,result.getName()+" got Skipped");
		logger.log(Status.SKIP, "Test case skipped due to : "+ result.getThrowable());
		logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+ repName;
		File extentReport= new File(pathofExtentReport);
		
		try{
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		}
	
}