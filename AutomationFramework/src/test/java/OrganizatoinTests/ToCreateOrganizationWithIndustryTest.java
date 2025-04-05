package OrganizatoinTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepository.HomePage;
import ObjectRepository.OrganizationInformation;
import ObjectRepository.Organizations;
import ObjectRepository.ToCreateOrganization;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WeBDriverUtility;
@Listeners(genericUtility.ListenersImplementations.class)
public class ToCreateOrganizationWithIndustryTest extends BaseClass {
	
	@Test(groups="Regression")
	public void toCreateOrganizationWithIndustry() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		Organizations og=new Organizations(driver);
		og.getOrganizationLookUpImage().click();
		ToCreateOrganization cog=new ToCreateOrganization(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String orgname=eutil.toReadDataFromExcelFile("Organization", 1, 2);
		Random r=new Random();
		int random=r.nextInt(1000);
		cog.getOrganizationTextField().sendKeys(orgname+random);
		WeBDriverUtility wutil= new WeBDriverUtility();
		wutil.toHandleDropDown(cog.getIndustryDropDown(),4);
		cog.getSaveButton().click();
		OrganizationInformation oginfo=new OrganizationInformation(driver);
		String orginfo=oginfo.getOrganizationheaderInformation().getText();
		Assert.assertTrue(orginfo.contains(orgname+random));
		
	}

}
