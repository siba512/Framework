package ContactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepository.ContactInformation;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WeBDriverUtility;
@Listeners(genericUtility.ListenersImplementations.class)
public class ToCreateContactWithOrgtTest extends BaseClass{
	@Test(groups = "smoke")
	public void toCreateContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {
		
		//Click on Contacts
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		//Click on Create contact look up image
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactLookUpImage().click();
		
		//To pass the data in lastname
		CreateContactPage ccp= new CreateContactPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME=eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastnameTextField().sendKeys(LASTNAME);
		
		//click on organization look up image
		WeBDriverUtility wutil=new WeBDriverUtility();
		ccp.getOrganizationLookupImage().click();
		
		//switch driver control to child window
		wutil.toSwitchWindow(driver,"Accounts");
		
		//click on Jspider137
		driver.findElement(By.xpath("//a[text()='Jspider201']")).click();
		
		//switch driver control to parent window
		wutil.toSwitchWindow(driver,"Contacts");
		Thread.sleep(3000);
		
		// save and verify
		ccp.getSaveButton().click();
		ContactInformation ci=new ContactInformation(driver);
		String headinfo=ci.getHeaderInformation().getText();
		Assert.assertTrue(headinfo.contains(LASTNAME));
		Reporter.log(headinfo+"added succesfully");
	}
	
	

}
