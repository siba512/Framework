package vtigerCRM;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ObjectRepository.ContactInformation;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganizationInformation;
import ObjectRepository.Organizations;
import ObjectRepository.ToCreateOrganization;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WeBDriverUtility;

public class DemoScriptWithDDTGUPOM_Create_ORG {

	public static void main(String[] args) throws IOException, InterruptedException {
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WeBDriverUtility wutil=new WeBDriverUtility();
		
		//Read data from Property file
		String URL=putil.toReadDataFromPropertyFile("url");
		String BROWSER=putil.toReadDataFromPropertyFile("browser");
		String USERNAME=putil.toReadDataFromPropertyFile("username");
		String PASSWORD=putil.toReadDataFromPropertyFile("password");
		
		//Read data from excel file
		String ORGNAME=eutil.toReadDataFromExcelFile("Organization", 1, 2);
		
		//Step-1 Launch Browser
		 WebDriver driver=null;
	       if(BROWSER.equals("chrome")) {
	    	   driver=new ChromeDriver();
	       }else if(BROWSER.equals("edge")) {
	    	   driver=new EdgeDriver();
	       }else if(BROWSER.equals("firefox")) {
	    	   driver=new FirefoxDriver();
	       }
	       wutil.toMaximizeWindow(driver);
	       wutil.toWaitElemnt(driver);
	       
	       driver.get(URL);
	       
	       //Step 2-Login to application
	       LoginPage lp=new LoginPage(driver);
	       lp.getUsernameTextField().sendKeys(USERNAME);
	       lp.getPasswordTextField().sendKeys(PASSWORD);
	       lp.getLoginButton().click();
	       
	     //step-3 home page -click on Organization  link
	       HomePage hp=new HomePage(driver);
	       hp.getOrganizationLink().click();
	       
	     //step-4  Click on create Organization look up image
	       Organizations og=new Organizations(driver);
	       og.getOrganizationLookUpImage().click();
	       
	     //Step-5 fill the details
	       ToCreateOrganization cog=new ToCreateOrganization(driver);
	       Random r=new Random();
			int random=r.nextInt(1000);
			cog.getOrganizationTextField().sendKeys(ORGNAME+random);
			
			//step 6 save and verify
			cog.getSaveButton().click();
			OrganizationInformation cip=new OrganizationInformation(driver);
		       String orgheadername=cip.getOrganizationheaderInformation().getText();
		       if(orgheadername.contains(ORGNAME+random)) {
		    	   System.out.println(orgheadername+ "is passed ");
		       }else {
		    	   System.out.println(orgheadername+ " is failed");
		       }
		       
		       //step 7 - Logout
		       wutil.toMouseHover(driver,hp.getAdminImage());
		       Thread.sleep(3000);
		       hp.getLogoutLink().click();
		       
		       //step8-close
		       driver.quit();
			
	       

	}

}
