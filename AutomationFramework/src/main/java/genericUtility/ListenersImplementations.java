package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementations implements ITestListener {
	
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		System.out.println(methodname+" ...Started");
		test =report.createTest(methodname);
		
	}

	public void onTestSuccess(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		System.out.println(methodname+" ...Passed");
		test.log(Status.PASS,methodname +"...Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		System.out.println(methodname+" ...Failed");
		test.log(Status.FAIL,methodname +"...Failed");
		test.log(Status.INFO,result.getThrowable());
		
		WeBDriverUtility wutil=new WeBDriverUtility();
		JavaUtility jutil= new JavaUtility();
		
		String screenshotname=methodname+"-"+jutil.toGetSystemDateAndTime();
		try {
			String path=wutil.toTakeScreenShot(BaseClass.sDriver,screenshotname);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		System.out.println(methodname+" ...Skipped");
		test.log(Status.SKIP,methodname +"...Skipped");
		test.log(Status.INFO,result.getThrowable());
		
		
	}

	public void onStart(ITestContext context) {
		System.out.println("---Suite execution Started---");
		//Extent Reports
		ExtentSparkReporter htmlreport=new ExtentSparkReporter("./ExtentReports/Reports-"+new JavaUtility().toGetSystemDateAndTime()+".html");
		htmlreport.config().setDocumentTitle("Vtiger Execution report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName(" VTIGER EXECUTION REPORT");
		
		report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("BaseUrl","http://localhost:8888/");
		report.setSystemInfo("Username","admin");
		report.setSystemInfo("Password","password");
		report.setSystemInfo("Reporter Name","Siba");
		
		
	}


	public void onFinish(ITestContext context) {
		System.out.println("---Suite execution Finished---");
		report.flush();
		
	}
	

}
