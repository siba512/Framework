package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformation {
	//Constructor
			public OrganizationInformation(WebDriver driver){
				PageFactory.initElements(driver, this);
			}
			@FindBy(xpath="//span[@class='dvHeaderText']")
			private WebElement organizationheaderInformation;
			
			public WebElement getOrganizationheaderInformation() {
				return organizationheaderInformation;
			}

}
