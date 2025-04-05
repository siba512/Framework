package vtigerCRM;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WeBDriverUtility;

public class DemoScriptWithGenericMethod_Create_Organization {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//to read data from common Data File
				PropertyFileUtility eutil=new PropertyFileUtility();
			     String URL= eutil.toReadDataFromPropertyFile("url");
			     String USERNAME=  eutil.toReadDataFromPropertyFile("username");
			     String PASSWORD=eutil.toReadDataFromPropertyFile("password");
			     String BROWSER=eutil.toReadDataFromPropertyFile("browser");
			     //to read data from excel file
			     ExcelFileUtility efil=new ExcelFileUtility();
			    String orgname= efil.toReadDataFromExcelFile("Organization",1,2);
			   //Launch browser
					WebDriver driver=null;
					if(BROWSER.equals("chrome")) {
						driver=new ChromeDriver();
					}else if(BROWSER.equals("edge")) {
						driver=new EdgeDriver();
					}else if(BROWSER.equals("firefox")) {
						driver=new FirefoxDriver();
					}
					   WeBDriverUtility web=new WeBDriverUtility();
					     web.toMaximizeWindow(driver);
					     web.toWaitElemnt(driver);
					//driver.manage().window().maximize();
					//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
					driver.get(URL);
					//Login with valid credentials
					driver.findElement(By.name("user_name")).sendKeys(USERNAME);
					driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
					driver.findElement(By.id("submitButton")).click();
					Thread.sleep(4000);
					System.out.println("Login Completed");
					
					//Navigate to Organization link
					driver.findElement(By.linkText("Organizations")).click();
					Thread.sleep(4000);
					
					//Click on Create Organization Look up Image
					driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
					//generate random number
					Random r=new Random();
					int random=r.nextInt(1000);
					
					//Create Organization with mandatory fields
					driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname+random);
					Thread.sleep(4000);
					
					//Save and verify
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					//Alert a=driver.switchTo().alert();
					//System.out.println(a.getText());
					//Thread.sleep(3000);
					//a.accept();
					String organization=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
					if(organization.contains(orgname+random)) {
						System.out.println(organization + ".....Passed");
					}else {
						System.out.println(organization + ".....Failed");
					}
					
					//logout from application
					WebElement logoutele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
					Actions action=new Actions(driver);
					action.moveToElement(logoutele).perform();
					driver.findElement(By.linkText("Sign Out")).click();
					System.out.println("Logout Completed");
					
					// cLose browser
					driver.quit();
		


	}

}
