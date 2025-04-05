package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	//Constructor
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement lastnameTextField;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement organizationLookupImage;
	
	@FindAll({@FindBy(name="button"),@FindBy(xpath="//input[@title='Save [Alt+S]']")})
	private WebElement saveButton;

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}
	public WebElement getOrganizationLookupImage() {
		return organizationLookupImage;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	

}
