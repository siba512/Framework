package vtigerCRM;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganizationInformation;
import ObjectRepository.Organizations;
import ObjectRepository.ToCreateOrganization;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WeBDriverUtility;

public class DemoScriptWithDDTGUPOM_TestScript_3 {

	public static void main(String[] args) throws IOException, InterruptedException {
		PropertyFileUtility putil=new PropertyFileUtility();
		WeBDriverUtility wutil=new WeBDriverUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		
		//Read data from Property file
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//Read data from excel file
		String orgname=eutil.toReadDataFromExcelFile("Organization", 1, 2);
		//Launch browser
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
		//Login to application
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		//step-3 home page -click on Organization  link
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		
		//step-4Organization page Click on create Organization look up image
		Organizations org=new Organizations(driver);
		org.getOrganizationLookUpImage().click();
		
		//Create Organization with Mandatory Fields
		ToCreateOrganization corg=new ToCreateOrganization(driver);
		Random r=new Random();
		int random=r.nextInt(1000);
		corg.getOrganizationTextField().sendKeys(orgname+random);
		Thread.sleep(2000);
		wutil.toHandleDropDown(corg.getIndustryDropDown(),4);
		//save and verify
		corg.getSaveButton().click();
		OrganizationInformation cip=new OrganizationInformation(driver);
	       String orgheadername=cip.getOrganizationheaderInformation().getText();
	       if(orgheadername.contains(orgname+random)) {
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
