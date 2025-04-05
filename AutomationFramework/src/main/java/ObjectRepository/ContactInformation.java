package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformation {
	//Constructor
		public ContactInformation(WebDriver driver){
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement contactheaderInformation;

		public WebElement getHeaderInformation() {
			return contactheaderInformation;
		}

}
