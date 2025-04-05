package vtigerCRM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WeBDriverUtility;

public class DemoScriptWithDDTGUPOM_TestScript_5 {

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
		String LASTNAME=eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
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
	       
	       //step-3 home page -click on Contact link
	       HomePage hp=new HomePage(driver);
	       hp.getContactLink().click();
	       
	       //step-4  Click on create contact look up image
	       ContactsPage cp=new ContactsPage(driver);
	       cp.getContactLookUpImage().click();
	       
	       //Step-5 fill the details
	       CreateContactPage ccp= new CreateContactPage(driver);
	       ccp.getLastnameTextField().sendKeys(LASTNAME);
	       //wutil.toSwitchWindow(driver,);
	       
	       
	       
	       
	       
	       
	       
	       
	      //step 7 - Logout
	       wutil.toMouseHover(driver,hp.getAdminImage());
	       Thread.sleep(3000);
	       hp.getLogoutLink().click();
	       
	       //step8-close
	       driver.quit();

	}

}
