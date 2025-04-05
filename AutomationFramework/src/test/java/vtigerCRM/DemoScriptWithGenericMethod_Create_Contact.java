package vtigerCRM;



import java.io.IOException;
import java.time.Duration;

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


public class DemoScriptWithGenericMethod_Create_Contact {

	public static void main(String[] args) throws IOException {
		//to read data from common Data File
		PropertyFileUtility eutil=new PropertyFileUtility();
	      String URL= eutil.toReadDataFromPropertyFile("url");
	     String USERNAME=  eutil.toReadDataFromPropertyFile("username");
	       String PASSWORD=eutil.toReadDataFromPropertyFile("password");
	       String BROWSER=eutil.toReadDataFromPropertyFile("browser");
	       
	       //To read data from excel file
	       ExcelFileUtility excl=new ExcelFileUtility();
	       String LASTNAME=excl.toReadDataFromExcelFile("Contacts",1,2);
	       
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
	      // web.toMinimizeWindow(driver);
	       web.toWaitElemnt(driver);
	    //   driver.manage().window().maximize();
	      //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       driver.get(URL);
	       //Login with valid Credentials
	       driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			System.out.println("Login Completed");
			//navigate to contact link
	        driver.findElement(By.linkText("Contacts")).click();
			
			//step-4 Click on Create Contact Look up Image
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			//step 5-Create contact with mandatory fields
			driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
			//step -6 Save and verify
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					String headname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
					if(headname.contains(headname)) {
						System.out.println(headname + ".....Passed");
					}else {
						System.out.println(headname + "......Failed");
					}
					
					//step 7-logout from application
					WebElement logoutele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
					Actions action=new Actions(driver);
					action.moveToElement(logoutele).perform();
					driver.findElement(By.linkText("Sign Out")).click();
					System.out.println("Logout Completed");
					
					// cLose browser
					driver.quit();
	       
	       
	       

	}

}
