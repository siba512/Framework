package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToCreateOrganization {
	//Constructor
	public ToCreateOrganization(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement organizationTextField;
	
	
	@FindAll({@FindBy(name="button"),@FindBy(xpath="//input[@title='Save [Alt+S]']")})
	private WebElement saveButton;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;

	public WebElement getOrganizationTextField() {
		return organizationTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

}
