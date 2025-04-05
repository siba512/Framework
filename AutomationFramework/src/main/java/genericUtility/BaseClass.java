package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class BaseClass {
	PropertyFileUtility putil=new PropertyFileUtility();
	WeBDriverUtility wutil=new WeBDriverUtility();
	
	public WebDriver driver = null;
	public static WebDriver sDriver;
	
	@BeforeSuite(groups = {"smoke","Regression"})
	//This annotated method will be run before all tests in this suite have run.
	public void beforeSuiteConfiguration() {
		Reporter.log(".....DataBase connection established---",true);
	}
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups = {"smoke","Regression"})
	//This annotated method will be run before the first test method in the current class is invoked. 
	public void beforeClassConfiguration(/*String BROWSER*/) throws IOException {
		 String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER=putil.toReadDataFromPropertyFile("browser");
		 
		if(BROWSER.contains("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver=new EdgeDriver();
			
		}else if(BROWSER.contains("firefox")) {
			driver=new FirefoxDriver();
		}
		Reporter.log("Browser got Launched",true);;
		sDriver=driver;
		wutil.toMaximizeWindow(driver);
		wutil.toWaitElemnt(driver);
		driver.get(URL);
	}
	@BeforeMethod(groups = {"smoke","Regression"})
	//This annotated method will be run before each test method
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME=putil.toReadDataFromPropertyFile("username");
		String PASSWORD=putil.toReadDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Logged in Succesfully",false);
	}
	
	@AfterMethod(groups = {"smoke","Regression"})
	//This annotated method will be run after each test method
	public void afterMethodConfiguration() {
		HomePage hp= new HomePage(driver);
		wutil.toMouseHover(driver, hp.getAdminImage());
		hp.getLogoutLink().click();
		Reporter.log("Logged out successfully",false);
	}
	
	@AfterClass(groups = {"smoke","Regression"})
	//This annotated method will be run after all the test method in the current class have been run
	public void afterClassConfiguration() {
		Reporter.log("Browser got Closed",true);
		driver.quit();
	}
	@AfterSuite(groups = {"smoke","Regression"})
	//This annotated method will be run after all tests in this suite have run
	public void afterSuiteConfiguration() {
		Reporter.log("Database connection Disconnected",true);
	}

}
