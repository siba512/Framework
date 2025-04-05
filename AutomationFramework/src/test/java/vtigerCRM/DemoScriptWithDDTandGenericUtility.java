package vtigerCRM;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WeBDriverUtility;

public class DemoScriptWithDDTandGenericUtility {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExcelFileUtility eutil=new ExcelFileUtility();
		PropertyFileUtility putil=new PropertyFileUtility();
		WeBDriverUtility wutil=new WeBDriverUtility();
		
		//Read data from property file
		String URL= putil.toReadDataFromPropertyFile("url");
	    String USERNAME=  putil.toReadDataFromPropertyFile("username");
	    String PASSWORD=putil.toReadDataFromPropertyFile("password");
	    String BROWSER=putil.toReadDataFromPropertyFile("browser");
	    
	    //Read data from excel file
	    String LASTNAME=eutil.toReadDataFromExcelFile("Contacts",1,2);
	    
	    //Launch browser
	       WebDriver driver=null;
	       if(BROWSER.equals("chrome")) {
	    	   driver=new ChromeDriver();
	       }else if(BROWSER.equals("edge")) {
	    	   driver=new EdgeDriver();
	       }else if(BROWSER.equals("firefox")) {
	    	   driver=new FirefoxDriver();
	       }
	        wutil.toMaximizeWindow(driver);
		      
	        wutil.toWaitElemnt(driver);
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
						wutil.toMouseHover(driver,logoutele);
						driver.findElement(By.linkText("Sign Out")).click();
						System.out.println("Logout Completed");
						
						// cLose browser
						driver.quit();

	}

}
