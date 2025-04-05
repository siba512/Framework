package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//Constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImage;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationLink;
	
	@FindBy(linkText="Sign Out")
	private WebElement logoutLink;

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getAdminImage() {
		return adminImage;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}
}
