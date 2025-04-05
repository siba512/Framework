package ContactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepository.ContactInformation;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
@Listeners(genericUtility.ListenersImplementations.class)
public class ToCreateContactTest extends BaseClass{
	
	@Test(groups = "smoke")
	public void toCreateContact_01() throws EncryptedDocumentException, IOException {
		HomePage hp= new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactLookUpImage().click();
		CreateContactPage ccp= new CreateContactPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME=eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastnameTextField().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		//Fail
		//Assert.fail();
		ContactInformation ci=new ContactInformation(driver);
		String headinfo=ci.getHeaderInformation().getText();
		Assert.assertTrue(headinfo.contains(LASTNAME));
		
	}
	

}
